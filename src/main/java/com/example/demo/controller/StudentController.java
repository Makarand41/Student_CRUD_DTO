package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@PostMapping("/add")
	public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
	    StudentDto newStudentDto = studentService.createStudent1(studentDto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(newStudentDto);
	}

	
	@GetMapping("/get")
	public ResponseEntity<List<StudentDto>> getAll() {
	    List<StudentDto> students = studentService.getStudents1();
	    return ResponseEntity.ok(students);  
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<StudentDto> getStudentByID (@PathVariable("id") long iddd)
	{
		StudentDto studentDto=studentService.getStudentById(iddd);
		 return new ResponseEntity<>(studentDto, HttpStatus.OK);	
		
	}
	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudentByID (@PathVariable("id") long iddd)
	{
		studentService.deleteStudentbyId(iddd);
		 return new ResponseEntity<>("deleted"+iddd, HttpStatus.OK);	
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") long id, @RequestBody Student updatedStudent) {
	    StudentDto updatedStudentDto = studentService.updateStudent(id, updatedStudent);
	    return ResponseEntity.ok(updatedStudentDto);
	}

	
	 @GetMapping("/count")
	    public ResponseEntity<Long> countStudents() {
	        long count = studentService.countStudents();
	        return new ResponseEntity<>(count, HttpStatus.OK);
	    }
	 
	 @GetMapping("/exists/{id}")
	    public ResponseEntity<Boolean> existsById(@PathVariable("id") Long studentId) {
	        boolean exists = studentService.existsById(studentId);
	        return new ResponseEntity<>(exists, HttpStatus.OK);
	    }

	 @DeleteMapping("/deleteAll")
	 public ResponseEntity<String> deleteAllStudents() {
	     studentService.deleteAllStudents();
	     return new ResponseEntity<>("All students deleted successfully!", HttpStatus.OK);
	 }

	

	
}
