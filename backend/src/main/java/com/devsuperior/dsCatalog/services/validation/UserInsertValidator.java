package com.devsuperior.dsCatalog.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.devsuperior.dsCatalog.dto.UserInsertDTO;
import com.devsuperior.dsCatalog.entities.User;
import com.devsuperior.dsCatalog.repositories.UserRepository;
import com.devsuperior.dsCatalog.resources.exceptions.FieldMessage;

import org.springframework.beans.factory.annotation.Autowired;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {
    
    @Autowired
    private UserRepository userRepository;

	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
        
        User user = userRepository.findByEmail(dto.getEmail());
        if(user!=null){
            list.add(new FieldMessage("email", "Email já existe"));
        }

        

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}