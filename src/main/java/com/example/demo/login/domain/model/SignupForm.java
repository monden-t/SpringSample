package com.example.demo.login.domain.model;

import java.util.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.login.domain.validator.FormatCheck;
import com.example.demo.login.domain.validator.PasswordCheck;
import com.example.demo.login.domain.validator.RequireCheck;

import lombok.Data;

@Data
public class SignupForm {

	@NotBlank(groups = RequireCheck.class)
	@Email(groups = FormatCheck.class)
	private String userId;

	@NotBlank(groups = RequireCheck.class)
	@Length(min = 4, max = 32, groups = FormatCheck.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = PasswordCheck.class)
	private String password;

	@NotBlank(groups = RequireCheck.class)
	private String userName;

	@NotNull(groups = RequireCheck.class)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;

	@Min(value = 20, groups = FormatCheck.class)
	@Max(value = 100, groups = FormatCheck.class)
	private int age;

	@AssertFalse(groups = FormatCheck.class)
	private boolean marriage;
}
