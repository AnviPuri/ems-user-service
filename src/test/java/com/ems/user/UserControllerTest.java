package com.ems.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.ems.user.controller.UserController;
import com.ems.user.dto.builder.UserRequestBuilder;
import com.ems.user.dto.request.UserRequest;
import com.ems.user.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserServiceImpl userServiceImpl;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void givenUserObject_whenCreateUser_thenReturnSavedUser() throws Exception {

		UserRequest userRequest = new UserRequestBuilder().with($_user -> {
			$_user.userType = "EMPLOYEE";
			$_user.firstName = "Meena";
			$_user.personalEmail = "m@gmail.com";
			$_user.countryCode = "91";
			$_user.phoneNumber = "009392920202";
			$_user.relationship = "SELF";
			$_user.status = "ACTIVE";
		}).createUserRequest();

		ResultActions response = mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userRequest)));

		response.andDo(print()).andExpect(status().isOk());

	}
}
