/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import wrappers.Activity;
import java.util.List;

/**
 *
 * @author dishelt
 */
public interface ActivityDAO {

    public List<Activity> getActivities();

    public void saveActivity(Activity activity);
    
    public void updateActivity (String id, Activity activity);
    
    public void deleteActivity (String id);
    
}
