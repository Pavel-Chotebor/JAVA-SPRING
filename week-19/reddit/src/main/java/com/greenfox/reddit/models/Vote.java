package com.greenfox.reddit.models;
import javax.persistence.*;

@Entity
@Table (name = "votes")
public class Vote {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isVotedForLike;
    private Long votedByUserId;

    @ManyToOne
    @JoinColumn (name = "post_id")
    private Post post;

    public Vote() {
    }

    public Vote(boolean isVotedForLike, Long votedByUserId) {
        this.isVotedForLike = isVotedForLike;
        this.votedByUserId = votedByUserId;
    }

    public Long getId() {
        return id;
    }

    public boolean isVotedForLike() {
        return isVotedForLike;
    }

    public void setVotedForLike(boolean votedForLike) {
        isVotedForLike = votedForLike;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getVotedByUserId() {
        return votedByUserId;
    }

    public void setVotedByUserId(Long votedByUserId) {
        this.votedByUserId = votedByUserId;
    }
}