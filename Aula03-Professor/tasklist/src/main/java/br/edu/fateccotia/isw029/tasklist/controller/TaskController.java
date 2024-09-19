package br.edu.fateccotia.isw029.tasklist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateccotia.isw029.tasklist.model.Task;
import br.edu.fateccotia.isw029.tasklist.service.AuthService;
import br.edu.fateccotia.isw029.tasklist.service.TaskService;

@RestController
@RequestMapping("/controller")
public class TaskController {
	
	@Autowired
	public TaskService taskService;
	@Autowired
	public AuthService authService;
	
	@GetMapping
	public ResponseEntity<List<Task>> ListarTask(){
		return ResponseEntity.ok(taskService.findALL());
	}
	
	@PostMapping
	public ResponseEntity<Task> create(@RequestBody Task task,
			@RequestHeader(name="token",required = true)String token){
		Boolean isValid = authService.validate(token);
		if(isValid) {
			return ResponseEntity.ok(taskService.save(task));
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
	}
	
	
	

	
}
