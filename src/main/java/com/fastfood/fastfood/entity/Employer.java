package com.fastfood.fastfood.entity;

import java.util.Date;

public class Employer {
    private int id;
    private Role role;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private Date created;
    private Date deactivated;
    private int author;
    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Date deactivated) {
        this.deactivated = deactivated;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public enum Role {

        ADMIN("/menu.xhtml"),
        CASH_REGISTER("/menu.xhtml"),
        CHEF_KITCHEN("/kitchen.xhtml"),
        KITCHEN("/kitchen.xhtml"),
        DEFAULT("/menu.xhtml");

        private final String workSpace;

        Role(String workSpace) {
            this.workSpace = workSpace;
        }

        public String getWorkSpace() {
            return workSpace;
        }
    }
}
