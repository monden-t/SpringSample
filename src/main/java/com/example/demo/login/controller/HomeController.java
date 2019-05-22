package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final UserService userService;

	@GetMapping("/home")
	public String getHome(Model model) {
		model.addAttribute("contents", "home/home::home_contents");
		return "home/homeLayout";
	}

	@PostMapping("/logout")
	public String postLogout() {
		return "redirect:/login";
	}
}
