package raisetech.studentManagement.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCourse {

  private String id;
  private String studentsId;
  private String courses;
  private LocalDate start;
  private LocalDate end;

}
