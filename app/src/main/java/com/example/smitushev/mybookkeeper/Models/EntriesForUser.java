package com.example.smitushev.mybookkeeper.Models;

public class EntriesForUser {
    private Long id;
    private Long userId;
    private Long entryId;

    public EntriesForUser(Long id, Long userId, Long entryId) {
        this.id = id;
        this.userId = userId;
        this.entryId = entryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }
}
