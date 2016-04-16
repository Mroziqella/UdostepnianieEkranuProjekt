package pl.mroziqella.repository;

import pl.mroziqella.domain.User;

import java.util.List;

/**
 * Created by Kamil on 25/03/2016.
 */
public interface UserRepository {
    boolean save(User user);
}
