package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;
import com.example.demo.login.domain.validator.ValidationGroupOrder;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignupController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final UserService userService;

	// General User Role
	private static final String ROLE_GENERAL = "ROLE_GENERAL";
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
		// TODO password mast be hashed
		logger.debug(form.toString());

		User user = new User();
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		user.setBirthday(form.getBirthday());
		user.setAge(form.getAge());
		user.setMarriage(form.isMarriage());
		user.setRole(ROLE_GENERAL);

		if (userService.insert(user)) {
			logger.info("User registration success !");
		} else {
			logger.error("User registration failed !");
		}

		return "redirect:/login";
	}

	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		model.addAttribute("error", "Internal Server Error(Data Base):ExceptionHandler");
		model.addAttribute("message", "DataAccessException occurs in " + this.getClass().getSimpleName());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		return "error";
	}

//	@ExceptionHandler(Exception.class)
//	public String exceptionHandler(Exception e, Model model) {
//		model.addAttribute("error", "Internal Server Error:ExceptionHandler");
//		model.addAttribute("message", "Exception occurs in " + this.getClass().getSimpleName());
//		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
//		return "error";
//	}

	private Map<String, String> initRadioMarriage() {
		Map<String, String> radio = new LinkedHashMap<>();
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		return radio;
	}
}
