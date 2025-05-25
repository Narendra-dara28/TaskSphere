package com.example.TaskSphere.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskSphere.DAO.PersonDAO;
import com.example.TaskSphere.Entities.Person;
import com.example.TaskSphere.Repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public Person save(Person person) {
		personRepository.save(person);
		return person;
	}
	
	public PersonDAO load() {
		PersonDAO d1 = new PersonDAO();
		d1.setPerson(personRepository.findAll());
		return d1;
	}
	
	public Person login(Person person) {
		Person result = personRepository.login(person.getEmail(), person.getPassword());
		if(result != null) {
			return result;
		}else {
			return null;
		}
	}

}
