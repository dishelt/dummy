/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import persistence.ActivityDAO;
import wrappers.Activity;

/**
 *
 * @author dishelt
 */
@RestController
public class ActivityController {
    
    @Autowired
    ApplicationContext context;

    @RequestMapping(value = "/getActivities", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    private List<Activity> getActivities() {
        //ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        ActivityDAO dao = (ActivityDAO) context.getBean("activityDao");
        return dao.getActivities();
    }

    @RequestMapping(value = "/addActivity", method = RequestMethod.POST)
    public ResponseEntity<String> addActivity(@RequestBody Activity activity) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        ActivityDAO dao = (ActivityDAO) context.getBean("activityDao");
        dao.saveActivity(activity);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/updateActivity/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Activity> updateActivity(@PathVariable(value = "id") String id, @RequestBody Activity activity) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        ActivityDAO dao = (ActivityDAO) context.getBean("activityDao");
        dao.updateActivity(id, activity);
        return new ResponseEntity<Activity>(activity, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteActivity/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteActivity(@PathVariable("id") String id) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        ActivityDAO dao = (ActivityDAO) context.getBean("activityDao");
        dao.deleteActivity(id);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
