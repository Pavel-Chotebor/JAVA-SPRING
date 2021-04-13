package com.egreenfox.trialexam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity (name = "secretNumbers")
public class SecretNumber {

    @Id
    @Column
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String number;

    @OneToOne (mappedBy = "secretNumber")
    @JsonIgnore
    private Link link;

    public SecretNumber(String number) {
        this.number = number;
    }

    public SecretNumber() {
    }

    public Integer getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
