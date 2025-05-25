package com.example.TaskSphere.DTO;

public class TaskDTO {
    private int id;
    private String deadLine;
    private String priority;
    private String status;
    private PersonSummaryDTO person;
    private ProjectSummaryDTO project;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public PersonSummaryDTO getPerson() {
		return person;
	}
	public void setPerson(PersonSummaryDTO person) {
		this.person = person;
	}
	public ProjectSummaryDTO getProject() {
		return project;
	}
	public void setProject(ProjectSummaryDTO project) {
		this.project = project;
	}
    
    
}

