package sda.soft.academy.spring.ordersapp.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import sda.soft.academy.spring.ordersapp.converters.UserConverter;
import sda.soft.academy.spring.ordersapp.dto.UserDto;
import sda.soft.academy.spring.ordersapp.entities.User;
import sda.soft.academy.spring.ordersapp.entities.UserStatus;
import sda.soft.academy.spring.ordersapp.repositories.UserRepository;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserConverter userConverter;

    private EmailService emailService;

    private MessageSource messageSource;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter,
                           EmailService emailService, MessageSource messageSource) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.emailService = emailService;
        this.messageSource = messageSource;
    }

    @Override
    public void registerUser(UserDto userDto) {
        User user = userConverter.apply(userDto);
        user.setStatus(UserStatus.REGISTERED);
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        String plainTextPassword = user.getPassword();
        String encryptedPassword = DigestUtils.md5Hex(plainTextPassword);
        user.setPassword(encryptedPassword);

        userRepository.save(user);
        String activationLink = messageSource.getMessage("order.server.activation.address",
                new Object[] {userDto.getLogin(), token}, Locale.getDefault());
        String emailTitle = messageSource.getMessage("user.registration.mail.title",
                new Object[]{}, Locale.getDefault());
        String emailBody = messageSource.getMessage("user.registration.mail.body",
                new Object[] {userDto.getLogin(), activationLink}, Locale.getDefault());
        emailService.send(userDto.getEmail(), emailTitle, emailBody);

    }

    @Override
    public boolean activateUser(String login, String token) {
        Optional<User> userOptional =userRepository.findOneByLoginAndToken(login, token);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatus(UserStatus.ACTIVE);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkUserExists(String login) {
        return userRepository.findOneByLogin(login).isPresent();
    }
}
