package com.example.todoapp.repository;

import com.example.todoapp.model.Todo;
import org.springframework.data.jpa.domain.Specification;

public class TodoSpecification {

    public static Specification<Todo> hasCompleted(Boolean completed) {
        return (root, query, criteriaBuilder) -> {
            if (completed == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("completed"), completed);
        };
    }

    public static Specification<Todo> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null || title.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("title"), "%" + title + "%");
        };
    }

    public static Specification<Todo> hasKeyWord(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if(keyword == null || keyword.isEmpty()){
                return null;
            }
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("title"), "%" + keyword + "%"),
                    criteriaBuilder.like(root.get("description"), "%" + keyword + "%")
            );
        };
    }
}
