package edu.northeastern.cs5200.models;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private int seats;
	@ManyToOne()
	private Course course;
	@ManyToMany(mappedBy = "enrolledSections")
	private Set<Student> enrolledStudents;

	public Section(int id, String title, int seats, Course course, Set<Student> enrolledStudents) {
		super();
		this.id = id;
		this.title = title;
		this.seats = seats;
		this.course = course;
		this.enrolledStudents = enrolledStudents;
	}

	public Section() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public Course getCourse() {
		return course;
	}

	public Course setCourse(Course course) {
		this.course = course;
		if (!course.getSections().contains(this)) {
			course.getSections().add(this);
		}
		return course;
	}

	public Set<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(Set<Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	public Boolean enrollStudent(Student student) {
		if (!student.getEnrolledSections().contains(this))
			student.getEnrolledSections().add(this);
		return this.enrolledStudents.add(student);
	}

	public void removeEnrollments() {
		this.getEnrolledStudents().clear();
	}
}
