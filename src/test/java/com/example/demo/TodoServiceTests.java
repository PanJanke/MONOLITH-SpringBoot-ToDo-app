package com.example.demo;

import com.example.demo.todo.Todo;
import com.example.demo.todo.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;

class TodoServiceTests {

    @Mock
    private List<Todo> mockTodos;

    @InjectMocks
    private TodoService todoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTodo() {
        // Given
        String username = "testUser";
        String description = "Test Description";
        LocalDate targetDate = LocalDate.now().plusDays(7);
        boolean done = false;

        // When
        todoService.addTodo(username, description, targetDate, done);

        // Then
        verify(mockTodos, times(1)).add(any(Todo.class));
    }

    @Test
    void testDeleteById() {
        // Given
        int todoId = 1;

        // When
        todoService.deleteById(todoId);

        // Then
        verify(mockTodos, times(1)).removeIf(any());
    }

    @Test
    void testFindById() {
        // Given
        int todoId = 1;

        // When
        when(mockTodos.stream()).thenReturn(mockTodos.stream()); // mock the stream for testing
        when(mockTodos.stream().filter(any())).thenReturn(mockTodos.stream());
        when(mockTodos.stream().filter(any()).findFirst()).thenReturn(java.util.Optional.of(new Todo()));

        Todo result = todoService.findById(todoId);

        // Then
        verify(mockTodos, times(1)).stream();
        verify(mockTodos, times(1)).stream().filter(any());
        verify(mockTodos, times(1)).stream().filter(any()).findFirst();
        // Add assertions for the result if needed
    }

    @Test
    void testUpdateTodo() {
        // Given
        Todo todoToUpdate = new Todo(1, "testUser", "Updated Description", LocalDate.now(), true);

        // When
        when(mockTodos.removeIf(any())).thenReturn(true);
        when(mockTodos.add(any(Todo.class))).thenReturn(true);

        todoService.updateTodo(todoToUpdate);

        // Then
        verify(mockTodos, times(1)).removeIf(any());
        verify(mockTodos, times(1)).add(any(Todo.class));
    }

    @Test
    void testFindByUsername() {
        // Given
        String username = "testUser";

        // When
        when(mockTodos.stream()).thenReturn(mockTodos.stream()); // mock the stream for testing
        when(mockTodos.stream().filter(any())).thenReturn(mockTodos.stream());
        when(mockTodos.stream().filter(any()).toList()).thenReturn(mockTodos);

        List<Todo> result = todoService.findByUsername(username);

        // Then
        verify(mockTodos, times(1)).stream();
        verify(mockTodos, times(1)).stream().filter(any());
        verify(mockTodos, times(1)).stream().filter(any()).toList();
        // Add assertions for the result if needed
    }
}

