package com.example.TaskSphere.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.TaskSphere.Entities.Person;
import com.example.TaskSphere.Entities.Project;
import com.example.TaskSphere.Entities.Task;

public interface TaskRepository extends CrudRepository<Task, Integer>{
	
	@Query(value = "select deadline from task where id = :id", nativeQuery = true)
	public Date findDate(Integer id);
	
	@Query(value = "select priority from task where id = :id", nativeQuery = true)
	public String findPriority(Integer id);

	@Query(value = "select status from task where id = :id", nativeQuery = true)
	public String findStatus(Integer id);
	
	@Query(value = "select p.* from person p inner join task t on p.id = t.person_id where t.id = :id", nativeQuery = true)
	public Person findPersonId(Integer id);
	
	@Query(value = "select p.* from project p inner join task t on p.id = t.project_id where t.id = :id", nativeQuery = true)
	public Project findProjectId(Integer id);
	
	@Query(value = "select t.* from task t inner join person p on p.id = t.person_id where p.id = :id", nativeQuery = true)
	public Task findPersonById(Integer id);
}
