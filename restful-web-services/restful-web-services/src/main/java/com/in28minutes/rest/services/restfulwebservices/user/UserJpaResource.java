package com.in28minutes.rest.services.restfulwebservices.user;

import com.in28minutes.rest.services.restfulwebservices.jpa.PostRepository;
import com.in28minutes.rest.services.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    private UserRepository repository;

    private PostRepository postRepository;
    public UserJpaResource(UserRepository repository, PostRepository postRepository){
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @GetMapping("/name")
    public String getName(){
        return "play Java";
    }
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
       return repository.findAll();
    }

    //In addition to returning data, let us return a link to the Users back
    @GetMapping("/jpa/users/{id}")
    //public User retrieveUser(@PathVariable int id){
    public EntityModel<User> retrieveUser(@PathVariable int id){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
        //return user;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        repository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);
        return user.get().getPosts();
    }
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
       User savedUser = repository.save(user);

       URI location = ServletUriComponentsBuilder.fromCurrentRequest()//URI of the current method + path /id)
               .path("/{id}")
               .buildAndExpand(savedUser.getId())//replace {id} with the id of the created user
               .toUri(); //convert it to Uri and return it back
        return ResponseEntity.created(location).build(); //returns 201 status and location as part of response entity
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostsForUser(@PathVariable int id, @Valid @RequestBody Post post){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        post.setUser(user.get());
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()//URI of the current method + path /id)
                .path("/{id}")
                .buildAndExpand(savedPost.getId())//replace {id} with the id of the created user
                .toUri(); //convert it to Uri and return it back
        return ResponseEntity.created(location).build();
    }
}
