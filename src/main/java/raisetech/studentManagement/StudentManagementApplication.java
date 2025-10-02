package raisetech.studentManagement;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

	private String name = "Kubo Taito";
	private int age = 50;


	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
		
	}
	@GetMapping("/studentInfo")
	public String getStudentInfo() {
		return name + " " + age + "歳";
	}
	@PostMapping("/studentInfo")
	public void setStudentInfo(String name,int age){
		this.name = name;
		this.age = age;
	}
	@PostMapping("/studentName")
	public void setStudentName(String name){
		this.name = name;
	}
}
