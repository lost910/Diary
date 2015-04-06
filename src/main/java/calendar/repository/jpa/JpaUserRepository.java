package calendar.repository.jpa;

import calendar.model.User;
import calendar.repository.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Created by Marat on 17.03.2015.
 */
@Repository
public class JpaUserRepository implements UserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public User findByLogin(String login) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT user FROM User user WHERE user.login =:login");
        query.setParameter("login", login);
        User user = null;
        try{
            user = (User) query.getSingleResult();}
        catch(NoResultException e) {
            return null;
        }
        finally {
            return user;
        }
    }

    @Override
    public User findById(int id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT user FROM User user WHERE user.id =:id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public void save(User user) throws DataAccessException {
        if(user.getId() == null) {
            this.em.persist(user);
        } else {
            this.em.merge(user);
        }
    }
}
