package calendar.util;

import calendar.model.BaseEntity;
import org.springframework.orm.ObjectRetrievalFailureException;

import java.util.Collection;

/**
 * Created by Marat on 16.03.2015.
 */
public abstract class EntityUtils {
    public static <T extends BaseEntity> T getById(Collection<T> entities, Class<T> entityClass,
                                                   int entityId)
        throws ObjectRetrievalFailureException {

        for ( T entity : entities) {
            if(entity.getId().intValue() == entityId && entityClass.isInstance(entity)) {
                return entity;
            }
        }
        throw new ObjectRetrievalFailureException(entityClass, entityId);
    }
}
