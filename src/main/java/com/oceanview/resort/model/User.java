package com.oceanview.resort.model;

public class User {
    private int id;
    private String name;
    private String username;
    private String passwordHash;
    private String email;
    private String role;

    public User() {
    }

    public User(int id, String name, String username, String passwordHash, String email, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isManager() {
        String r = role == null ? "" : role.trim();
        return r.equalsIgnoreCase("MANAGER") || r.equalsIgnoreCase("ADMIN");
    }

    public boolean isReceptionist() {
        String r = role == null ? "" : role.trim();
        return r.equalsIgnoreCase("RECEPTIONIST") || r.equalsIgnoreCase("STAFF");
    }
}
