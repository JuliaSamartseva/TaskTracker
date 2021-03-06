package knu.groupproject.controller;

import knu.groupproject.dto.StatisticsClassDto;
import knu.groupproject.dto.TaskClassDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

  @RequestMapping("/statistics")
  public ModelAndView statistics() {
    return new ModelAndView("general-statistics");
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

  @RequestMapping("/classes/{sort}")
  @PreAuthorize("hasAuthority('SCOPE_profile')")
  public ResponseEntity<List<TaskClassDto>> listSortedCatalog(@AuthenticationPrincipal OidcUser user, @PathVariable String sort) {
    String email = user.getEmail();
    logger.info("Email:" + email);
    logger.info("Get sorted tasks for user");
    return restTemplate.exchange(
            "http://task-catalog-service/catalog/" + email + "/" + sort,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<TaskClassDto>>() {});
  }

  @RequestMapping("/get-statistics")
  @PreAuthorize("hasAuthority('SCOPE_profile')")
  public ResponseEntity<StatisticsClassDto> listStatistics(@AuthenticationPrincipal OidcUser user) {
    String email = user.getEmail();
    logger.info("Email:" + email);
    logger.info("Get general statistics for user");
    return restTemplate.exchange(
            "http://task-statistics-service/statistics/" + email,
            HttpMethod.GET,
            null,
            StatisticsClassDto.class);
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

  @RequestMapping(value = "/statistics/created_tasks", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('SCOPE_profile')")
  public ResponseEntity<?> updateCreatedTasks(@AuthenticationPrincipal OidcUser user) {
    String email = user.getEmail();
    return restTemplate.exchange(
            "http://task-statistics-service/statistics/created_tasks/" + email,
            HttpMethod.GET,
            null,
            Void.class);
  }

  @RequestMapping(value = "/statistics/done_tasks", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('SCOPE_profile')")
  public ResponseEntity<?> updateDoneTasks(@AuthenticationPrincipal OidcUser user) {
    String email = user.getEmail();
    return restTemplate.exchange(
            "http://task-statistics-service/statistics/done_tasks/" + email,
            HttpMethod.GET,
            null,
            Void.class);
  }

  @RequestMapping(value = "/statistics/deleted_tasks", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('SCOPE_profile')")
  public ResponseEntity<?> updateDeletedTasks(@AuthenticationPrincipal OidcUser user) {
    String email = user.getEmail();
    return restTemplate.exchange(
            "http://task-statistics-service/statistics/deleted_tasks/" + email,
            HttpMethod.GET,
            null,
            Void.class);
  }

  @RequestMapping(value = "/statistics/by_deadline", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('SCOPE_profile')")
  public ResponseEntity<?> updateTotalTasksDoneByDeadline(@AuthenticationPrincipal OidcUser user) {
    String email = user.getEmail();
    return restTemplate.exchange(
            "http://task-statistics-service/statistics/by_deadline/" + email,
            HttpMethod.GET,
            null,
            Void.class);
  }

  @RequestMapping(value = "/statistics/over_deadline", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('SCOPE_profile')")
  public ResponseEntity<?> updateTotalTasksDoneOverDeadline(@AuthenticationPrincipal OidcUser user) {
    String email = user.getEmail();
    return restTemplate.exchange(
            "http://task-statistics-service/statistics/over_deadline/" + email,
            HttpMethod.GET,
            null,
            Void.class);
  }
  
  @RequestMapping(value = "/tasks/edit-task/{id}", method = RequestMethod.POST)
  @PreAuthorize("hasAuthority('SCOPE_profile')")
  public ResponseEntity<?> editTask(@PathVariable Long id, @RequestBody TaskClassDto task){
    logger.info("UI service: got task dto: "+ task.getName());
    HttpEntity<TaskClassDto> request = new HttpEntity<>(task);
    return restTemplate.exchange(
            "http://task-catalog-service/catalog/edit-task/"+id,
            HttpMethod.POST,
            request,
            TaskClassDto.class);
  }
}
