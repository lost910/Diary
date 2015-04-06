package calendar.service;

import calendar.model.CEvent;
import calendar.model.User;
import calendar.repository.EventRepository;
import calendar.repository.UserRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Marat on 16.03.2015.
 */
@Service
public class CalendarServiceImpl implements CalendarService {
    private EventRepository eventRepository;
    private UserRepository userRepository;

    @Autowired
    public CalendarServiceImpl(EventRepository eventRepository,
                               UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public CEvent findEventById(int id) throws DataAccessException {
        return eventRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByLogin(String login) throws DataAccessException {
        return userRepository.findByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<CEvent> getAllEvent() throws DataAccessException {
        return eventRepository.getAllEvents();
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(int id) throws DataAccessException {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<CEvent> getEventsByLinkedId(int id) throws DataAccessException {
        return eventRepository.findByLinkedUserId(id);
    }

    @Override
    @Transactional
    public void saveEvent(CEvent event) throws DataAccessException {
        eventRepository.save(event);
    }

    @Override
    @Transactional
    public void saveUser(User user) throws DataAccessException {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void addNewEvent(String theme, String descr, DateTime dt, int userId) throws DataAccessException {
        try {
            User user = userRepository.findById(userId);
            CEvent event = new CEvent();
            event.setId(null);
            event.setTheme(theme);
            event.setDescr(descr);
            event.setCr_date(dt);
            event.setUser(user);
            eventRepository.save(event);
        }
        catch (NullPointerException e) {
            throw new DataAccessResourceFailureException(e.toString());
        }
    }

    @Override
    @Transactional
    public void deleteEventById(int id) throws DataAccessException {
        eventRepository.deleteById(id);
    }

    @Override
    @Transactional
    public  boolean peekUserByLogin(String login) throws DataAccessException {
        User user = null;
        user = userRepository.findByLogin(login);
        return (user != null);
    }
}
