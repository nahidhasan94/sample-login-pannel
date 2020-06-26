package com.example.loginSystem.model;
import com.example.loginSystem.enums.Status;
import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;

public class User{
    @Id
    private ObjectId id;
    private String username;
    private String password;
    private Status status;

    public User() {
    }

    public User(ObjectId id, String username, String password, Status status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
