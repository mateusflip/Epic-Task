package br.com.fiap.epictask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.epictask.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	//Select ... where  title LIKE %criar%
	List<Task> findByTitleLike(String title);

}
