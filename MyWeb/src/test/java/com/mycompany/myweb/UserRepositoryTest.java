package com.mycompany.myweb;
import com.mycompany.myweb.user.LoginRepository;
import com.mycompany.myweb.user.User;
import com.mycompany.myweb.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;
    @Autowired private LoginRepository repo1;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("waaae@gmail.com");
        user.setPassword("hi1234");
        user.setFirstName("Asura");
        user.setLastName("Daito");

        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

    }
    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testUpdate() {
        Integer userId =1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("hello12345");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("hello12345");
    }
    @Test
    public void testGet() {
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println((optionalUser.get()));
    }
    @Test
    public void testDelete() {
        Integer userId = 14;
        repo.deleteById(userId);

        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
//    @Test
//    public void testFindUserByEmail() {
//        String email = "emal@gmail.com";
//
//        User user = repo1.findByEmail(email);
//
//        assertThat(user).isNotNull();
//    }
}
