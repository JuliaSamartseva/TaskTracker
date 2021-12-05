package knu.groupproject.taskcatalogservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
  private Long userId;
}
