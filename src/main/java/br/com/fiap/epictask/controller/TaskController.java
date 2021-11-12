package br.com.fiap.epictask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.repository.TaskRepository;

@Controller
public class TaskController {
	
	@Autowired
	private TaskRepository repository;
	
	
	@GetMapping("/task")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("tasks"); //Nome do arquivo html que quero retornar
		List<Task> tasks = repository.findAll(); 
		modelAndView.addObject("tasks", tasks);
		return modelAndView;
	}
	
	@RequestMapping("/task/new")
	public String create(Task task) {
		return "task-form";
	}
	
	
	
	@PostMapping("/task")
	public String save(@Valid Task task, BindingResult result, RedirectAttributes redirect) { //Valid irá implantar as regras de validação que setamos no Model
		if(result.hasErrors()) {
			return "task-form";
		}
		repository.save(task);
		redirect.addFlashAttribute("message", "Tarefa cadastrada com sucesso");
		
		return "redirect:task"; //Endereço da requisição
	}
}
