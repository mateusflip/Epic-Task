package br.com.fiap.epictask.controller.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public Page<Task> index(@RequestParam(required= false) String title,
			@PageableDefault Pageable pageable
			) {
		if(title == null) {
			return repository.findAll(pageable);
		}
		return repository.findByTitleLike("%" + title + "%", pageable);
	}
	
	@PostMapping
	public ResponseEntity<Task> create(
			@RequestBody @Valid Task task,
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
	
	//Update
	@PutMapping("{id}")
	public ResponseEntity<Task> update(@PathVariable Long id,
			@RequestBody @Valid Task newTask){
		Optional<Task> optional = repository.findById(id);
		
		if(optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Task task = optional .get();
		task.setTitle(newTask.getTitle());
		task.setDescription(newTask.getDescription());
		task.setPoints(newTask.getPoints());
		
		repository.save(task);
		
		return ResponseEntity.ok(task);
	}
	
	//Deletar por id na api
	@DeleteMapping("{id}")
	public ResponseEntity<Task> destroy(@PathVariable Long id){
		Optional<Task> task = repository.findById(id);
		
		if(task.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
}
