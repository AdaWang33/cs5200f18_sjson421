package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityDao {
	@Autowired
	PersonRepository personRepo;
	@Autowired
	FacultyRepository facultyRepo;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	SectionRepository sectionRepo;
	
	public void truncateDatabase() {
		List<Student> students = (List<Student>) studentRepo.findAll();
		List<Section> sections = (List<Section>) sectionRepo.findAll();
		studentRepo.removeEnrollments(students);
		studentRepo.saveAll(students);
		sectionRepo.removeEnrollments(sections);
		sectionRepo.saveAll(sections);
		sectionRepo.deleteAll();
		courseRepo.deleteAll();
		studentRepo.deleteAll();
		facultyRepo.deleteAll();
		personRepo.deleteAll();
	}

	public Faculty createFaculty(Faculty faculty) {
		return facultyRepo.save(faculty);
	}

	public Student createStudent(Student student) {
		return studentRepo.save(student);
	}

	public Course createCourse(Course course) {
		return courseRepo.save(course);
	}

	public Section createSection(Section section) {
		return sectionRepo.save(section);
	}

	public Course addSectionToCourse(Section section, Course course) {
		Course c = courseRepo.addSectionToCourse(section, course);
		courseRepo.save(c);
		return c;
	}

	public Course setAuthorForCourse(Faculty faculty, Course course) {
		Course c = courseRepo.setAuthorForCourse(faculty, course);
		courseRepo.save(course);
		return c;
	}

	public Boolean enrollStudentInSection(Student student, Section section) {
		Boolean b = studentRepo.enrollStudentInSection(student, section);
		studentRepo.save(student);
		return b;
	}

	public List<Person> findAllUsers() {
		return (List<Person>) personRepo.findAll();
	}

	public List<Faculty> findAllFaculty() {
		return (List<Faculty>) facultyRepo.findAll();
	}

	public List<Student> findAllStudents() {
		return (List<Student>) studentRepo.findAll();
	}

	public List<Course> findAllCourses() {
		return (List<Course>) courseRepo.findAll();
	}

	public List<Section> findAllSections() {
		return (List<Section>) sectionRepo.findAll();
	}

	public List<Course> findCoursesForAuthor(Faculty faculty) {
		return facultyRepo.findCoursesForAuthor(faculty);
	}

	public List<Section> findSectionForCourse(Course course) {
		return courseRepo.findSectionForCourse(course);
	}

	public Set<Student> findStudentsInSection(Section section) {
		return sectionRepo.findStudentsInSection(section);
	}

	public Set<Section> findSectionsForStudent(Student student) {
		return studentRepo.findSectionsForStudent(student);
	}
	
	public int findSeatsForSection(Section section) {
		return sectionRepo.findSeatsForSection(section);
	}

}
