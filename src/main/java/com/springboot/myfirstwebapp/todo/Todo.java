package com.springboot.myfirstwebapp.todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

//we need to store these in database
//Static List of Todos first then we move to -> Database (h2, mysql)


//To make a Todo table with Todo as entity, JPA enables us to
//map our beans to database


@Entity //this anotaion means this bean is mapped to a database table
//auto configuration of springboot makes tables for all entity present
public class Todo {

    @Id //for every entity we need a primary key
    @GeneratedValuee //to generate this primary key in a sequence
    private int id;
   
    private String username;
    @Size(min = 5, message = "Enter atleast 5 characters description")
    private String description;
    private LocalDate targetDate;
    private boolean done;

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    

    public Todo() {
    }

    public Todo(int id, String username, String description, LocalDate targetDate, boolean done) {
        super();
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo [username=" + username + ", description=" + description + ", targetDate=" + targetDate + ", done="
                + done + ", id=" + id + "]";
    }

}
