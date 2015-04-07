package calendar.repository.jpa;

import calendar.model.CEvent;
import calendar.repository.EventRepository;
import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Marat on 17.03.2015.
 */
@Repository
public class JpaEventRepository implements EventRepository {
    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public Collection<CEvent> findByLinkedUserId(int id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT cEvent FROM CEvent cEvent WHERE linked_user_id=:id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public CEvent findById(int id) throws DataAccessException {
        Query query = em.createQuery("SELECT DISTINCT cEvent FROM CEvent cEvent WHERE cEvent.id=:id");
        query.setParameter("id", id);
        return (CEvent) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public Collection<CEvent> getAllEvents() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT cEvent FROM CEvent cEvent");
        return query.getResultList();
    }

    @Override
    public void deleteById(int id) throws DataAccessException {
        Query query = this.em.createQuery("DELETE FROM CEvent WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void save(CEvent event) throws DataAccessException {
        if(event.getId() == null) {
            this.em.persist(event);
        } else {
            this.em.merge(event);
            /*
            Query query = this.em.createQuery("UPDATE CEvent e SET e.theme=:theme," +
                    "e.descr=:descr WHERE e.id=:id");
            query.setParameter("theme", event.getTheme());
            query.setParameter("descr", event.getDescr());
            query.setParameter("id", event.getId());
            query.executeUpdate();*/
        }
    }
}
