package knu.groupproject.taskstatisticsservice.resources;

import knu.groupproject.taskstatisticsservice.dto.StatisticsClassDto;
import knu.groupproject.taskstatisticsservice.mapper.StatisticsMapper;
import knu.groupproject.taskstatisticsservice.model.Statistics;
import knu.groupproject.taskstatisticsservice.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsResource {
  private final Logger logger = LoggerFactory.getLogger(StatisticsResource.class);
  @Autowired private StatisticsService statisticsService;

  @GetMapping("/{email}")
  public StatisticsClassDto getStatistics(@PathVariable String email) {
    logger.info("Getting statistics");
    return StatisticsMapper.mapStatistics(statisticsService.getStatisticsByEmail(email));
  }

  @GetMapping("/created_tasks/{email}")
  public void updateCreatedTasks(@PathVariable String email) {
    logger.info("Update created task number");
    Statistics statistics = statisticsService.getStatisticsByEmail(email);
    statistics.setTotalTasksCreated(statistics.getTotalTasksCreated() + 1);
    statisticsService.updateStatistics(statistics);
  }

  @GetMapping("/done_tasks/{email}")
  public void updateDoneTasks(@PathVariable String email) {
    logger.info("Update done task number");
    Statistics statistics = statisticsService.getStatisticsByEmail(email);
    statistics.setTotalTasksDone(statistics.getTotalTasksDone() + 1);
    statisticsService.updateStatistics(statistics);
  }

  @GetMapping("/deleted_tasks/{email}")
  public void updateDeletedTasks(@PathVariable String email) {
    logger.info("Update deleted task number");
    Statistics statistics = statisticsService.getStatisticsByEmail(email);
    statistics.setTotalTasksDeleted(statistics.getTotalTasksDeleted() + 1);
    statisticsService.updateStatistics(statistics);
  }

  @GetMapping("/by_deadline/{email}")
  public void updateTotalTasksDoneByDeadline(@PathVariable String email) {
    logger.info("Update total tasks done by deadline number");
    Statistics statistics = statisticsService.getStatisticsByEmail(email);
    statistics.setTotalTasksDoneByDeadline(statistics.getTotalTasksDoneByDeadline() + 1);
    statisticsService.updateStatistics(statistics);
  }

  @GetMapping("/over_deadline/{email}")
  public void updateTotalTasksDoneOverDeadline(@PathVariable String email) {
    logger.info("Update total tasks done by deadline number");
    Statistics statistics = statisticsService.getStatisticsByEmail(email);
    statistics.setTotalTasksDoneOverDeadline(statistics.getTotalTasksDoneOverDeadline() + 1);
    statisticsService.updateStatistics(statistics);
  }

}
