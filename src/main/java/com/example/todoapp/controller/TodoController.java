package com.example.todoapp.controller;

import com.example.todoapp.dto.*;
import com.example.todoapp.mapper.TodoMapper;
import com.example.todoapp.model.Todo;
import com.example.todoapp.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ApiResponse<PageResponse<TodoResponse>> findAll(Pageable pageable){
        Page<TodoResponse> page = todoService.findAll(pageable);
        PageResponse<TodoResponse> response = new PageResponse<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
        return new ApiResponse<>(200, "success", response);
    }

    @PostMapping
    public ApiResponse<TodoResponse> add(@Valid @RequestBody TodoRequest request){
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setCompleted(request.isCompleted());

        TodoResponse response = todoService.save(todo);

        return new ApiResponse<>(200, "success", response);
    }

    @GetMapping("/{id}")
    public ApiResponse<TodoResponse> findById(@PathVariable long id){
        return new ApiResponse<>(200, "success", todoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable long id){
        todoService.deleteById(id);
        return "deleted successfully";
    }

    @PutMapping("/{id}")
    public Todo updateById(@PathVariable long id,@RequestBody Todo updatedTodo){
        return todoService.updateById(id, updatedTodo);
    }

    private TodoMapper todoMapper = new TodoMapper();
    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }
    @GetMapping("/filter")
    public ApiResponse<PageResponse<TodoResponse>> filter (@RequestParam(required = false) Boolean completed, @RequestParam(required = false) String keyword, Pageable pageable, @PathVariable String filter){
        Page<Todo> page = todoService.filter(completed, keyword, pageable);

        PageResponse<TodoResponse> response = new PageResponse<>(
                page.getContent().stream().map(todoMapper::toResponse).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages());
        return new ApiResponse<>(200, "success", response);
    }

    @PatchMapping("/{id}")
    public ApiResponse<TodoResponse> update(@PathVariable long id, @RequestBody TodoUpdateRequest request){
        Todo updateTodo = todoService.update(id, request);
        TodoResponse response = todoMapper.toResponse(updateTodo);
        return new ApiResponse<>(200, "success", response);
    }
}
