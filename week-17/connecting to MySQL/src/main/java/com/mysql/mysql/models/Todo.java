package com.mysql.mysql.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  //is auto-generated one by one
    private Long id;

    @Column
    private String title;

    @Column
    private boolean urgent;

    @Column
    private boolean done;

    @Column
    private String createDate= new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date());

    @Column
    private String dueDate= new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date());

    @Column
    private String description;

    @ManyToOne
    @JoinColumn (name="assignee_ID")
    private Assignee assignee;



    public Todo() {
    }

    public Todo(String title) {
        this.title = title;
    }

    public Todo(String title, boolean urgent, boolean done, String description) {
        // this.id = id;
        this.title = title;
        this.urgent =urgent;
        this.done = done;
        this.createDate= createDate;
        //this.dueDate = dueDate;
        this.description = description;
    }



    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getUrgent() {
        return urgent;
    }

    public void setUrgent(Boolean urgent) {
        this.urgent = urgent;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}