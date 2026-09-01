package com.example.School_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/School")
public class SchoolController {

	@Autowired
	private SchoolService schoolService;
	@PostMapping
	public ResponseEntity<School> addingSchool(@RequestBody School school){
		School addSchool= schoolService.addSchool(school);
		return ResponseEntity.ok(addSchool);
	}
	
	@GetMapping
	public List<School> showData(){
		return schoolService.showAlldata();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<School> fetchByid(@PathVariable Long id){
		School school= schoolService.findByid(id);
		if(school!=null) {
			return ResponseEntity.ok(school);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
