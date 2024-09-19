package br.edu.fateccotia.isw029.tasklist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.fateccotia.isw029.tasklist.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer>{

}
