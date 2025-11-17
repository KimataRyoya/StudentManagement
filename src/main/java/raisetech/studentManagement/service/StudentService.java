package raisetech.studentManagement.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.searchStudentList();
  }

  public List<StudentCourse> searchStudentCourseList() {
    return repository.searchStudentCourseList();
  }

  @Transactional
  public void registerStudent(StudentDetail studentDetail) {
    repository.registerStudent(studentDetail.getStudent());
    // TODO:コース情報登録も行う。
    for (StudentCourse studentCourse : studentDetail.getStudentCourses()) {
      studentCourse.setStudentsId(studentDetail.getStudent().getId());
      studentCourse.setStart(LocalDate.now());
      studentCourse.setEnd(LocalDate.now().plusYears(1));
      repository.registerStudentCourses(studentCourse);
    }
  }
}
