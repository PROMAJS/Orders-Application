package sda.soft.academy.spring.ordersapp.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @Size(min=2, max = 100)
    private String firstName;

    @Size(max = 100)
    private String lastName;

    @Size(max = 50)
    private String street;

    @Size(max = 30)
    private String postal;

    @Size(max = 30)
    private String number;

    @Size(max = 50)
    private String city;

    @Size(min = 3, max = 20)
    private String login;

    @Email
    private String email;

    @Size(min = 3, max = 50)
    private String password1;

    @Size(min = 3, max = 50)
    private String password2;

    private Boolean agreement;

}
