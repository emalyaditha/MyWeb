package com.example.GoCheeta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class GoCheetaApplicationTests {

	@Autowired private CustomerRepository repo;

//	@Test
//	public void testAddNew() {
//		Customer user = new Customer();
//		user.setEmail("waaae@gmail.com");
//		user.setPassword("hi1234");
//		user.setFirstName("Asura");
//		user.setLastName("Daito");
//
//		Customer savedUser = repo.save(user);
//
//		Assertions.assertThat(savedUser).isNotNull();
//		Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
//
//	}
}
