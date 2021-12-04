package knu.groupproject.taskcatalogservice.resources;

import knu.groupproject.taskcatalogservice.model.Priority;
import knu.groupproject.taskcatalogservice.model.Status;
import knu.groupproject.taskcatalogservice.model.Task;
import knu.groupproject.taskcatalogservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class TaskCatalogResource {
    @Autowired
    private TaskService service;

    @GetMapping("/{userId}")
    public List<Task> getCatalog(@PathVariable("userId") String userId) {
        return Collections.singletonList(
                new Task("Task1", "To do task", parseDate("2022-02-14"), parseDate("2021-02-14"),
                        Status.CREATED, Priority.HIGH)
        );
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
