package it.unibz.digidojo.activityplannerservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unibz.digidojo.activityplannerservice.domain.model.task.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}