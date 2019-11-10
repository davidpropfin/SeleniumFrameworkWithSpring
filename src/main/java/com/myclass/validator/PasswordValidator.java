package com.myclass.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.myclass.DTO.SignUpDTO;

@Component
public class PasswordValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return SignUpDTO.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		// ép kiểu đối tượng truyền vào để validate
		SignUpDTO signUpDTO = (SignUpDTO) target;
		if (signUpDTO.getConfirm() == null || signUpDTO.getConfirm().length() == 0) {
			errors.rejectValue(signUpDTO.getConfirm(), "nhập khớp khớp xíu má ôi!");
		}

	}

}
