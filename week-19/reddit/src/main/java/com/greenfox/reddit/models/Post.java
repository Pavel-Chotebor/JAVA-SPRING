package com.greenfox.reddit.models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name="posts")
public class Post {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String URL;
    private int rating;

    @ManyToOne
    @JoinColumn (name="user_id")
    private User user;

    @OneToMany (mappedBy = "post")
    private List <Vote> votes = new ArrayList<>();


    public Post() {

    }
    public Post(String title, String URL) {
        this.title = title;
        this.URL = URL;
        rating = 0;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
