package com.example.Student_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/Student")
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		Student s = studentService.AddStudent(student);
		return ResponseEntity.ok(s);
	}
	
	@GetMapping
	public List<Student> showall(){
		return studentService.showAlldata();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> fetchByid(@PathVariable long id)
	{
		return studentService.fetchStudentById(id);
	}
	
}
