package com.in28minutes.rest.services.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 10)
    private String description;
    // If you want to retrieve the details of Post & User in the same query, you are asking for Eager fetch
    //If Eager is chosen, along with Post details, User details will also be fetched
    //If you don't want to fetch User details along with Post, use LAZY
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore //We don't want User to be part of the Json responses for Post bean
    private User user;

    public Post(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
