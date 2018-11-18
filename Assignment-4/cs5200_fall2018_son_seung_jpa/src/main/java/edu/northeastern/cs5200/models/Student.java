package edu.northeastern.cs5200.models;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Student extends Person {
	private int gradYear;
	private long scholarship;
	@ManyToMany
	@JoinTable(name = "Enrollment", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "section_id", referencedColumnName = "id"))
	private Set<Section> enrolledSections;

	public Student(int id, String username, String password, String firstName, String lastName, int gradYear,
			long scholarship, Set<Section> enrolledSections) {
		super(id, username, password, firstName, lastName);
		this.gradYear = gradYear;
		this.scholarship = scholarship;
		this.enrolledSections = enrolledSections;
	}

	public Student() {

	}

	public Set<Section> getEnrolledSections() {
		return enrolledSections;
	}

	public void setEnrolledSections(Set<Section> enrolledSections) {
		this.enrolledSections = enrolledSections;
	}

	public int getGradYear() {
		return gradYear;
	}

	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}

	public long getScholarship() {
		return scholarship;
	}

	public void setScholarship(long scholarship) {
		this.scholarship = scholarship;
	}

	public Boolean enrollSection(Section section) {
		this.enrolledSections.add(section);
		return true;
	}

	public void removeEnrollments() {
		this.getEnrolledSections().clear();
	}
}
