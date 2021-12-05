package knu.groupproject.taskcatalogservice.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task")
public class Task {
  @Id @GeneratedValue private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "deadline")
  private Date deadline;

  @Column(name = "created")
  private Date created;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private Status status;

  @Column(name = "priority")
  @Enumerated(EnumType.STRING)
  private Priority priority;

  @Column(name = "userEmail")
  private String userEmail;

  public Task() {}

  public Task(
      String name,
      String description,
      Date deadline,
      Date created,
      Status status,
      Priority priority,
      String userEmail) {
    this.name = name;
    this.description = description;
    this.deadline = deadline;
    this.created = created;
    this.status = status;
    this.priority = priority;
    this.userEmail = userEmail;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }
}
