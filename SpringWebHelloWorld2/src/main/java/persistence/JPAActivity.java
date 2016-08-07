/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

/**
 *
 * @author dishelt
 */
import wrappers.Activity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "activityDao")
public class JPAActivity implements ActivityDAO {

    private EntityManager entityManager = null;
    
    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }
    
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public List<Activity> getActivities() {
        return entityManager.createQuery("select a from Activity a order by a.id").getResultList();
    }

    @Transactional(readOnly = false)
    @Override
    public void saveActivity(Activity activity) {
        entityManager.merge(activity);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void updateActivity(String id, Activity activity) {
        Activity oldActivity = entityManager.find(Activity.class, Integer.parseInt(id));
        oldActivity.setDescription(activity.getDescription());
        oldActivity.setDeadline(activity.getDeadline());
        entityManager.merge(oldActivity);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void deleteActivity(String id) {
        entityManager.remove(entityManager.find(Activity.class, Integer.parseInt(id)));
    }

}
