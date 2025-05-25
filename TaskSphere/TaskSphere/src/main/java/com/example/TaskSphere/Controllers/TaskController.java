package com.example.TaskSphere.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaskSphere.DAO.TaskDAO;
import com.example.TaskSphere.DTO.TaskCreateDTO;
import com.example.TaskSphere.DTO.TaskDTO;
import com.example.TaskSphere.Entities.Person;
import com.example.TaskSphere.Entities.Task;
import com.example.TaskSphere.Services.TaskService;

@RestController
@RequestMapping("task")
@CrossOrigin
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping
	public void save(@RequestBody TaskCreateDTO task) {
		taskService.save(task);
	}
	
	@GetMapping
	public List<TaskDTO> load() {
		return taskService.load();
	}
	
	@PostMapping("/update")
	public String update(@RequestBody Task task) {
		return taskService.update(task);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		return taskService.delete(id);
	}
	
	@GetMapping("/getById/{id}")
	public Task getById(@PathVariable Integer id) {
		return taskService.findByIdPerson(id);
	}

}
