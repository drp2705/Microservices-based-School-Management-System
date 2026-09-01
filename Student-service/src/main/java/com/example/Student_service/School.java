package com.example.Student_service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class School {
	private int schoolId;
	private String SchoolName;
	private String Location;
	private String PrincipleName;
}
