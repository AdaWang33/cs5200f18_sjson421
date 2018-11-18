package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.*;

import java.util.List;

import org.springframework.data.repository.*;

public interface FacultyRepository extends CrudRepository <Faculty, Integer>{
	public default List<Course> findCoursesForAuthor(Faculty faculty) {
		return faculty.getAuthoredCourses();
	}
}

