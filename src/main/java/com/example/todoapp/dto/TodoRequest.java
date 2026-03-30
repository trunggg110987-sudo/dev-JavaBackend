package com.example.todoapp.dto;

import jakarta.validation.constraints.NotBlank;

public class TodoRequest {

    @NotBlank(message = "title connot be empty")
    private String title;

    @NotBlank(message = "description cannot be empty")
    private String description;

    private boolean completed;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public @NotBlank(message = "description cannot be empty") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "description cannot be empty") String description) {
        this.description = description;
    }

    public @NotBlank(message = "title connot be empty") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "title connot be empty") String title) {
        this.title = title;
    }
}
