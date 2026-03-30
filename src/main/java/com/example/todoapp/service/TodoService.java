package com.example.todoapp.service;

import com.example.todoapp.dto.TodoResponse;
import com.example.todoapp.dto.TodoUpdateRequest;
import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;
import com.example.todoapp.repository.TodoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll(){
        return todoRepository.findAll();
    }

    public TodoResponse save(Todo todo){
        Todo saved = todoRepository.save(todo);
        return new TodoResponse(saved.getId(), saved.getTitle(), saved.getDescription(), saved.isCompleted());
    }

    public TodoResponse findById(long id){
        Optional<Todo> optional = todoRepository.findById(id);

        if(optional.isPresent()){
            Todo t = optional.get();
            return new TodoResponse(t.getId(), t.getTitle(), t.getDescription(), t.isCompleted());
        }else{
            throw new RuntimeException("todo not found");
        }
    }

    public void deleteById(long id){
        if(todoRepository.existsById(id)){
            todoRepository.deleteById(id);
        }else{
            throw new RuntimeException("id is not found");
        }
    }

    public Todo updateById(long id, Todo updatedTodo){
        Optional<Todo> optional = todoRepository.findById(id);
        if(optional.isPresent()){
            Todo existingTodo = optional.get();

            existingTodo.setTitle(updatedTodo.getTitle());
            existingTodo.setDescription(updatedTodo.getDescription());
            existingTodo.setCompleted(updatedTodo.isCompleted());

            return todoRepository.save(existingTodo);
        }else{
            throw new RuntimeException("id is not found");
        }
    }

    public Page<TodoResponse> findAll(Pageable pageable){
        Page<Todo> page = todoRepository.findAll(pageable);

        return page.map(todo -> new TodoResponse(todo.getId(), todo.getTitle(), todo.getDescription(), todo.isCompleted()));
    }

    public Page<Todo> filter(Boolean completed,String keyword, Pageable pageable){
        Specification<Todo> spec = Specification.where(TodoSpecification.hasCompleted(completed)).and(TodoSpecification.hasKeyWord(keyword));
        return todoRepository.findAll(spec, pageable);
    }

    public Todo update(long id, TodoUpdateRequest request) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        if(request.getTitle() != null){
            todo.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            todo.setDescription(request.getDescription());
        }

        if (request.getCompleted() != null) {
            todo.setCompleted(request.getCompleted());
        }
        return todoRepository.save(todo);
    }
}
