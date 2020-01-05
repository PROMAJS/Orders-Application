package sda.soft.academy.spring.ordersapp.services;

import sda.soft.academy.spring.ordersapp.dto.UserDto;

public interface UserService {

    public void registerUser(UserDto userDto);

    public boolean activateUser(String login, String token);

    public boolean checkUserExists(String login);
}
