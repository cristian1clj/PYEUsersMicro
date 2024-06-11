package com.example.PYEAPI.repositories;

import com.example.PYEAPI.modelo.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository <User, String> {
    Optional<User> findByEmail(String email);
}
