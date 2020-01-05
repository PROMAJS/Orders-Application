package sda.soft.academy.spring.ordersapp.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.soft.academy.spring.ordersapp.entities.User;
import sda.soft.academy.spring.ordersapp.entities.UserStatus;
import sda.soft.academy.spring.ordersapp.repositories.UserRepository;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean login(String login, String plainPassword) {
        String encryptedPassword = DigestUtils.md5Hex(plainPassword);
        Optional<User> user =  userRepository.findOneByLoginAndPasswordAndStatus(login, encryptedPassword, UserStatus.ACTIVE);
        return user.isPresent();
    }
}
