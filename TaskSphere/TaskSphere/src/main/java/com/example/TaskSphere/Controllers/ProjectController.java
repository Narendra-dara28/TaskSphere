package com.example.TaskSphere.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaskSphere.DAO.ProjectDAO;
import com.example.TaskSphere.Entities.Project;
import com.example.TaskSphere.Services.ProjectService;

@RestController
@RequestMapping("project")
@CrossOrigin
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping
	public Project save(@RequestBody Project project) {
		projectService.save(project);
		return project;	
	}
	
	@GetMapping
	public ProjectDAO load() {
		
		return projectService.load();
	}
	
	@PostMapping("/update")
	public String update(@RequestBody Project project) {
		return projectService.update(project);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		return projectService.delete(id);
	}

}
