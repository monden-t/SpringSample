package com.example.demo.login.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.RestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserRestController {
	private final RestService service;

	@GetMapping("/rest/get")
	public List<User> getUserAll() {
		return service.selectAll();
	}

	@GetMapping("/rest/get/{id:.+}")
	public User getUserOne(@PathVariable("id") String userId) {
		return service.selectOne(userId);
	}

	/*
	 * test command -> curl -X POST -H "Content-Type:application/json" -d
	 * "{\"userId\": \"test3@test.com\", \"password\": \ "password\", \"userName\":
	 * \"testttt\", \"birthday\": \"1990-11-01\",\"age\": \"29\",\"marriage\": \"f
	 * alse\", \"role\": \"ROLE_ADMIN\"}" http://localhost:8080/rest/insert
	 */
	@PostMapping("/rest/insert")
	public String postUserOne(@RequestBody User user) {
		if (service.insert(user)) {
			return "{\"result\":\"success\"}";
		} else {
			return "{\"result\":\"failed\"}";
		}
	}

	/*
	 * test command -> curl -X PUT -H "Content-Type:application/json" -d
	 * "{\"userId\": \"test@test.com\", \"password\": \"p
	 * assword\", \"userName\": \"testttt\", \"birthday\": \"1990-11-01\",\"age\": \"29\",\"marriage\": \"
	 * fal se\", \"role\": \"ROLE_ADMIN\"}" http://localhost:8080/rest/update
	 */
	@PutMapping("/rest/update")
	public String putUserOne(@RequestBody User user) {
		if (service.update(user)) {
			return "{\"result\":\"success\"}";
		} else {
			return "{\"result\":\"failed\"}";
		}
	}

	/*
	 * test command -> curl http://localhost:8080/rest/delete/test@test.com -X
	 * DELETE
	 */
	@DeleteMapping("/rest/delete/{id:.+}")
	public String deleteUserOne(@PathVariable("id") String userId) {
		if (service.delete(userId)) {
			return "{\"result\":\"success\"}";
		} else {
			return "{\"result\":\"failed\"}";
		}
	}

}
