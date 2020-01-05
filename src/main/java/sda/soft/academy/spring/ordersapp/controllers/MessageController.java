package sda.soft.academy.spring.ordersapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sda.soft.academy.spring.ordersapp.enums.MessageType;

@Controller
public class MessageController {

    @GetMapping("/message")
    public String message(@RequestParam String messageText, @RequestParam String messageType, Model model) {
        model.addAttribute("message", messageText);
        try {
            MessageType messageTypeEnum = MessageType.valueOf(messageType);
        } catch (IllegalArgumentException ex) {
            messageType = MessageType.success.name();
        }
        model.addAttribute("messageType", messageType);
        return "message";
    }
}
