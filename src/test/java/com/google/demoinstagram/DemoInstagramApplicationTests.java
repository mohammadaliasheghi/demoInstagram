package com.google.demoinstagram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.demoinstagram.entity.Users;
import com.google.demoinstagram.restController.UsersRestController;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // For restTemplate
@ActiveProfiles("test")
class DemoInstagramApplicationTests {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsersRestController usersRestController;

    @Test
    void contextLoads() {
        assertThat(usersRestController).isNotNull();
    }

    @Test
    @DisplayName("shouldBeCreateUser - /api/users/register")
    public void shouldBeCreateUser() throws JsonProcessingException, JSONException {
        Users users_one = new Users();
        users_one.setId(1L);
        users_one.setUsername("mohammad_ali");
        users_one.setEmail("mohammad_ali@gmail.com");
        users_one.setNumber("09901234567");
        users_one.setPassword("mohammad_ali");

        String expected = om.writeValueAsString(users_one);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register", users_one, String.class);
        assertThat(HttpStatus.CREATED).isEqualTo(response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
}
