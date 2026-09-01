package com.example.Student_service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Student AddStudent(Student student) {
		return studentRepository.saveAndFlush(student);
	}
	
	//show all data
	public List<Student> showAlldata(){
		return studentRepository.findAll();
	}
	
	//show fetch data
	public ResponseEntity<?> fetchStudentById(Long id){
		Optional<Student> student = studentRepository.findById(id);
		if(student.isPresent()) {
			School school = restTemplate.getForObject("http://SCHOOL-SERVICE/School/" + student.get().getSchoolId(), School.class);
			StudentResponse studentResponse = new StudentResponse(
					student.get().getId(),
					student.get().getStudentName(),
					student.get().getAge(),
					student.get().getGender(),
					school
					);
			return new ResponseEntity<>(studentResponse,HttpStatus.OK);	
		}else {
			return new ResponseEntity<>("No Student Found", HttpStatus.NOT_FOUND);
		}
	}
}
