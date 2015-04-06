package calendar.repository;

import calendar.model.CEvent;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

/**
 * Created by Marat on 16.03.2015.
 */
public interface EventRepository {
    CEvent findById(int id) throws DataAccessException;
    Collection<CEvent> findByLinkedUserId(int id) throws DataAccessException;
    void deleteById(int id) throws DataAccessException;
    Collection<CEvent> getAllEvents() throws DataAccessException;
    void save(CEvent CEvent) throws DataAccessException;
}
