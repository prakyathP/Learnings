package com.in28minutes.rest.services.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int count = 0;

    static {
        users.add(new User(++count,"Prak", LocalDate.now().minusYears(28)));
        users.add(new User(++count,"Praj", LocalDate.now().minusYears(28)));
        users.add(new User(++count,"Sow", LocalDate.now().minusYears(33)));
    }
    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(++count);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
        //return users.stream().filter(predicate).findFirst().orElse(null);
    }
}
