package com.in28minutes.rest.services.restfulwebservices.jpa;

import com.in28minutes.rest.services.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> { //User represents the entity that it manages, and Integer represents the type of the Id

}
