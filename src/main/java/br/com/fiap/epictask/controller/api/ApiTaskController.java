package br.com.fiap.epictask.controller.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<Task> index(@RequestParam(required= false) String title) {
		if(title == null) {
			return repository.findAll();
		}
		return repository.findByTitleLike("%" + title + "%");
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
	
	//Procura por Id e caso não encontre retorna 404
	@GetMapping("{id}")
	public ResponseEntity<Task> show(@PathVariable Long id){
		return ResponseEntity.of(repository.findById(id));
	}
	
}
