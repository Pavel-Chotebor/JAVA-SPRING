package com.greenfox.reddit.services;

import com.greenfox.reddit.models.Post;
import com.greenfox.reddit.models.Vote;
import com.greenfox.reddit.repositories.PostRepository;
import com.greenfox.reddit.repositories.VoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private VoteService voteService;
    private VoteRepository voteRepository;

    public PostServiceImpl(PostRepository postRepository, VoteService voteService, VoteRepository voteRepository) {
        this.postRepository = postRepository;
        this.voteService = voteService;
        this.voteRepository = voteRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> allPosts = new ArrayList<>();
        allPosts = postRepository.findAll().stream().collect(Collectors.toList());
        return allPosts;
    }


    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }


    // USER CAN GIVE AND CHANGE LIKE OR DISLIKE UNDER POSTS
    @Override
    public void likeMethod(Long postIdFromLikeVoting, Long postIdFromDisLikeVoting, Long userId) {

        // WHEN USER CLICK TO LIKE
        if (postIdFromLikeVoting != null) {
            Vote existingVote = getPostById(postIdFromLikeVoting).getVotes().stream()
                 .filter(vote -> vote.getVotedByUserId().equals(userId)).findFirst().orElse(null);

            // IF VOTE DOES NOT EXIST YET ->
        if (existingVote == null) {

            // -> THEN NEW VOTE IS CREATED WITH TRUE FOR LIKE AND POST RATING +1
          saveVotedPostWithVote(userId, postIdFromLikeVoting, true);

            // IF VOTE EXIST -> VOTE IS NOW CHANGED FOR LIKE AND POST RATING IS +1
        } else if (!existingVote.isVotedForLike()) {
            editAndSaveVotedPost(existingVote, postIdFromLikeVoting, true);
        }

        // SAME SITUATION WHEN USER CLICK TO DISLIKE, POST CANNOT HAVE LESS THEN 0 VOTES
    } else if (postIdFromDisLikeVoting != null && getPostById(postIdFromDisLikeVoting).getRating() > 0) {

            // CHECKING IF USER ALREADY GIVE A VOTE
            Vote existingVote = getPostById(postIdFromDisLikeVoting).getVotes().stream()
                    .filter(vote -> vote.getVotedByUserId().equals(userId)).findFirst().orElse(null);

            // IF VOTE DOES NOT EXIST YET ->
        if (existingVote == null) {

            // -> THEN NEW VOTE IS CREATED WITH DISLIKE AND POST RATING -1
            saveVotedPostWithVote(userId, postIdFromDisLikeVoting, false);

            // IF VOTE EXIST -> VOTE IS NOW CHANGED FOR DISLIKE AND POST RATING IS -1
        } else if (existingVote.isVotedForLike()){
            editAndSaveVotedPost(existingVote, postIdFromDisLikeVoting, false);
        }
        }
    }


        // USING PAGINATION FOR SORTING A PRINTING ONLY NUMBER OF POSTS WE WANT
    @Override
    public List<Post> getSortedPostsByNewest (Integer pageNumber, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());

         Page <Post> pagedResult = postRepository.findAll(paging);

         if (pagedResult.hasContent()) {
             return pagedResult.getContent();
         } else {
             return new ArrayList<Post>();
         }
    }

    @Override
    public void saveVotedPostWithVote(Long userId, Long postIdFromVotingForm, boolean isVotedForLike) {

        Vote vote = new Vote(isVotedForLike, userId);
        vote.setPost(getPostById(postIdFromVotingForm));
        voteService.saveVote(vote);
        getPostById(postIdFromVotingForm).setRating(getPostById(postIdFromVotingForm).getRating() + getVoteValueByVoteAction(isVotedForLike));
        savePost(getPostById(postIdFromVotingForm));
    }

    @Override
    public void editAndSaveVotedPost(Vote existingVote, Long postIdFromVotingForm, boolean isVotedForLike) {
        existingVote.setVotedForLike(isVotedForLike);
        getPostById(postIdFromVotingForm).setRating(getPostById(postIdFromVotingForm).getRating() + getVoteValueByVoteAction(isVotedForLike));
        savePost(getPostById(postIdFromVotingForm));
    }

    public int getVoteValueByVoteAction (boolean isVotedForLike) {
        if(isVotedForLike) {
            return 1;
        } else {
            return -1;
        }
    }
}