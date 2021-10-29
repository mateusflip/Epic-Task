package br.com.fiap.epictask.controller.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.repository.TaskRepository;

@RestController
@RequestMapping("/api/task")
public class ApiTaskController {

	@Autowired
	private TaskRepository repository;
	
	@GetMapping
	public List<Task> index() {
		List<Task> tasks = repository.findAll();
		return tasks;
	}
	
	@PostMapping
	public ResponseEntity<Task> create(
			@RequestBody Task task,
			UriComponentsBuilder uriBuilder) {
		repository.save(task);
		
		URI uri = uriBuilder
				.path("/api/task/{id}")
				.buildAndExpand(task.getId())
				.toUri();

		return ResponseEntity.created(uri).body(task);
	}
	
}
