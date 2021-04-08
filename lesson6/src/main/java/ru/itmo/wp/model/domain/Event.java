package ru.itmo.wp.model.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Event implements Serializable {
    private long userId;
    private Type type;
    private long id;
    private Date creationTime;

    public Event(long userId, Type type) {
        this.userId = userId;
        this.type = type;
    }

    public Event() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public long getId() {
        return id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public long getUserId() {
        return userId;
    }

    public Type getType() {
        return type;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setType(String name) {
        Type t = null;
        switch (name) {
            case "ENTER":
                t = Type.ENTER;
                break;
            case "LOGOUT":
                t = Type.LOGOUT;
        }
        this.type = t;
    }

    public enum Type {
        ENTER("ENTER"), LOGOUT("LOGOUT");
        private final String value;

        Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
