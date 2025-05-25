package com.example.TaskSphere.DAO;

import com.example.TaskSphere.Entities.Task;

public class TaskDAO {
	
	private Iterable<Task> tasks;
	
	public void setTasks(Iterable<Task> tasks) {
		this.tasks = tasks;
	}
	
	public Iterable<Task> getTasks(){
		return tasks;
	}

}
