package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.login.domain.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final UserService userService;
	private static final String HOME_PATH = "home/homeLayout";

	@GetMapping("/home")
	public String getHome(Model model) {
		model.addAttribute("contents", "home/home::home_contents");
		return HOME_PATH;
	}

	@GetMapping("/userList")
	public String getUserList(Model model) {
		model.addAttribute("contents", "home/userList::userList_contents");
		model.addAttribute("userList", userService.selectAll());
		model.addAttribute("userListCount", userService.count());
		return HOME_PATH;
	}

	@GetMapping("/logout")
	public String postLogout() {
		return "redirect:/login";
	}

	@GetMapping("/userList/csv")
	public String getUserListCsv(Model model) {
		return getUserList(model);
	}
}
