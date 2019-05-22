package com.example.demo.login.domain.validator;

import javax.validation.GroupSequence;

@GroupSequence({ RequireCheck.class, FormatCheck.class, PasswordCheck.class })
public interface ValidationGroupOrder {

}
