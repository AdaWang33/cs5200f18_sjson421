package edu.northeastern.cs5200.models;

import java.util.List;

import javax.persistence.*;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
	private List<Section> sections;
	@ManyToOne
	private Faculty author;

	public Course(int id, String title, List<Section> sections, Faculty author) {
		super();
		this.id = id;
		this.title = title;
		this.sections = sections;
		this.author = author;
	}
	public Course() {
		
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

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Faculty getAuthor() {
		return author;
	}

	public void setAuthor(Faculty author) {
		this.author = author;
		if (!author.getAuthoredCourses().contains(this)) {
			author.getAuthoredCourses().add(this);
		}
	}
	public void addSection(Section s) {
		this.sections.add(s);
		if(s.getCourse() != this)
	    s.setCourse(this);
	}
}
