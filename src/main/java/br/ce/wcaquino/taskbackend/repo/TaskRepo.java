package br.ce.wcaquino.taskbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ce.wcaquino.taskbackend.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long>{

}
