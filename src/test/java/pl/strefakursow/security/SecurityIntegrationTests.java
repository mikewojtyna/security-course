package pl.strefakursow.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityIntegrationTests {
	@Autowired
	private MockMvc mockMvc;

	// @formatter:off
	@DisplayName(
		"given no credentials, " +
		"when GET on /secured-basic, " +
		"then status is unauthorized"
	)
	// @formatter:on
	@Test
	void basicAuthNegative() throws Exception {
		// when
		mockMvc.perform(get("/secured-basic"))

			// then
			.andExpect(status().isUnauthorized());
	}

	// @formatter:off
	@DisplayName(
		"given user:user basic auth credentials, " +
		"when GET on /secured-basic, " +
		"then status is OK"
	)
	// @formatter:on
	@Test
	void basicAuthPositive() throws Exception {
		// when
		mockMvc.perform(
			get("/secured-basic").with(httpBasic("user", "user")))

			// then
			.andExpect(status().isOk());
	}

	// @formatter:off
	@DisplayName(
		"given mock user, " +
		"when GET on /secured-basic, " +
		"then status is OK"
	)
	// @formatter:on
	@Test
	@WithMockUser
	void mockUser() throws Exception {
		// when
		mockMvc.perform(get("/secured-basic"))

			// then
			.andExpect(status().isOk());
	}
}
