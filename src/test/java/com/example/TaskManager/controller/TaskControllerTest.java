package com.example.TaskManager.controller;
import com.example.TaskManager.model.Task;
import com.example.TaskManager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TaskRepository taskRepository;

    // @Autowired
    // private JwtProvider jwtProvider;

    @Test
    void testGetAllTasksReturnsTasks() {
        // Arrange: Save a sample task to DB
        Task task = new Task();
        task.setTitle("Sample");
        task.setDescription("Sample Desc");
        taskRepository.save(task);

        JwtProvider jwtProvider = new JwtProvider();

        String jwt = jwtProvider.getJwtToken("sas", "s", port, restTemplate);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwt);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Act: Call endpoint
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/tasks/all", HttpMethod.GET, entity, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Sample");
        System.out.println("This is working fine");
    }
}
