package sda.soft.academy.spring.ordersapp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sda.soft.academy.spring.ordersapp.dto.UserDto;

@Component
public class SamePasswordsValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UserDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto userDto = (UserDto) o;
        if (!userDto.getPassword1().equals(userDto.getPassword2())) {
            errors.rejectValue("password1", "user.registration.validation.message.passwordsAreNotTheSame");
        }
    }
}
