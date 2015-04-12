package calendar.service;

import calendar.model.CEvent;
import calendar.model.User;
import calendar.model.UserFile;
import calendar.repository.EventRepository;
import calendar.repository.UserFileRepository;
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
    private UserFileRepository userFileRepository;

    @Autowired
    public CalendarServiceImpl(EventRepository eventRepository,
                               UserRepository userRepository,
                               UserFileRepository userFileRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.userFileRepository = userFileRepository;
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

    @Override
    @Transactional(readOnly = true)
    public UserFile findFileById(int id) throws DataAccessException {
        return userFileRepository.findById(id);
    }

    @Override
    @Transactional
    public void addNewFile(String name, int size, int userId) throws DataAccessException {
        try {
            User user = userRepository.findById(userId);
            UserFile userFile = new UserFile();
            userFile.setId(null);
            userFile.setFname(name);
            userFile.setUpl_date(DateTime.now());
            userFile.setUser(user);
            userFile.setFsize(size);
            userFileRepository.save(userFile);
        }
        catch (NullPointerException e) {
            throw new DataAccessResourceFailureException(e.toString());
        }
    }

    @Override
    @Transactional
    public void saveUserFile(UserFile userFile) throws DataAccessException {
        userFileRepository.save(userFile);
    }

    @Override
    @Transactional
    public Collection<UserFile> getFilesByLinkedId(int id) throws DataAccessException {
        return userFileRepository.findByLinkedUserId(id);
    }

    @Override
    @Transactional
    public void deleteFileId(int id) throws DataAccessException {
        userFileRepository.deleteById(id);
    }
}
