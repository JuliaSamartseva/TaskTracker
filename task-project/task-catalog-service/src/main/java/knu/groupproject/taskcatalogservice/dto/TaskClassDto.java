package knu.groupproject.taskcatalogservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
