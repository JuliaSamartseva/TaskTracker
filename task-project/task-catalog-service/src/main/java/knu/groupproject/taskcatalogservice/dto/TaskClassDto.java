package knu.groupproject.taskcatalogservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskClassDto {
  private Long id;
  private String name;
  private String description;
  private String deadline;
  private String created;
  private String status;
  private String priority;
  private String userEmail;
}
