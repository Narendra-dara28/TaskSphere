package com.example.TaskSphere.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.TaskSphere.Entities.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{
	
	@Query(value = "select * from person where email = :email and password = :password", nativeQuery = true)
	public Person login(String email, String password);

}
