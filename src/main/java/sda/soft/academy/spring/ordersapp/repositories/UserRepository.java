package sda.soft.academy.spring.ordersapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sda.soft.academy.spring.ordersapp.entities.User;
import sda.soft.academy.spring.ordersapp.entities.UserStatus;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public Optional<User> findOneByLoginAndToken(String login, String token);

    public Optional<User> findOneByLoginAndPasswordAndStatus(String login, String password, UserStatus userStatus);

    public Optional<User> findOneByLogin(String login);
}
