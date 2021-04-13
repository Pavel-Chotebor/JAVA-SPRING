package com.greenfox.reddit.services;

import com.greenfox.reddit.models.Post;
import com.greenfox.reddit.models.Vote;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface PostService {

    List<Post> getAllPosts();

    void savePost (Post post);

    public Post getPostById(Long id);

    void likeMethod (Long postIdFromLikeVoting, Long postIdFromDisLikeVoting, Long userId);

    List<Post> getSortedPostsByNewest (Integer pageNumber, Integer pageSize, String sortBy);

    void saveVotedPostWithVote(Long userId, Long postIdFromVotingForm, boolean isVotedForLike);

    void editAndSaveVotedPost(Vote existingVote, Long postIdFromVotingForm, boolean isVotedForLike);

}
