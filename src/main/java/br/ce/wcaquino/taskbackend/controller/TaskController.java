package br.ce.wcaquino.taskbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.DateUtils;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

@RestController
@RequestMapping(value ="/todo")
public class TaskController {

	@Autowired
	private TaskRepo taskRepo;
	
	@GetMapping
	public List<Task> findAll() {
		return taskRepo.findAll();
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		try {
			taskRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody Task todo) throws ValidationException {
		try {
			if(todo.getTask() == null || todo.getTask().equals("")) {
				throw new ValidationException("Fill the task description");
			}
			if(todo.getDueDate() == null) {
				throw new ValidationException("Fill the due date");
			}
			if(!DateUtils.isEqualOrFutureDate(todo.getDueDate())) {
				throw new ValidationException("Due date must not be in past");
			}
			Task saved = taskRepo.save(todo);
			return new ResponseEntity<>(saved, HttpStatus.CREATED);			
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}
}
