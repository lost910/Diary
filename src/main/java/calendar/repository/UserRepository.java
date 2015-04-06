package calendar.repository;

import calendar.model.User;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

/**
 * Created by Marat on 16.03.2015.
 */
public interface UserRepository {
    User findByLogin(String login) throws DataAccessException;
    User findById(int id) throws DataAccessException;
    void save(User user) throws DataAccessException;
}
