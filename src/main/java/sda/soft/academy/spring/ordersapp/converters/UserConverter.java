package sda.soft.academy.spring.ordersapp.converters;

import org.springframework.stereotype.Component;
import sda.soft.academy.spring.ordersapp.dto.UserDto;
import sda.soft.academy.spring.ordersapp.entities.Address;
import sda.soft.academy.spring.ordersapp.entities.User;

import java.util.function.Function;

@Component
public class UserConverter implements Function<UserDto, User> {

    @Override
    public User apply(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setLogin(userDto.getLogin());
        user.setAgreement(userDto.getAgreement());
        user.setPassword(userDto.getPassword1());
        Address address = new Address();
        address.setCity(userDto.getCity());
        address.setStreet(userDto.getStreet());
        address.setNumber(userDto.getNumber());
        address.setPostal(userDto.getPostal());
        user.setAddress(address);
        return user;
    }
}
