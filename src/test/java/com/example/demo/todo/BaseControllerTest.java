package com.example.demo.todo;
import org.springframework.security.test.context.support.WithMockUser;

@WithMockUser(username = "admin", password = "admin", roles = "USER")
public abstract class BaseControllerTest {
}
