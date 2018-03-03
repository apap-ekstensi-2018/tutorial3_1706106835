package com.example.tutorial3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.tutorial3.service.InMemoryStudentService;
import com.example.tutorial3.service.StudentService;
import com.example.tutorial3.model.StudentModel;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
	private final StudentService studentService;
	
	public StudentController() {
		studentService = new InMemoryStudentService();
	}
	
	@RequestMapping("/student/add")
	public String add(@RequestParam(value = "npm", required = true) String npm,
				@RequestParam(value = "name", required = true) String name,
				@RequestParam(value = "gpa", required = true) double gpa) {
		StudentModel student = new StudentModel(npm, name, gpa);
		studentService.addStudent(student);
		return "add";
	}
	
	@RequestMapping(value = {"/student/view","student/view/{npm}"})
		public String viewPath(@PathVariable Optional<String> npm, Model model)
		{
			String view = "view";
			if(npm.isPresent()){
				StudentModel student = studentService.selectStudent(npm.get());
				if(student == null)
				{
					model.addAttribute("eMessage","Tidak ditemukan");
					view = "ePage";
				}else {
					model.addAttribute("student", student);
					view = "view";
				}
			} else {
				model.addAttribute("eMessage","NPM Kosong");
				view = "ePage";
			}
			
			return view;
		};
	
@RequestMapping(value = {"/student/delete","student/delete/{npm}"})
public String deletePath(@PathVariable Optional<String> npm, Model model)
{
	if(npm.isPresent()){
		StudentModel student = studentService.selectStudent(npm.get());
		if(student == null)
		{
			model.addAttribute("eMessage","Tidak ditemukan");
		}else {
			model.addAttribute("eMessage", "Data berhasil dihapus");
			studentService.deleteStudent(student);
		}
	} else {
		model.addAttribute("eMessage","NPM Kosong");
	}
	
	return "ePage";
};

	@RequestMapping("/student/viewall")
	public String viewAll(Model model) {
		List<StudentModel> students = studentService.selectAllStudents();
		model.addAttribute("students", students);
		return "viewall";
	}
	
	
}