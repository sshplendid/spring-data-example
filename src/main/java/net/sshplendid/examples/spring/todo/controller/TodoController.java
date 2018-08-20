package net.sshplendid.examples.spring.todo.controller;

import net.sshplendid.examples.spring.todo.entity.Todo;
import net.sshplendid.examples.spring.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TodoController {

    private TodoRepository repository;

    @Autowired
    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/todos", consumes = "application/json")
    public List<Todo> getTodos() {
        return repository.findAll();
    }
    @GetMapping(value = "/todos/{id}", consumes = "application/json")
    public Todo getTodoById(Long id) {
        Todo result = repository.findById(id).get();
        return result;
    }
    @GetMapping(value = "/todos", consumes = "text/html")
    public ModelAndView getTodosPage() {
        Map<String, Object> model = new HashMap<>();
        model.put("todos", repository.findAll());

        return new ModelAndView("todosView", model);
    }

    @PostMapping("/todos")
    public Todo createTodo(String content) {
        Todo.Builder builder = new Todo.Builder();
        Todo todo = builder.content(content).completion(false).build();
        return repository.save(todo);
    }

    @PatchMapping("/todos/{id}")
    public Todo updateTodo(Todo todo) {
        return repository.save(todo);
    }
}
