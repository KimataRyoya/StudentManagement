package raisetech.studentManagement.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import raisetech.studentManagement.controller.converter.StudentConverter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.service.StudentService;

@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  /**
   * 受講生情報一覧を表示するメソッド
   *
   * @param model
   * @return　受講生情報一覧
   */
  @GetMapping("/studentList")
  public String getStudentList(Model model) {
    List<Student> students = service.searchStudentList();
    List<StudentCourse> studentCourses = service.searchStudentCourseList();

    model.addAttribute("studentList", converter.convertStudentDetail(students, studentCourses));
    return "studentList";
  }

  /**
   * 受講生登録画面を表示するメソッド
   *
   * @param model
   * @return　受講生登録画面
   */
  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudentCourses(Arrays.asList(new StudentCourse()));
    model.addAttribute("studentDetail", studentDetail);
    return "registerStudent";
  }

  /**
   * 受講生登録を行うメソッド
   *
   * @param studentDetail
   * @param result
   * @return　受講生情報一覧
   */
  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if (result.hasErrors()) {
      return "registerStudent";
    }
    service.registerStudent(studentDetail);
    return "redirect:/studentList";
  }

  /**
   * IDに紐づいた受講生情報の詳細を表示するメソッド
   *
   * @param id
   * @param model
   * @return　IDに紐づいた受講生情報詳細
   */
  @GetMapping("/updateStudent/{id}")
  public String updateStudent(@PathVariable String id, Model model) {
    StudentDetail studentDetail = service.findById(id);
    model.addAttribute("studentDetail", studentDetail);
    return "updateStudent";
  }

  /**
   * 受講生情報の更新を行うメソッド
   *
   * @param studentDetail
   * @param result
   * @return　受講生情報一覧
   */
  @PostMapping("/updateStudent/{id}")
  public String updateStudent(@PathVariable String id,
      @ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if (result.hasErrors()) {
      return "updateStudent";
    }
    service.updateStudent(studentDetail);
    return "redirect:/studentList";
  }
}
