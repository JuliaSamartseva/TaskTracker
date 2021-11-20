package knu.groupproject.controller;

import knu.groupproject.dto.TaskClassDto;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/")
public class TaskController {
  private final RestTemplate restTemplate;
  private final String serviceHost;

  public TaskController(RestTemplate restTemplate, @Value("${service.host}") String serviceHost) {
    this.restTemplate = restTemplate;
    this.serviceHost = serviceHost;
  }

  @RequestMapping("")
  public ModelAndView index() {
    return new ModelAndView("index");
  }

  @GetMapping("/classes")
  @PreAuthorize("hasAuthority('SCOPE_profile')")
  public ResponseEntity<List<TaskClassDto>> listClasses(){
    return restTemplate
            .exchange("http://task-catalog-service/classes", HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<TaskClassDto>>() {});
  }
}
