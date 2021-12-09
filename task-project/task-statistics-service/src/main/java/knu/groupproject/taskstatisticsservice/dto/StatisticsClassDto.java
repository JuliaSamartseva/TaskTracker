package knu.groupproject.taskstatisticsservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatisticsClassDto {
  private Long id;

  private Integer totalTasksCreated;

  private Integer totalTasksDeleted;

  private Integer totalTasksDone;

  private Integer totalTasksDoneByDeadline;

  private Integer totalTasksDoneOverDeadline;

  private String userEmail;
}