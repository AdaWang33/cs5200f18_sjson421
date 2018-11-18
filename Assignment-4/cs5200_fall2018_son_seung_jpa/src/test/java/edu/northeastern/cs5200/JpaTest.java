package edu.northeastern.cs5200;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.northeastern.cs5200.daos.UniversityDao;
import edu.northeastern.cs5200.models.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {
	@Autowired
	UniversityDao uDao;

	@Before
	public void setUpTest() {
		uDao.truncateDatabase();

		Faculty alan = new Faculty(0, null, null, "Alan", "Turin", "123A", true, null);
		Faculty ada = new Faculty(0, null, null, "Ada", "Loveless", "123B", true, null);
		uDao.createFaculty(alan);
		uDao.createFaculty(ada);

		Student alice = new Student(0, null, null, "Alice", "Wonderland", 2020, 12000, new HashSet<Section>());
		Student bob = new Student(0, null, null, "Bob", "Hope", 2021, 23000, new HashSet<Section>());
		Student charlie = new Student(0, null, null, "Charlie", "Brown", 2019, 21000, new HashSet<Section>());
		Student dan = new Student(0, null, null, "Dan", "Craig", 2019, 0, new HashSet<Section>());
		Student edward = new Student(0, null, null, "Edward", "Scissorhands", 2022, 11000, new HashSet<Section>());
		Student frank = new Student(0, null, null, "Frank", "Herbert", 2018, 0, new HashSet<Section>());
		Student gregory = new Student(0, null, null, "Gregory", "Peck", 2023, 10000, new HashSet<Section>());
		uDao.createStudent(alice);
		uDao.createStudent(bob);
		uDao.createStudent(charlie);
		uDao.createStudent(dan);
		uDao.createStudent(edward);
		uDao.createStudent(frank);
		uDao.createStudent(gregory);

		Course a = new Course(0, "CS1234", new ArrayList<Section>(), alan);
		Course b = new Course(0, "CS2345", new ArrayList<Section>(), alan);
		Course c = new Course(0, "CS3456", new ArrayList<Section>(), ada);
		Course d = new Course(0, "CS4567", new ArrayList<Section>(), ada);
		uDao.createCourse(a);
		uDao.createCourse(b);
		uDao.createCourse(c);
		uDao.createCourse(d);

		Section q = new Section(0, "SEC4321", 50, a, new HashSet<Student>());
		Section w = new Section(0, "SEC5432", 50, a, new HashSet<Student>());
		Section e = new Section(0, "SEC6543", 50, b, new HashSet<Student>());
		Section r = new Section(0, "SEC7654", 50, c, new HashSet<Student>());
		uDao.createSection(q);
		uDao.createSection(w);
		uDao.createSection(e);
		uDao.createSection(r);

		uDao.enrollStudentInSection(alice, q);
		uDao.enrollStudentInSection(alice, w);
		uDao.enrollStudentInSection(bob, w);
		uDao.enrollStudentInSection(charlie, e);
	}

	@Test
	public void validateUsers() {
		assertEquals(9, uDao.findAllUsers().size());
	}

	@Test
	public void validateFaculty() {
		assertEquals(2, uDao.findAllFaculty().size());
	}

	@Test
	public void validateStudents() {
		assertEquals(7, uDao.findAllStudents().size());
	}

	@Test
	public void validateCourses() {
		assertEquals(4, uDao.findAllCourses().size());
	}

	@Test
	public void validateSections() {
		assertEquals(4, uDao.findAllSections().size());
	}

	@Test
	public void validateCourseAuthorship() {
		List<Faculty> faculty = uDao.findAllFaculty();
		for (int i = 0; i < faculty.size(); i++) {
			int count = 0;
			Faculty f = faculty.get(i);

			List<Course> courses = uDao.findCoursesForAuthor(f);
			for (int j = 0; j < courses.size(); j++) {
				count++;
			}
			if (f.getFirstName().equals("Alan"))
				assertEquals(2, count);
			else
				assertEquals(2, count);
		}
	}

	@Test
	public void validateSectionPerCourse() {
		List<Course> courses = uDao.findAllCourses();
		for (int i = 0; i < courses.size(); i++) {
			int count = 0;
			Course c = courses.get(i);

			List<Section> sections = uDao.findSectionForCourse(c);
			for (int j = 0; j < sections.size(); j++) {
				count++;
			}
			if (c.getTitle().equals("CS1234"))
				assertEquals(2, count);
			else if (c.getTitle().equals("CS2345"))
				assertEquals(1, count);
			else if (c.getTitle().equals("CS3456"))
				assertEquals(1, count);
			else
				assertEquals(0, count);
		}
	}

	@Test
	public void validateSectionEnrollment() {
		List<Student> students = uDao.findAllStudents();
		for (int i = 0; i < students.size(); i++) {
			int count = 0;
			Student s = students.get(i);

			Set<Section> sections = uDao.findSectionsForStudent(s);
			for (int j = 0; j < sections.size(); j++) {
				count++;
			}
			if (s.getFirstName().equals("Alice"))
				assertEquals(2, count);
			else if (s.getFirstName().equals("Bob"))
				assertEquals(1, count);
			else if (s.getFirstName().equals("Charlie"))
				assertEquals(1, count);
			else if (s.getFirstName().equals("Dan"))
				assertEquals(0, count);
			else if (s.getFirstName().equals("Edward"))
				assertEquals(0, count);
			else if (s.getFirstName().equals("Frank"))
				assertEquals(0, count);
			else
				assertEquals(0, count);
		}
	}

	@Test
	public void validateStudentEnrollment() {
		List<Section> sections = uDao.findAllSections();
		for (int i = 0; i < sections.size(); i++) {
			int count = 0;
			Section s = sections.get(i);

			Set<Student> students = uDao.findStudentsInSection(s);
			for (int j = 0; j < students.size(); j++) {
				count++;
			}
			if (s.getTitle().equals("SEC4321"))
				assertEquals(1, count);
			else if (s.getTitle().equals("SEC5432"))
				assertEquals(2, count);
			else if (s.getTitle().equals("SEC6543"))
				assertEquals(1, count);
			else
				assertEquals(0, count);
		}
	}

	@Test
	public void validateSectionSeats() {
		List<Section> sections = uDao.findAllSections();
		for (int i = 0; i < sections.size(); i++) {
			Section s = sections.get(i);
			
			if (s.getTitle().equals("SEC4321"))
				assertEquals(50, uDao.findSeatsForSection(s));
			else if (s.getTitle().equals("SEC5432"))
				assertEquals(50, uDao.findSeatsForSection(s));
			else if (s.getTitle().equals("SEC6543"))
				assertEquals(50, uDao.findSeatsForSection(s));
			else
				assertEquals(50, uDao.findSeatsForSection(s));
		}
	}
}
