package knu.groupproject.controller;

import knu.groupproject.dto.TaskClassDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.config.Task;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TaskController {
  @Autowired RestTemplate restTemplate;
  private final Logger logger = LoggerFactory.getLogger(TaskController.class);

  @RequestMapping("")
  public ModelAndView index() {
    return new ModelAndView("index");
  }

  @RequestMapping("/add-task")
  public ModelAndView addTask() {
    return new ModelAndView("add-task");
  }

  @RequestMapping("/classes")
  @PreAuthorize("hasAuthority('SCOPE_profile')")
  public ResponseEntity<List<TaskClassDto>> listCatalog(@AuthenticationPrincipal OidcUser user) {
    String email = user.getEmail();
    logger.info("Email:" + email);
    logger.info("Get all tasks for user");
    return restTemplate.exchange(
        "http://task-catalog-service/catalog/" + email,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<TaskClassDto>>() {});
  }

  @RequestMapping(value = "/tasks/add-task-post", method = RequestMethod.POST)
  @PreAuthorize("hasAuthority('SCOPE_profile')")
  public ResponseEntity<?> addTask(
      @AuthenticationPrincipal OidcUser user, @RequestBody TaskClassDto task) {
    logger.info("Add task for user " + task.getName());
    String email = user.getEmail();
    task.setUserEmail(email);

    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
    Date date = new Date();
    task.setCreated(formatter.format(date));

    task.setStatus("CREATED");

    HttpEntity<TaskClassDto> request = new HttpEntity<>(task);
    return restTemplate.exchange(
        "http://task-catalog-service/catalog/add-task",
        HttpMethod.POST,
        request,
        TaskClassDto.class);
  }

  @RequestMapping(value = "/tasks/delete-task/{id}", method = RequestMethod.DELETE)
  @PreAuthorize("hasAuthority('SCOPE_profile')")
  public ResponseEntity<?> deleteTask(@PathVariable Long id){
    return restTemplate.exchange(
            "http://task-catalog-service/catalog/delete-task/" + id,
            HttpMethod.DELETE,
            null,
            Void.class);
  }
}
