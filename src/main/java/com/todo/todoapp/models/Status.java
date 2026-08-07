package com.todo.todoapp.models;

public enum Status {
    COMPLETED("Completed"),
    NotStarted("Not Started"),
    InProgress("In Progress");

    private String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
