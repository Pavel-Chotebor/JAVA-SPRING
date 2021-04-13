package com.greenfox.reddit.controllers;

import com.greenfox.reddit.models.Post;
import com.greenfox.reddit.services.PostService;
import com.greenfox.reddit.services.PostServiceImpl;
import com.greenfox.reddit.services.UserService;
import com.greenfox.reddit.services.VoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("/post")
public class PostController {

    private UserService userService;
    private PostService postService;
    private VoteService voteService;

    public PostController(UserService userService, PostService postService, VoteService voteService) {
        this.userService = userService;
        this.postService = postService;
        this.voteService = voteService;
    }


    // MAIN PAGE
    @GetMapping ("/main/user={userId}")
    public String showMainPage (Model model, @PathVariable Long userId) {
        model.addAttribute("posts", postService.getSortedPostsByNewest(0, 4, "id"));
        model.addAttribute("userId", userId);

        return "redditMainPage";
    }


    // RATING LIKE-DISLIKE PROCESSING -> USER CAN VOTE ONLY ONCE ON ONE POST
    @PostMapping ("/ratingProcessing/user={userId}")
    public String ratingProcessing (@PathVariable Long userId, @RequestParam (required = false) Long like,
                                    @RequestParam (required = false) Long dislike, @ModelAttribute Post post) {

       postService.likeMethod(like, dislike, userId);
        return "redirect:/post/main/user=" + userId;
    }

    // ADD NEW POST FORM
    @GetMapping ("/addNewPost/user={userId}")
    public String showNewPostForm (Model model, @PathVariable Long userId) {
        model.addAttribute("userId", userId);
        return "newPostForm";
    }

    // SAVING NEW POST
    @PostMapping ("/addNewPostProcessing/user={userId}")
    public String addNewPostProcessing (@PathVariable Long userId, @ModelAttribute Post post) {
        postService.savePost(post);
        post.setUser(userService.getUserById(userId));
        postService.savePost(post);

        return "redirect:/post/main/user=" + userId;
    }

    @GetMapping("/account/user={userId}")
    public String showAccount (Model model, @PathVariable Long userId) {
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("likedPosts", voteService.findVoteByUserId(userId));
        return "userAccount";
    }
}
