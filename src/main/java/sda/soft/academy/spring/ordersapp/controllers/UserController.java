package sda.soft.academy.spring.ordersapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sda.soft.academy.spring.ordersapp.dto.UserDto;
import sda.soft.academy.spring.ordersapp.enums.MessageType;
import sda.soft.academy.spring.ordersapp.services.UserService;
import sda.soft.academy.spring.ordersapp.validators.SamePasswordsValidator;
import sda.soft.academy.spring.ordersapp.validators.UniqueLoginValidator;

import javax.persistence.PostUpdate;
import javax.validation.Valid;
import java.util.Locale;

import static sda.soft.academy.spring.ordersapp.enums.MessageType.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UniqueLoginValidator uniqueLoginValidator;

    @Autowired
    private SamePasswordsValidator samePasswordsValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(uniqueLoginValidator);
        binder.addValidators(samePasswordsValidator);
    }


    @GetMapping("/user/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userModel", new UserDto());
        return "registerUser";
    }

    @PostMapping("/user/register")
    public String register(@Valid @ModelAttribute("userModel") UserDto userDto,BindingResult bindingResult , Model model
                           ) {
        if (bindingResult.hasErrors()) {
            return "registerUser";
        } else {
            userService.registerUser(userDto);
            String registrationSuccessMessage = messageSource.getMessage("user.registration.message.success",
                    new Object[]{}, Locale.getDefault());
            return "redirect:/message?messageText=" + registrationSuccessMessage + "&messageType=" + success.name();
        }
    }

    @GetMapping("/user/activation")
    public String activate(@RequestParam String login, @RequestParam String token) {
        boolean activated = userService.activateUser(login, token);
        String activationSuccessMessage = messageSource.getMessage("user.activation.message.success",
                new Object[] {login}, Locale.getDefault());
        String activationFailureMessage = messageSource.getMessage("user.activation.message.failure",
                new Object[] {login}, Locale.getDefault());
        if (activated) {
            return "redirect:/message?messageText="+activationSuccessMessage+"&messageType="+success.name();
        } else {
            return "redirect:/message?messageText="+activationFailureMessage+"&messageType="+danger.name();
        }
    }

}
