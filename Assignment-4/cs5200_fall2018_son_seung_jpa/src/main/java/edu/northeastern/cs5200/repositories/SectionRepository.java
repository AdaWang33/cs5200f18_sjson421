package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.*;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.*;

public interface SectionRepository extends CrudRepository <Section, Integer>{
	public default Set<Student> findStudentsInSection(Section section) {
		return section.getEnrolledStudents();
	}
	public default void removeEnrollments(List<Section> s) {
		for (int i = 0; i < s.size(); i++)
			s.get(i).removeEnrollments();
	}
	public default int findSeatsForSection(Section section) {
		return section.getSeats();
	}
	public default Boolean enrollStudentInSection(Section section, Student student) {
		return section.enrollStudent(student);
	}
}

