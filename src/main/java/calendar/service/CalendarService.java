package calendar.service;

import calendar.model.CEvent;
import calendar.model.User;
import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

/**
 * Created by Marat on 16.03.2015.
 */
public interface CalendarService {
    User findUserById(int id) throws DataAccessException;
    User findUserByLogin(String login) throws DataAccessException;
    CEvent findEventById(int id) throws DataAccessException;

    public void addNewEvent(String theme, String descr, DateTime dt, int userId) throws DataAccessException;
    public void deleteEventById(int id) throws DataAccessException;

    void saveUser(User user) throws DataAccessException;
    void saveEvent(CEvent event) throws DataAccessException;

    boolean peekUserByLogin(String login) throws DataAccessException;

    Collection<CEvent> getEventsByLinkedId(int id) throws DataAccessException;
    Collection<CEvent> getAllEvent() throws DataAccessException;
}
