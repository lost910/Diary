package calendar.repository;

import calendar.model.UserFile;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

/**
 * Created by Marat on 12.04.2015.
 */
public interface UserFileRepository {
    UserFile findById(int id) throws DataAccessException;
    Collection<UserFile> findByLinkedUserId(int id) throws DataAccessException;
    void deleteById(int id) throws DataAccessException;
    void save(UserFile userFile) throws DataAccessException;
}
