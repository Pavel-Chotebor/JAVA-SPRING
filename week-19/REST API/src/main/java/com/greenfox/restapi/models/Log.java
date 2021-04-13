package com.greenfox.restapi.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "logs")
public class Log {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createdAt;

    @Column
    private String endpoint;

    @Column
    private String data;


    public Log() {
        createdAt= new Date();
    }

    public Log(String endpoint, String data) {
        this.id = id;
        this.endpoint = endpoint;
        this.data = data;
        createdAt= new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}


















