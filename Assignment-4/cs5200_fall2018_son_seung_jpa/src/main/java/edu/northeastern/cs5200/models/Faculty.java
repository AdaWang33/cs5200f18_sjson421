package edu.northeastern.cs5200.models;

import java.util.List;

import javax.persistence.*;

@Entity
public class Faculty extends Person {
	private String office;
	private Boolean tenured;
	@OneToMany(mappedBy = "author")
	private List<Course> authoredCourses; 
	
	public Faculty(int id, String username, String password, String firstName, String lastName, String office,
			Boolean tenured, List<Course> authoredCourses) {
		super(id, username, password, firstName, lastName);
		this.office = office;
		this.tenured = tenured;
		this.authoredCourses = authoredCourses;
	}
	public Faculty () {
		
	}
	public List<Course> getAuthoredCourses() {
		return authoredCourses;
	}

	public void setAuthoredCourses(List<Course> authoredCourses) {
		this.authoredCourses = authoredCourses;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public Boolean getTenured() {
		return tenured;
	}

	public void setTenured(Boolean tenured) {
		this.tenured = tenured;
	}
	public Course addAuthoredCourse(Course course) {
		this.authoredCourses.add(course);
		if(course.getAuthor() != this)
	    course.setAuthor(this);
		return course;
	}
}
