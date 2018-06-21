package com.example.smitushev.mybookkeeper.Models;

import com.google.gson.annotations.SerializedName;

public class Entry {
    @SerializedName("id")
    private Long id;

    @SerializedName("description")
    private String description;

    @SerializedName("comment")
    private String comment;

    @SerializedName("value")
    private Double value;

    @SerializedName("type")
    private Boolean type;

    public Entry(Long id, String description, String comment, Double value,Boolean type) {
        this.id = id;
        this.description = description;
        this.comment = comment;
        this.value = value;
        this.type = type;
    }

    public Entry(String description, String comment, Double value,Boolean type) {
        this.description = description;
        this.comment = comment;
        this.value = value;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}
