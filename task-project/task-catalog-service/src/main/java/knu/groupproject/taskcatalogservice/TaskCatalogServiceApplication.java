package knu.groupproject.taskcatalogservice;

import knu.groupproject.taskcatalogservice.model.Priority;
import knu.groupproject.taskcatalogservice.model.Status;
import knu.groupproject.taskcatalogservice.model.Task;
import knu.groupproject.taskcatalogservice.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import static knu.groupproject.taskcatalogservice.resources.TaskCatalogResource.parseDate;

@SpringBootApplication
public class TaskCatalogServiceApplication {
    private final Logger logger = LoggerFactory.getLogger(TaskCatalogServiceApplication.class);

    @Autowired
    private TaskRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(TaskCatalogServiceApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        Task task = new Task("Task2", "To do task", parseDate("2022-02-14"), parseDate("2021-02-14"),
                Status.CREATED, Priority.HIGH);
        logger.info("Saving new customer...");
        repository.save(task);
    }
}
