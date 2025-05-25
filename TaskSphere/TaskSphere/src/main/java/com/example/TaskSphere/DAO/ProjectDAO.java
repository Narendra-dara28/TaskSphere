package com.example.TaskSphere.DAO;

import com.example.TaskSphere.Entities.Project;

public class ProjectDAO {
	
	private Iterable<Project> projects;
	
	public void setProjects(Iterable<Project> projects) {
		this.projects = projects;
	}
	
	public Iterable<Project> getProjects(){
		return projects;
	}

}
