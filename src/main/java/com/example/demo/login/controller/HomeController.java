package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private static final String HOME_PATH = "home/homeLayout";

	private final UserService userService;
	private Map<String, String> radioMarriage;

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

	@GetMapping("/userDetail/{id:.+}")
	public String getUserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id") String userId) {
		model.addAttribute("contents", "home/userDetail::userDetai_contents");
		radioMarriage = initRadioMarriage();
		if (!StringUtils.isEmpty(userId)) {
			User user = userService.selectOne(userId);
			form.setUserId(user.getUserId());
			form.setUserName(user.getUserName());
			form.setBirthday(user.getBirthday());
			form.setAge(user.getAge());
			form.setMarriage(user.isMarriage());

			model.addAttribute("signupForm", form);
		}
		return HOME_PATH;
	}

	private Map<String, String> initRadioMarriage() {
		Map<String, String> radio = new LinkedHashMap<>();
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		return radio;
	}
}
