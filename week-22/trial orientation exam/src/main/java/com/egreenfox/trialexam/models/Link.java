package com.egreenfox.trialexam.models;

import javax.persistence.*;

@Entity (name = "links")
public class Link {

    @Column
    private String url;
    @Column
    private String alias;
    @Column
    private int hitCount;


    @Id
    @Column
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "secretNumber_id")
    private SecretNumber secretNumber;

    public Link(String url, String alias, Integer hitCount) {
        this.url = url;
        this.alias = alias;
        this.hitCount = hitCount;
    }

    public Link() {
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getId() {
        return id;
    }

    public SecretNumber getSecretNumber() {
        return secretNumber;
    }

    public void setSecretNumber(SecretNumber secretNumber) {
        this.secretNumber = secretNumber;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }
}
