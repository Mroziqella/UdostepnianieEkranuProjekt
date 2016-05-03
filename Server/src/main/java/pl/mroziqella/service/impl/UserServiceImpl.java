package pl.mroziqella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mroziqella.domain.User;
import pl.mroziqella.repository.UserRepository;
import pl.mroziqella.service.UserService;

import java.util.List;

/**
 * Created by Kamil on 30/03/2016.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean save(User user) {
         return userRepository.save(user);
    }

    @Override
    public List<User> userList() {
        return null;
    }

    @Override
    public User getUser(String login) {
        return userRepository.getUser(login);
    }
}
