package com.example.TaskSphere.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskSphere.DAO.TaskDAO;
import com.example.TaskSphere.DTO.PersonSummaryDTO;
import com.example.TaskSphere.DTO.ProjectSummaryDTO;
import com.example.TaskSphere.DTO.TaskCreateDTO;
import com.example.TaskSphere.DTO.TaskDTO;
import com.example.TaskSphere.Entities.Person;
import com.example.TaskSphere.Entities.Project;
import com.example.TaskSphere.Entities.Task;
import com.example.TaskSphere.Repository.PersonRepository;
import com.example.TaskSphere.Repository.ProjectRepository;
import com.example.TaskSphere.Repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Task save(TaskCreateDTO taskDto) {
		
	        Task task = new Task();
	        task.setPriority(taskDto.getPriority());
	        task.setStatus(taskDto.getStatus());

	        // Parse deadline string to java.util.Date
	        try {
	        	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
		        task.setDeadLine(sdf.parse(taskDto.getDeadLine()));

	        }catch(ParseException e) {
	        	
	        }
	        
	        // Fetch person and project from DB
	        Person person = personRepository.findById(taskDto.getPersonId())
	                            .orElseThrow(() -> new RuntimeException("Person not found"));
	        Project project = projectRepository.findById(taskDto.getProjectId())
	                            .orElseThrow(() -> new RuntimeException("Project not found"));

	        task.setPerson(person);
	        task.setProject(project);

	        taskRepository.save(task);
		return task;
	}
	
//	public TaskDAO load() {
//		TaskDAO t1 = new TaskDAO();
//		t1.setTasks(taskRepository.findAll());
//		return t1;
//	}
	
	public List<TaskDTO> load() {
		
		List<Task> tasks = StreamSupport
			    .stream(taskRepository.findAll().spliterator(), false)
			    .collect(Collectors.toList());
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");

	    return tasks.stream().map(task -> {
	        TaskDTO dto = new TaskDTO();
	        dto.setId(task.getId());
	        dto.setStatus(task.getStatus());
	        dto.setPriority(task.getPriority());
	        dto.setDeadLine(sdf.format(task.getDeadLine()));

	        Person p = task.getPerson();
	        PersonSummaryDTO pdto = new PersonSummaryDTO();
	        pdto.setId(p.getId());
	        pdto.setName(p.getName());
	        pdto.setType(p.getType());
	        pdto.setEmail(p.getEmail());
	        dto.setPerson(pdto);

	        Project pr = task.getProject();
	        ProjectSummaryDTO prdto = new ProjectSummaryDTO();
	        prdto.setId(pr.getId());
	        prdto.setTitle(pr.getTitle());
	        prdto.setDescription(pr.getDescription());
	        prdto.setStatus(pr.getStatus());
	        prdto.setDeadLine(sdf.format(pr.getDeadLine()));
	        dto.setProject(prdto);

	        return dto;
	    }).collect(Collectors.toList());
	}

	
	public String update(Task task) {
	    Optional<Task> existingTaskOpt = taskRepository.findById(task.getId());

	    if (existingTaskOpt.isPresent()) {
	        Task existingTask = existingTaskOpt.get();

	        if (task.getDeadLine() != null) {
	            existingTask.setDeadLine(task.getDeadLine());
	        }
	        if (task.getStatus() != null) {
	            existingTask.setStatus(task.getStatus());
	        }
	        if (task.getPriority() != null) {
	            existingTask.setPriority(task.getPriority());
	        }
	        if (task.getPerson() != null) {
	            existingTask.setPerson(task.getPerson());
	        }
	        if (task.getProject() != null) {
	            existingTask.setProject(task.getProject());
	        }

	        taskRepository.save(existingTask);
	        return "Updated successfully";
	    } else {
	        return "No such Task found";
	    }
	}

	
	public String delete(Integer id) {
		if(taskRepository.existsById(id)) {
			taskRepository.deleteById(id);
			return "Task deleted successfully";
		}else {
			return "No such task found";
		}
	}
	
	
	public Task findByIdPerson(Integer id) {
		return taskRepository.findPersonById(id);
	}

}
