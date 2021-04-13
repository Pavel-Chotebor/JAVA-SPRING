package com.greenfox.reddit.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name="users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany (mappedBy = "user")
    private List <Post> posts = new ArrayList<>();

    public User() {
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
