package com.example.demo.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;
    private TodoService underTest;

    @BeforeEach
    void setUp() {
        underTest = new TodoService(todoRepository);
    }


    @Test
    void addTodo() {
        //given
        Todo todo = new Todo(
                1,
                "user1",
                "Task 1 ,tenChar",
                LocalDate.now(),
                false
        );
        //when
        underTest.addTodo(todo);

        //then
        ArgumentCaptor<Todo> todoArgumentCaptor = ArgumentCaptor.forClass(Todo.class);

        verify(todoRepository).save(todoArgumentCaptor.capture());

        Todo captureTodo = todoArgumentCaptor.getValue();
        assertThat(captureTodo).isEqualTo(todo);
    }

    @Test
    void deleteById() {

    }

    @Test
    void findById() {

    }

    @Test
    void updateTodo() {
    }

    @Test
    void findByUsername() {

    }


}