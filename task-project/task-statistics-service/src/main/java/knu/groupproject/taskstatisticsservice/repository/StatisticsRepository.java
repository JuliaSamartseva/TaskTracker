package knu.groupproject.taskstatisticsservice.repository;

import knu.groupproject.taskstatisticsservice.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
  Statistics findByUserEmail(String userEmail);
}
