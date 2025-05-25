package com.example.TaskSphere;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.TaskSphere.Entities.Person;
import com.example.TaskSphere.Entities.Project;
import com.example.TaskSphere.Entities.Task;
import com.example.TaskSphere.Repository.PersonRepository;
import com.example.TaskSphere.Repository.ProjectRepository;
import com.example.TaskSphere.Repository.TaskRepository;

@SpringBootApplication
public class TaskSphereApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TaskSphereApplication.class, args);
	}
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	public void run(String ...strings) {
		// Create Persons
		Person p1 = new Person();
		p1.setEmail("ch@gmail.com");
		p1.setName("naveen");
		p1.setPassword("123");
		p1.setType("admin");

		Person p2 = new Person();
		p2.setEmail("k@gmail.com");
		p2.setName("baba");
		p2.setPassword("143");
		p2.setType("user");

		// Save persons first
		personRepository.save(p1);
		personRepository.save(p2);

		// Create Projects
		Project pr1 = new Project();
		pr1.setDescription("Attendance will be managed");
		pr1.setTitle("Student Management System");
		pr1.setStatus("Done");

		Project pr2 = new Project();
		pr2.setDescription("Medicines will be managed");
		pr2.setTitle("Mail order pharmacy");
		pr2.setStatus("On going");

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
		try {
		    pr1.setDeadLine(sdf.parse("23-May-2025"));
		    pr2.setDeadLine(sdf.parse("30-June-2025"));
		} catch (ParseException e) {
		    e.printStackTrace();
		}

		// Save projects first
		projectRepository.save(pr1);
		projectRepository.save(pr2);

		// Set project-person relationships
		p1.setProjects(Set.of(pr1, pr2));
		p2.setProjects(Set.of(pr2));
		pr1.setPersons(Set.of(p1));
		pr2.setPersons(Set.of(p1, p2));

		// Save again to update relationships
		personRepository.save(p1);
		personRepository.save(p2);
		projectRepository.save(pr1);
		projectRepository.save(pr2);

		// Create Tasks (after projects and persons are saved)
		Task t1 = new Task();
		t1.setPriority("High");
		t1.setStatus("On going");
		t1.setPerson(p1);
		t1.setProject(pr1);
		try {
		    t1.setDeadLine(sdf.parse("23-May-2025"));
		} catch (ParseException e) {
		    e.printStackTrace();
		}

		Task t2 = new Task();
		t2.setPriority("Medium");
		t2.setStatus("Not yet started");
		t2.setPerson(p2);
		t2.setProject(pr2);
		try {
		    t2.setDeadLine(sdf.parse("23-May-2025"));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		// Save tasks
		taskRepository.saveAll(List.of(t1, t2));
	}

}
