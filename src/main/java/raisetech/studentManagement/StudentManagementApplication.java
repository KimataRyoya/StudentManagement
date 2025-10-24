package raisetech.studentManagement;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

	private String name = "Kubo Taito";
	private int age = 50;

	private Map<String,Integer> studentMap = new HashMap<>();

	public void studentMapPut(){
		studentMap.put("KIMATA",29);
		studentMap.put("HINA",22);
	}

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
	@GetMapping("/studentMap")
	public Map<String, Integer> getStudentMap(){
		studentMapPut();
		return studentMap;
	}
	@PostMapping("/studentMap")
	public void setStudentMap(@RequestParam String name,@RequestParam Integer age){
		studentMap.put(name, age);
		
	}
}
