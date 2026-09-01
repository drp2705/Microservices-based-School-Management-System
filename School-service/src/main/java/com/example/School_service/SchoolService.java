package com.example.School_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

	@Autowired
	private SchoolRepository schoolRepository;
	
	//add school data
	public School addSchool(School school) {
		return schoolRepository.saveAndFlush(school);
	}
	
	//show all the data
	public List<School> showAlldata(){
		return schoolRepository.findAll();
	}
	
	//show data by id
	public School findByid(Long id) {
		return schoolRepository.findById(id).orElse(null);
	}
}
