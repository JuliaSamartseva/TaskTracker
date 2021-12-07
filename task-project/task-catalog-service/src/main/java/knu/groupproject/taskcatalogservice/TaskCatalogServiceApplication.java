package knu.groupproject.taskcatalogservice;

import knu.groupproject.taskcatalogservice.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableEurekaClient
public class TaskCatalogServiceApplication {
  private final Logger logger = LoggerFactory.getLogger(TaskCatalogServiceApplication.class);

  @Autowired private TaskRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(TaskCatalogServiceApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void runAfterStartup() {}
}
