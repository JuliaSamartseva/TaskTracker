package knu.groupproject.taskstatisticsservice.model;

import org.hibernate.mapping.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Statistics {
  @Id
  @GeneratedValue
  private long id;

  @Column(name = "totalTasksCreated")
  private Integer totalTasksCreated;

  @Column(name = "totalTasksDeleted")
  private Integer totalTasksDeleted;

  @Column(name = "totalTasksDone")
  private Integer totalTasksDone;

  @Column(name = "totalTasksDoneByDeadline")
  private Integer totalTasksDoneByDeadline;

  @Column(name = "totalTasksDoneOverDeadline")
  private Integer totalTasksDoneOverDeadline;

  @Column(name = "userEmail")
  private String userEmail;

  public Statistics() {}

  public Statistics(Integer totalTasksCreated, Integer totalTasksDeleted, Integer totalTasksDone, Integer totalTasksDoneByDeadline, Integer totalTasksDoneOverDeadline, String userEmail) {
    this.totalTasksCreated = totalTasksCreated;
    this.totalTasksDeleted = totalTasksDeleted;
    this.totalTasksDone = totalTasksDone;
    this.totalTasksDoneByDeadline = totalTasksDoneByDeadline;
    this.totalTasksDoneOverDeadline = totalTasksDoneOverDeadline;
    this.userEmail = userEmail;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Integer getTotalTasksCreated() {
    return totalTasksCreated;
  }

  public void setTotalTasksCreated(Integer totalTasksCreated) {
    this.totalTasksCreated = totalTasksCreated;
  }

  public Integer getTotalTasksDeleted() {
    return totalTasksDeleted;
  }

  public void setTotalTasksDeleted(Integer totalTasksDeleted) {
    this.totalTasksDeleted = totalTasksDeleted;
  }

  public Integer getTotalTasksDone() {
    return totalTasksDone;
  }

  public void setTotalTasksDone(Integer totalTasksDone) {
    this.totalTasksDone = totalTasksDone;
  }

  public Integer getTotalTasksDoneByDeadline() {
    return totalTasksDoneByDeadline;
  }

  public void setTotalTasksDoneByDeadline(Integer totalTasksDoneByDeadline) {
    this.totalTasksDoneByDeadline = totalTasksDoneByDeadline;
  }

  public Integer getTotalTasksDoneOverDeadline() {
    return totalTasksDoneOverDeadline;
  }

  public void setTotalTasksDoneOverDeadline(Integer totalTasksDoneOverDeadline) {
    this.totalTasksDoneOverDeadline = totalTasksDoneOverDeadline;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }
}
