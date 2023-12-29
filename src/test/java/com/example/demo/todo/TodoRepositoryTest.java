package com.example.demo.todo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testFindByUsername() {
        // Given
        Todo todo1 = new Todo(1, "user1", "Task 1 ,tenChar", LocalDate.now(), false);
        Todo todo2 = new Todo(2, "user1", "Task 2 ,tenChar", LocalDate.now(), true);
        Todo todo3 = new Todo(3, "user2", "Task 3 ,tenChar", LocalDate.now(), false);

        todoRepository.saveAll(List.of(todo1, todo2, todo3));

        // When
        List<Todo> todosForUser1 = todoRepository.findByUsername("user1");
        List<Todo> todosForUser2 = todoRepository.findByUsername("user2");

        // Then
        assertEquals(2, todosForUser1.size());
        assertEquals(1, todosForUser2.size());
    }

}
