package com.example.TaskSphere.DAO;

import com.example.TaskSphere.Entities.Person;

public class PersonDAO {
	
	private Iterable<Person> persons;
	
	public void setPerson(Iterable<Person> persons) {
		this.persons = persons;
	}
	
	public Iterable<Person> getPersons(){
		return persons;
	}

}
