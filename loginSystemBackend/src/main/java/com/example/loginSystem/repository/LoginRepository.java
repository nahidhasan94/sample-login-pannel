package com.example.loginSystem.repository;

import com.example.loginSystem.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends MongoRepository<User, String> {
     User findByUsername(String username);
     User findByPassword(String password);
}
