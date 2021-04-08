package ru.itmo.wp.model.domain;

import java.io.Serializable;
import java.util.Date;


public class Talk implements Serializable  {
    private long sourceUserId;
    private long targetUserId;
    private String text;
    private long id;
    private Date creationTime;

    public Talk(long sourceUserId, long tagretUserId, String text) {
        this.sourceUserId = sourceUserId;
        this.targetUserId = tagretUserId;
        this.text = text;
    }

    public Talk() {
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

    public void setSourceUserId(long sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    public void setTargetUserId(long tagretUserId) {
        this.targetUserId = tagretUserId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getSourceUserId() {
        return sourceUserId;
    }

    public long getTargetUserId() {
        return targetUserId;
    }

    public String getText() {
        return text;
    }
}
