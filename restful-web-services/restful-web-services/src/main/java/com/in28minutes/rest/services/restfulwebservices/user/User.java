package com.in28minutes.rest.services.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

//We want JPA to manage this, hence using @Entity
@Entity(name = "user_details") //"user" is a keyword in h2
public class User {
    @Id
    @GeneratedValue //We want this id field to be generated
    private Integer id;
    @Size(min=2, message = "Name should have atleast 2 characters")
    //@JsonProperty("user_name")
    private String name;
    @Past(message = "Birth Date should be in the past")
    //@JsonProperty("birth_date")
    private LocalDate birthDate;
    @OneToMany(mappedBy = "user") //Single User can have many posts
    @JsonIgnore //We don't want Post to be part of the Json responses for User bean
    private List<Post> posts;
    protected User(){

    }
    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
    public List<Post> getPosts() {
        return posts;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
