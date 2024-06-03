package com.example.jeancraft.Entity;

public class User {
    private String id;
    private String username;
    private String role;
    private String email;
    private String phonenumber;
    private String token;

    public User() {
    }

    public User(String id, String username, String role, String email, String phonenumber, String token) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.email = email;
        this.phonenumber = phonenumber;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
