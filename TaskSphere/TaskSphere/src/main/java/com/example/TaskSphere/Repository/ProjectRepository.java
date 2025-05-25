package com.example.TaskSphere.Repository;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.TaskSphere.Entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer>{
	
	@Query(value = "select title from project where id = :id", nativeQuery = true)
	public String findTitle(Integer id);
	
	@Query(value = "select description from project where id = :id", nativeQuery = true)
	public String findDescription(Integer id);
	
	@Query(value = "select status from project where id = :id", nativeQuery = true)
	public String findStatus(Integer id);
	
	@Query(value = "select dead_line from project where id = :id", nativeQuery = true)
	public Date findDeadLine(Integer id);
}
