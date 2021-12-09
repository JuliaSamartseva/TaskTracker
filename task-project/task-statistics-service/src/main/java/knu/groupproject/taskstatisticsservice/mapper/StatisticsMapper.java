package knu.groupproject.taskstatisticsservice.mapper;

import knu.groupproject.taskstatisticsservice.dto.StatisticsClassDto;
import knu.groupproject.taskstatisticsservice.model.Statistics;

public class StatisticsMapper {
  public static StatisticsClassDto mapStatistics(Statistics statistics) {
    StatisticsClassDto dto = new StatisticsClassDto();
    dto.setId(statistics.getId());
    dto.setUserEmail(statistics.getUserEmail());
    dto.setTotalTasksCreated(statistics.getTotalTasksCreated());
    dto.setTotalTasksDeleted(statistics.getTotalTasksDeleted());
    dto.setTotalTasksDone(statistics.getTotalTasksDone());
    dto.setTotalTasksDoneByDeadline(statistics.getTotalTasksDoneByDeadline());
    dto.setTotalTasksDoneOverDeadline(statistics.getTotalTasksDoneOverDeadline());
    return dto;
  }
}
