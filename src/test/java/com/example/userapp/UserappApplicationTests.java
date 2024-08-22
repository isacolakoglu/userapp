package com.example.userapp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserappApplicationTests {

	@Test
	public void testAddition() {
		int sum = 2 + 3;
		assertEquals(5, sum, "2 + 3 should equal 5");
	}

	@Test
	void contextLoads() {
		// Test, Spring konteksinin yüklenip yüklenmediğini kontrol eder.
	}
}
