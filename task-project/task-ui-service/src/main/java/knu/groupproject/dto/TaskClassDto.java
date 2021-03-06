package knu.groupproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskClassDto {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("deadline")
  private String deadline;

  private String created;

  @JsonProperty("status")
  private String status;

  @JsonProperty("priority")
  private String priority;

  private String userEmail;
}
