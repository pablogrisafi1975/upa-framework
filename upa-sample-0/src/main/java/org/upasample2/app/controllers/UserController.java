package org.upasample2.app.controllers;

import java.util.Arrays;
import java.util.List;

import org.upasample0.app.domain.User;

public class UserController {

	public List<User> findAll() {
		User user1 = new User();
		user1.setFirstName("firstName1");
		user1.setLastName("lastName1");
		user1.setLoginName("loginName1");

		User user2 = new User();
		user2.setFirstName("firstName2");
		user2.setLastName("lastName2");
		user2.setLoginName("loginName2");

		return Arrays.asList(user1, user2);
	}
}
