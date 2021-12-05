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
import java.util.List;

@Controller
public class TaskController {
  @Autowired  RestTemplate restTemplate;
  private final Logger logger = LoggerFactory.getLogger(TaskController.class);

  @RequestMapping("")
  public ModelAndView index() {
    return new ModelAndView("index");
  }

  @RequestMapping("/index")
  public ModelAndView index2() {
    return new ModelAndView("index");
  }

  @RequestMapping("/classes")
  @PreAuthorize("hasAuthority('SCOPE_profile')")
  public ResponseEntity<List<TaskClassDto>> listClasses(@AuthenticationPrincipal OidcUser user) {
    String email = user.getEmail();
    logger.warn("Email:" + email);
    logger.info("Get all tasks for user");
    return restTemplate.exchange(
        "http://task-catalog-service/catalog",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<TaskClassDto>>() {});
  }

}
