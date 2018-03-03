package com.example.tutorial3.model;

public class StudentModel{
	private String name;
	private String npm;
	private double gpa;
	
	public StudentModel(String npm, String name, double gpa) {
		this.name = name;
		this.npm = npm;
		this.gpa = gpa;
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public String getNPM(){
		return this.npm;
	}
	
	public double getGPA(){
		return this.gpa;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNPM(String npm) {
		this.npm = npm;
	}
	
	public void setGPA(double gpa) {
		this.gpa = gpa;
	}
}
