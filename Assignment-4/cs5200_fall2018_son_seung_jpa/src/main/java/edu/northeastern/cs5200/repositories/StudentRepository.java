package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.*;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.*;

public interface StudentRepository extends CrudRepository <Student, Integer>{
	public default Boolean enrollStudentInSection(Student student, Section section) {
		return student.enrollSection(section);
	}
	public default Set<Section> findSectionsForStudent(Student student) {
		return student.getEnrolledSections();
	}
	public default void removeEnrollments(List<Student> s) {
		for (int i = 0; i < s.size(); i++)
			s.get(i).removeEnrollments();
	}
}
