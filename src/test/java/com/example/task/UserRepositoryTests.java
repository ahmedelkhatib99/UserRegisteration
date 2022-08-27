package com.example.task;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("omar");
        user.setPassword("123456");
        user.setEmail("omar@gmail.com");
        user.setPhone("01111111111");
        user.setFirstName("omar");
        user.setLastName("khaled");
        User savedUser = repo.save(user);

        User existingUser = entityManager.find(User.class, user.getId());

        assertThat(existingUser.getUsername()).isEqualTo(savedUser.getUsername());
    }

    @Test
    public void testFindUserByUsername() {
        User user = new User();
        user.setUsername("ahmed");
        user.setPassword("123456");
        user.setEmail("ahmed@gmail.com");
        user.setPhone("01111111111");
        user.setFirstName("ahmed");
        user.setLastName("youssef");
        User savedUser = repo.save(user);

        String username = "ahmed";

        User searchUser = repo.findByUsername(username);

        assertThat(searchUser).isNotNull();
    }

}
