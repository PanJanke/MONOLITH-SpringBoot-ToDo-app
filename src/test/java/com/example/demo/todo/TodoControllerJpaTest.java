package com.example.demo.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerJpaTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoRepository todoRepository;

    @MockBean
    private TodoService todoService;

    @Test
    public void testListAllTodos() throws Exception {

        Todo todo1 = new Todo(1, "user1", "Task 1", LocalDate.now(), false);
        Todo todo2 = new Todo(2, "user1", "Task 2", LocalDate.now(), true);
        List<Todo> todoList = Arrays.asList(todo1, todo2);

        when(todoRepository.findByUsername("user1")).thenReturn(todoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/list-todos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("todos"))
                .andExpect(MockMvcResultMatchers.view().name("listTodos"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/listTodos.jsp"))
                .andReturn();
    }

    @Test
    public void testAddNewTodo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/add-todo")
                        .param("username", "admin")
                        .param("description", "New Task,Ten chars")
                        .param("targetDate", LocalDate.now().toString())
                        .param("done", "false"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());


        verify(todoService).addTodo("admin", "New Task,Ten chars", LocalDate.now(), false);
    }

    @Test
    public void testDeleteTodo() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/delete-todo").param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("list-todos"));


        verify(todoRepository).deleteById(1);
    }

    @Test
    public void testUpdateTodo() throws Exception {

        Todo todoToUpdate = new Todo(1, "admin", "Updated Task", LocalDate.now(), true);


        mockMvc.perform(MockMvcRequestBuilders.post("/update-todo")
                        .param("id", "1")
                        .param("username", "admin")
                        .param("description", "Updated Task")
                        .param("targetDate", LocalDate.now().toString())
                        .param("done", "true"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("list-todos"));


        verify(todoService).updateTodo(todoToUpdate);
    }

}
