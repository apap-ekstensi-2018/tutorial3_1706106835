package com.example.tutorial3.service;

import java.util.*;

import com.example.tutorial3.model.StudentModel;

public class InMemoryStudentService implements StudentService{
	private static List<StudentModel> studentList = new ArrayList<StudentModel>();
	
	public StudentModel selectStudent(String npm) {
		Comparator<StudentModel> s = new Comparator<StudentModel>() {
			public int compare(StudentModel s1, StudentModel s2) {
				return s1.getNPM().compareTo(s2.getNPM());
			}
		};
		
		Collections.sort(studentList,s);
		int index = Collections.binarySearch(studentList, new StudentModel(npm,null,0), s);
		
		if(index < 0)
			return null;
		
		return studentList.get(index);
	}
	
	public List<StudentModel> selectAllStudents(){
		return studentList;
	}
	
	public void addStudent(StudentModel student) {
		studentList.add(student);
	}
	
	public void deleteStudent(StudentModel student) {
		studentList.remove(student);
	}
}