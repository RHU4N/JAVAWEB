package br.edu.fateccotia.isw029.tasklist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.fateccotia.isw029.tasklist.model.Task;
import br.edu.fateccotia.isw029.tasklist.model.User;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer>{

	@Query("select t from Task t where t.user.id = ?1")
	List<Task> findByUserId(Integer id);
	
	List<Task> searchByDescriptionStartingWithIgnoreCaseAndUser(String description, User user);

}
