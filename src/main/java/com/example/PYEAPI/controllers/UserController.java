package com.example.PYEAPI.controllers;

import com.example.PYEAPI.modelo.User;
import com.example.PYEAPI.repositories.UserRepository;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    private final UserRepository userrepository;

    public UserController(UserRepository userrepository) {
        super();
        this.userrepository = userrepository;
    }

    @GetMapping(value = "")
    public List<User> getAll() {
        return userrepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable String id) {
        Optional<User> u = userrepository.findById(id);
        if(u.isPresent()) return u.get();
        throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
    }

    @PostMapping(value = "/login")
    User Login(@RequestBody User user) {
        Optional<User> u = userrepository.findByEmail(user.getEmail());
        if (u.isPresent()) {
            if (user.getPassword().equals(u.get().getPassword())) return u.get();
            throw new ResponseStatusException(UNAUTHORIZED, "Incorrect user data");
        } else {
            throw new ResponseStatusException(NOT_FOUND, "User does not exist");
        }
    }

    @PostMapping(value = "/register")
    User Register(@RequestBody User user) {
        return  userrepository.save(user);
    }
}
