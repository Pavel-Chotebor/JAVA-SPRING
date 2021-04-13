package com.greenfox.reddit.controllers;

import com.greenfox.reddit.models.User;
import com.greenfox.reddit.services.PostService;
import com.greenfox.reddit.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("/user")
public class UserController {

    private UserService userService;
    private PostService postService;

    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }


    // NEW USER REGISTRATION ENDPOINT
    @GetMapping ("/registration")
    public String showUserRegistrationForm () {
        return "userRegistration";
    }

    // NEW USER REGISTRATION PROCESSING
    @PostMapping ("/registrationProcessing")
    public String registrationProcessing (Model model, @ModelAttribute User user, @RequestParam String username,
                                          @RequestParam String password, @RequestParam String passwordCheck) {

        // NOT EVERY FIELD IS FILLED
        if (password.isEmpty() || passwordCheck.isEmpty() || username.isEmpty() ) {
            model.addAttribute("fieldsError", true);
            return "userRegistration";
        }

        // USERNAME IS ALREADY USED
        else if (userService.isNameAlreadyExist(username)) {
            model.addAttribute("usernameError", true);
            return "userRegistration";

            // SUCCESSFUL REGISTRATION -> REDIRECT USER LOGIN
        } else if (passwordCheck.equals(password)) {
            userService.saveUser(user);
            return "redirect:/user/login";

            // PASSWORD DOESNT MATCH
        } else if (!passwordCheck.equals(password)) {
            model.addAttribute("passwordError", true);
        }
        return "userRegistration";
    }


    // LOGIN PAGE
    @GetMapping ("/login")
    public String showUserLoginForm () {
        return "userLogin";
    }

    //LOGIN PROCESSING and CONTROLLING USERNAME AND PASSWORDS, IF ITS OK -> REDIRECTING TO MAIN NAVIGATION WITH LOGGED USER
    @PostMapping("loginProcessing")
    public String loginProcessing(Model model, @ModelAttribute User user, @RequestParam String password,
                                  @RequestParam String username) {

        // SUCCESSFUL LOGIN -> REDIRECT REDDIT MAIN PAGE
        if (userService.isNameAlreadyExist(username)
                && userService.getUserByName(username).getPassword().equals(password)) {
            Long userId = userService.getUserByName(username).getId();

            return "redirect:/post/main/user=" + userId;

            // WRONG PASSWORD -> BACK TO LOGIN
        } else if (userService.isNameAlreadyExist(username)
                && !userService.getUserByName(username).getPassword().equals(password)) {
            model.addAttribute("passwordError", "incorrect password!");
            return "userLogin";

            // WRONG USERNAME -> BACK TO LOGIN
        } else if (!userService.isNameAlreadyExist(username)) {
            model.addAttribute("usernameError", "incorrect username!");
        }
        return "userLogin";
    }
}