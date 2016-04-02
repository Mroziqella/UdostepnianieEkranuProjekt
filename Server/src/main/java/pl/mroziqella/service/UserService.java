package pl.mroziqella.service;

import pl.mroziqella.domain.User;

import java.util.List;

/**
 * Created by Kamil on 30/03/2016.
 */
public interface UserService {
    boolean save(User user);
    List<User> userList();
}
