package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.*;

import java.util.List;

import org.springframework.data.repository.*;

public interface CourseRepository extends CrudRepository <Course, Integer>{
	public default Course addSectionToCourse(Section section, Course course) {
		course.addSection(section);
		return section.setCourse(course);
	}
	public default Course setAuthorForCourse(Faculty faculty, Course course) {
		course.setAuthor(faculty);
		return faculty.addAuthoredCourse(course);
	}
	public default List<Section> findSectionForCourse(Course course) {
		return course.getSections();
	}
}