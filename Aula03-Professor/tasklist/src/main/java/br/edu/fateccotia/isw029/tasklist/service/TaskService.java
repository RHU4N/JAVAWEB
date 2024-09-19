package br.edu.fateccotia.isw029.tasklist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.isw029.tasklist.model.Task;
import br.edu.fateccotia.isw029.tasklist.repository.TaskRepository;

@Service
public class TaskService {
	@Autowired //Auto gerancia o obj com o framework e quando a gente quiser ele entrega
	private TaskRepository taskRepository;
	
	public Task save(Task task) {
		return taskRepository.save(task);
	}
	
	public List<Task> findALL(){
		List<Task> list = new ArrayList<Task>();
		taskRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	public void delete(Integer id) {
		taskRepository.deleteById(id);
	}
	
	public Task update(Task task) {
		Optional<Task> found = taskRepository.findById(task.getId());
		found.get().setDescription(task.getDescription());
		found.get().setCategory(task.getCategory());
		found.get().setStatus(task.getStatus());
		found.get().setPriority(task.getPriority());
		
		return taskRepository.save(found.get());
		
		
	}
}
