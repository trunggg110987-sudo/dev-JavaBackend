package com.example.todoapp.mapper;

import com.example.todoapp.dto.TodoResponse;
import com.example.todoapp.model.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public TodoResponse toResponse (Todo todo){
        TodoResponse response = new TodoResponse();
        response.setId(todo.getId());
        response.setTitle(todo.getTitle());
        response.setDescription(todo.getDescription());
        response.setCompleted(todo.isCompleted());
        return response;
    }
}
