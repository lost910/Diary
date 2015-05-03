package calendar.repository.jpa;

import calendar.model.UserFile;
import calendar.repository.UserFileRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Created by Marat on 12.04.2015.
 */
@Repository
public class JpaUserFileRepository implements UserFileRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public UserFile findById(int id) throws DataAccessException {
        return em.find(UserFile.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<UserFile> findByLinkedUserId(int id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT userFile FROM UserFile userFile " +
                "WHERE user_id=:id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void save(UserFile userFile) throws DataAccessException {
        if(userFile.getId() == null) {
            this.em.persist(userFile);
        } else {
            this.em.merge(userFile);
        }
    }

    @Override
    public void deleteById(int id) throws DataAccessException {
        Query query = this.em.createQuery("DELETE FROM UserFile WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
