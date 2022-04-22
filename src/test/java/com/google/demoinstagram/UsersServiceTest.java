package com.google.demoinstagram;

import com.google.demoinstagram.entity.Users;
import com.google.demoinstagram.service.UsersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@ActiveProfiles("test")
public class UsersServiceTest {

    @Autowired
    private UsersService usersService;

    @Test
    public void shouldBeCreateUser() throws Exception {
        Users users_one = new Users();
        users_one.setId(1L);
        users_one.setUsername("mohammad_ali");
        users_one.setEmail("mohammad_ali@gmail.com");
        users_one.setNumber("09901234567");
        users_one.setPassword("mohammad_ali");

        usersService.saveUser(users_one);
        Assertions.assertNotNull(usersService.getUserById(1L).getId());
    }

    @Test
    public void shouldBeUpdateUser() throws Exception {
        Users users_one = new Users();
        users_one.setId(1L);
        users_one.setUsername("mohammad_ali");
        users_one.setEmail("mohammad_ali@gmail.com");
        users_one.setNumber("09901234567");
        users_one.setPassword("mohammad_ali");
        usersService.saveUser(users_one);
        Users users = usersService.getUserById(1L);
        users.setUsername("mohsen");
        usersService.updateUser(users, users_one.getId());
        assertNotEquals(users_one.getUsername(), usersService.getUserById(1L).getUsername());
    }

    @Test
    public void shouldBeFindAllUsers() throws Exception {
        Users users_one = new Users();
        users_one.setId(1L);
        users_one.setUsername("mohammad_ali");
        users_one.setEmail("mohammad_ali@gmail.com");
        users_one.setNumber("09901234567");
        users_one.setPassword("mohammad_ali");

        usersService.saveUser(users_one);

        Users users_two = new Users();
        users_two.setId(2L);
        users_two.setUsername("mohammad_reza");
        users_two.setEmail("mohammad_reza@gmail.com");
        users_two.setNumber("09901234567");
        users_two.setPassword("mohammad_reza");

        usersService.saveUser(users_two);
        List<Users> usersList = usersService.getAllUser();
        assertThat(usersList).size().isGreaterThan(0);
    }

    @Test
    public void shouldBeFindUserByUsername() throws Exception {
        Users users_one = new Users();
        users_one.setId(1L);
        users_one.setUsername("mohammad_ali");
        users_one.setEmail("mohammad_ali@gmail.com");
        users_one.setNumber("09901234567");
        users_one.setPassword("mohammad_ali");

        usersService.saveUser(users_one);
        String username = usersService.getUserById(1L).getUsername();
        assertEquals(users_one.getUsername(), username);
    }

    @Test
    public void shouldBeFindById() throws Exception {
        Users users_one = new Users();
        users_one.setId(1L);
        users_one.setUsername("mohammad_ali");
        users_one.setEmail("mohammad_ali@gmail.com");
        users_one.setNumber("09901234567");
        users_one.setPassword("mohammad_ali");

        usersService.saveUser(users_one);
        Long userId = usersService.getUserById(1L).getId();
        assertEquals(users_one.getId(), userId);
    }

    @Test
    public void shouldBeDeleteById() throws Exception {
        Users users_one = new Users();
        users_one.setId(1L);
        users_one.setUsername("mohammad_ali");
        users_one.setEmail("mohammad_ali@gmail.com");
        users_one.setNumber("09901234567");
        users_one.setPassword("mohammad_ali");

        usersService.saveUser(users_one);
        usersService.deleteUser(users_one.getId());
        assertThat(usersService.existUsersById(1L)).isFalse();
    }

}
