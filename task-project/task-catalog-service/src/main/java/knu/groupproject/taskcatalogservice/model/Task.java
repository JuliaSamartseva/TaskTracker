package knu.groupproject.taskcatalogservice.model;

import java.util.Date;

public class Task {
    private String name;
    private String description;
    private Date deadline;
    private Date created;
    private Status status;
    private Priority priority;

    public Task() {}

    public Task(String name, String description, Date deadline, Date created, Status status, Priority priority) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.created = created;
        this.status = status;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
