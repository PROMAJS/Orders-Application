package sda.soft.academy.spring.ordersapp.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sda.soft.academy.spring.ordersapp.dto.UserDto;
import sda.soft.academy.spring.ordersapp.entities.UserStatus;
import sda.soft.academy.spring.ordersapp.services.UserService;

@Component
public class UniqueLoginValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto userDto = (UserDto) o;
        String login = userDto.getLogin();
        boolean userExists=userService.checkUserExists(login);
        if (userExists) {
            errors.rejectValue("login", "user.registration.validation.message.loginAlreadyExists");
        }

    }
}
