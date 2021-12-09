package knu.groupproject.taskstatisticsservice.service;

import knu.groupproject.taskstatisticsservice.model.Statistics;
import knu.groupproject.taskstatisticsservice.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
  @Autowired
  private StatisticsRepository statisticsRepository;

  public Statistics getStatisticsByEmail(String userEmail) {
    Statistics statistics = statisticsRepository.findByUserEmail(userEmail);

    if (statistics == null) {
     statistics = new Statistics(0, 0, 0, 0, 0, userEmail);
     return statisticsRepository.save(statistics);
    }

    return statistics;
  }

  public void updateStatistics(Statistics statistics) {
    statisticsRepository.save(statistics);
  }
}
