package com.example.TaskSphere.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaskSphere.DAO.PersonDAO;
import com.example.TaskSphere.Entities.Person;
import com.example.TaskSphere.Services.PersonService;

@RestController
@RequestMapping("person")
@CrossOrigin
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@PostMapping
	public Person save(@RequestBody Person person) {
		personService.save(person);
		return person;
	}
	
	@GetMapping
	public PersonDAO load() {
		return personService.load();
	}
	
	@PostMapping("/login")
	public Person login(@RequestBody Person person) {
		return personService.login(person);
		
	}
}
