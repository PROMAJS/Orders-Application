package sda.soft.academy.spring.ordersapp.services;

public interface EmailService {

    public void send(String to, String title, String body);
}
