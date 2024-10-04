package br.edu.fateccotia.isw029.tasklist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateccotia.isw029.tasklist.model.Task;
import br.edu.fateccotia.isw029.tasklist.model.User;
import br.edu.fateccotia.isw029.tasklist.service.AuthService;
import br.edu.fateccotia.isw029.tasklist.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	public TaskService taskService;
	@Autowired
	public AuthService authService;
	
	//meu
//	@GetMapping
//	public ResponseEntity<List<Task>> ListarTask(){
//		return ResponseEntity.ok(taskService.findALL());
//	}
	
//	@DeleteMapping
//	public ResponseEntity<Task> delete(@RequestBody Task task,
//			@RequestHeader(name="token",required = true)String token){
//		Boolean isValid = authService.validate(token);
//		if(isValid) {
//			taskService.delete(task.getId());
//			return ResponseEntity.ok().build();
//		}
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//	}
//	
//	@PutMapping
//	public ResponseEntity<Task> update(@RequestBody Task task,
//			@RequestHeader(name="token",required = true)String token){
//		Boolean isValid = authService.validate(token);
//		if(isValid) {
//			return ResponseEntity.ok(taskService.update(task));
//		}
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//	}
	
//	@PutMapping("/{id}")
//	public ResponseEntity<Task> checkBoxAlt(@RequestBody Task task,
//			@RequestHeader(name="token",required = true)String token){
//		Boolean isValid = authService.validate(token);
//		if(isValid) {
//			return ResponseEntity.ok(taskService.checkBoxAlt(task));
//		}
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//	
//	
//	
//	}
	
	//professor
	@PostMapping
	public ResponseEntity<Task> create(@RequestBody Task task,
			@RequestHeader(name="token",required = true)String token){
		Boolean isValid = authService.validate(token);
		if(isValid) {
			User user = authService.toUser(token);
			task.setUser(user);
			Task save = taskService.save(task);
			return ResponseEntity.ok(save);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
	}
	
	@GetMapping
	public ResponseEntity<List<Task>> findAll(@RequestHeader(name="token",required = true)String token) {
		Boolean isValid = authService.validate(token);
		if(isValid) {
			User user = authService.toUser(token);
			List<Task> list = taskService.findByUserId(user.getId());
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	

	
	@PutMapping("/{id}")
	public ResponseEntity<Task> update(@PathVariable(name="id") Integer id,
			@RequestHeader(name = "token",required = true) String token,
			@RequestBody Task task){
		Boolean isValid = authService.validate(token);
		if(isValid) {
			Task saved = taskService.update(id, task);
			return ResponseEntity.ok(saved);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Task>> search(@RequestParam(name = "q") String query
			,@RequestHeader(name= "token", required = true) String token){
		Boolean isValid = authService.validate(token);
		if(isValid) {
			User user = authService.toUser(token);
			List<Task> list = taskService.search(query, user);
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	
}
