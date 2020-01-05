package sda.soft.academy.spring.ordersapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/user/login")
    public String showLoginForm() {
        return "loginPage";
    }
}
