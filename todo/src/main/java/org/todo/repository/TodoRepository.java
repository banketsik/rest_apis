package org.todo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.todo.entity.Todo;
import org.todo.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findByOwner(User owner);
    Optional<Todo> findByIdAndOwner(Long id, User owner);
}
