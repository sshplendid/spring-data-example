package net.sshplendid.examples.spring.todo.controller;

import net.sshplendid.examples.spring.todo.entity.Todo;
import net.sshplendid.examples.spring.todo.repository.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TodoController.class)
public class TodoControllerTest {
    private static final Logger log = LoggerFactory.getLogger(TodoControllerTest.class);

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TodoRepository repository;

    @Before
    public void setUp() {

    }

    @Test
    public void getTodos_shouldReturnOk() throws Exception {
        List<Todo> mockList = new ArrayList<>();
        mockList.add(new Todo("빨래", new Date(System.currentTimeMillis() - 1000*60*60*24*3), new Date(), false));
        mockList.add(new Todo("청소", new Date(System.currentTimeMillis() - 1000*60*60*24*7), new Date(), false));
        mockList.add(new Todo("고양이 화장실 정리", new Date(System.currentTimeMillis() - 1000*60*60*24*25), new Date(), false));
        mockList.add(new Todo("설거지", new Date(System.currentTimeMillis() - 1000*60*60*24*31), new Date(), false));

        when(repository.findAll()).thenReturn(mockList);

        mockMvc.perform(get("/todos")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
