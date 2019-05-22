package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.validator.ValidationGroupOrder;

@Controller
public class SignupController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private Map<String, String> radioMarriage;

	@GetMapping("/signup")
	public String getSignUp(@ModelAttribute SignupForm form, Model model) {
		radioMarriage = initRadioMarriage();
		model.addAttribute("radioMarriage", radioMarriage);
		return "login/signup";
	}

	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute @Validated(ValidationGroupOrder.class) SignupForm form,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return getSignUp(form, model);
		}
		logger.info(form.toString());
		return "redirect:/login";
	}

	private Map<String, String> initRadioMarriage() {
		Map<String, String> radio = new LinkedHashMap<>();
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		return radio;
	}
}
