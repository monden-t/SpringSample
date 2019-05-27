package com.example.demo.login.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	public ResponseEntity<byte[]> getUserListCsv(Model model) {
		userService.userCsvOut();
		byte[] bytes = null;
		try {
			bytes = userService.getFile("sample.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/csv; charset=UTF-8");
		header.setContentDispositionFormData("filename", "sample.csv");
		return new ResponseEntity<>(bytes, header, HttpStatus.OK);
	}

	@GetMapping("/userDetail/{id:.+}")
	public String getUserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id") String userId) {
		model.addAttribute("contents", "home/userDetail::userDetail_contents");
		radioMarriage = initRadioMarriage();
		model.addAttribute("radioMarriage", radioMarriage);
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

	@PostMapping(value = "/userDetail", params = "update")
	public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {
		User user = new User();
		user.setUserId(form.getUserId());
		user.setUserName(form.getUserName());
		user.setBirthday(form.getBirthday());
		user.setAge(form.getAge());
		user.setMarriage(form.isMarriage());
		try {
			if (userService.updateOne(user)) {
				model.addAttribute("result", "Update Success!");
			} else {
				model.addAttribute("result", "Update Failed!");
			}
		} catch (DataAccessException e) {
			model.addAttribute("result", "Update Failed!");
			throw e;
		}
		return getUserList(model);
	}

	@PostMapping(value = "/userDetail", params = "delete")
	public String postUserDelete(@ModelAttribute SignupForm form, Model model) {
		if (userService.deleteOne(form.getUserId())) {
			model.addAttribute("result", "Delete Success!");
		} else {
			model.addAttribute("result", "Delete Failed!");
		}
		return getUserList(model);
	}

	@GetMapping("/admin")
	public String getAdmin(Model model) {
		model.addAttribute("contents", "login/adming :: admin_contents");
		return HOME_PATH;
	}

	private Map<String, String> initRadioMarriage() {
		Map<String, String> radio = new LinkedHashMap<>();
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		return radio;
	}
}
