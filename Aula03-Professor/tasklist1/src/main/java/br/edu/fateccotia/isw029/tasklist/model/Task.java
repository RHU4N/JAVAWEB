package br.edu.fateccotia.isw029.tasklist.model;

import br.edu.fateccotia.isw029.tasklist.enums.TaskPriority;
import br.edu.fateccotia.isw029.tasklist.enums.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	private User user;
	private TaskStatus status = TaskStatus.PENDING;
	private String description;
	@ManyToOne
	private Category category;
	private TaskPriority priority = TaskPriority.MEDIUM;
	
	//Constructors
	public Task() {	}

	public Task(Integer id, User user, TaskStatus status, String description, Category category,
			TaskPriority priority) {
		super();
		this.id = id;
		this.user = user;
		this.status = status;
		this.description = description;
		this.category = category;
		this.priority = priority;
	}

	//Getters and Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public TaskStatus getStatus() {
		return status;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public TaskPriority getPriority() {
		return priority;
	}
	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}
}
