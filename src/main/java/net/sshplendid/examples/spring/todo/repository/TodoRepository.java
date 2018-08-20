package net.sshplendid.examples.spring.todo.repository;

import net.sshplendid.examples.spring.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByCompletionTrue();
    List<Todo> findByCompletionFalse();
}
