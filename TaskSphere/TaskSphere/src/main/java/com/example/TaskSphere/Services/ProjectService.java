package com.example.TaskSphere.Services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskSphere.DAO.ProjectDAO;
import com.example.TaskSphere.Entities.Person;
import com.example.TaskSphere.Entities.Project;
import com.example.TaskSphere.Entities.Task;
import com.example.TaskSphere.Repository.ProjectRepository;
import com.example.TaskSphere.Repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	public Project save(Project project) {
		projectRepository.save(project);
		return project;
	}
	
	public ProjectDAO load() {
		ProjectDAO d1 = new ProjectDAO();
		d1.setProjects(projectRepository.findAll());
		System.out.println(d1.getProjects());
		return d1;
	}
	
	@Transactional
	public String update(Project project) {
	    // Check if the project exists
	    if (projectRepository.existsById(project.getId())) {
	        // Fetch the existing project to ensure we are working with a persistent entity
	        Project existingProject = projectRepository.findById(project.getId()).get();

	        // Update project fields with the new data if provided
	        if (project.getTitle() != null) {
	            existingProject.setTitle(project.getTitle());
	        } else {
	            existingProject.setTitle(projectRepository.findTitle(project.getId()));
	        }

	        if (project.getDescription() != null) {
	            existingProject.setDescription(project.getDescription());
	        } else {
	            existingProject.setDescription(projectRepository.findDescription(project.getId()));
	        }

	        if (project.getDeadLine() != null) {
	            existingProject.setDeadLine(project.getDeadLine());
	        } else {
	            existingProject.setDeadLine(projectRepository.findDeadLine(project.getId()));
	        }

	        if (project.getStatus() != null) {
	            existingProject.setStatus(project.getStatus());
	        } else {
	            existingProject.setStatus(projectRepository.findStatus(project.getId()));
	        }

	        // Save updated project
	        projectRepository.save(existingProject);

	        // Now update related tasks (ensure they are using the updated project details)
	        for (Task task : existingProject.getTasks()) {
	            task.setProject(existingProject); // Set the updated project
	            taskRepository.save(task); // Save each updated task
	        }

	        return "Project and associated tasks updated successfully";
	    } else {
	        return "No such project found";
	    }
	}
	public String delete(Integer id) {
		Optional<Project> optionalProject = projectRepository.findById(id);
	    if (optionalProject.isPresent()) {
	        Project project = optionalProject.get();

	        // Break ManyToMany links
	        for (Person person : project.getPersons()) {
	            person.getProjects().remove(project);
	        }
	        project.getPersons().clear();

	        // Now safely delete
	        projectRepository.delete(project);
	        return "Project deleted successfully";
	    } else {
	        return "No such project found";
	    }
	}

}
