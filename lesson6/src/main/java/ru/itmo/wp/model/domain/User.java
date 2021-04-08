package ru.itmo.wp.model.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String login;
    private String email;
    private long id;
    private Date creationTime;


    public long getId() {
        return id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
