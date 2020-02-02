package pl.strefakursow.security.attacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.strefakursow.security.attacks.sqlinjection.User;
import pl.strefakursow.security.attacks.sqlinjection.UserDao;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SqlInjectionTests {
	@Autowired
	private UserDao userDao;

	@BeforeEach
	void beforeEach() {
		userDao.deleteAll();
	}

	// @formatter:off
	@DisplayName(
		"given 4 users, " +
		"when call unsafe query method using SQL injection attack, " +
		"then all 4 users are returned"
	)
	// @formatter:on
	@Test
	void unsafe() throws Exception {
		// given
		userDao.save(new User("goobar"), new User("foobar"),
			new User("hoobar"), new User("goobar"));

		// when
		Collection<User> allUsers = userDao
			.unsafeFindByName("' OR '1'='1");

		// then
		assertThat(allUsers).hasSize(4);
	}

	// @formatter:off
	@DisplayName(
		"given 4 users, " +
		"when call safe query method using SQL injection attack, " +
		"then no users are returned"
	)
	// @formatter:on
	@Test
	void safe() throws Exception {
		// given
		userDao.save(new User("goobar"), new User("foobar"),
			new User("hoobar"), new User("goobar"));

		// when
		Collection<User> allUsers = userDao
			.safeFindByName("' OR '1'='1");

		// then
		assertThat(allUsers).isEmpty();
	}
}
