package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepo;


@Service
public class StudentService {
	
	@Autowired
	StudentRepo studentRepo;

	@Autowired
	private ModelMapper modelMapper;

	public StudentDto createStudent1(StudentDto studentDto) {
	    // Convert DTO to Entity
	    Student student = modelMapper.map(studentDto, Student.class);
	    // Save Entity
	    Student savedStudent = studentRepo.save(student);
	    // Convert back to DTO
	    return modelMapper.map(savedStudent, StudentDto.class);
	}

	
	public List<StudentDto> getStudents1() {
	    List<Student> students = studentRepo.findAll();
	    return students.stream()
	                   .map(student -> modelMapper.map(student, StudentDto.class))
	                   .toList();
	}

	
	
	 public StudentDto getStudentById(Long studentId) {
	        
	        Student student = studentRepo.findById(studentId).orElse(null);
	        return modelMapper.map(student, StudentDto.class);
	    }

	 
	 public void deleteStudentbyId(Long studentId)
	 {
		 studentRepo.deleteById(studentId);
	 }
	
	 public StudentDto updateStudent(long id, StudentDto updatedStudentDto) {
		 Student existingStudent = studentRepo.findById(id).orElse(null);

	    // Update fields from DTO
	    existingStudent.setName(updatedStudentDto.getName());
	    existingStudent.setDept(updatedStudentDto.getDept());
	    existingStudent.setDob(updatedStudentDto.getDob());

	    Student updated = studentRepo.save(existingStudent);
	    return modelMapper.map(updated, StudentDto.class);
	}



	 public long countStudents() {
	        return studentRepo.count();
	    }
	 
	 
	 public boolean existsById(Long studentId) {
	        return studentRepo.existsById(studentId);
	    }


	 public void deleteAllStudents() {
		    studentRepo.deleteAll();
		}


}
