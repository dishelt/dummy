/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springfrontend;

/**
 *
 * @author dishelt
 */
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import wrappers.Activity;

@Controller
public class MVCController {

    private String url = wrappers.Utility.URL;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RequestMapping(value = "/activities", method = RequestMethod.GET)
    public ModelAndView getActivities() {
        ModelAndView model = new ModelAndView("activities");
        model.addObject("listActivities", this.getActivitiesMod());
        return model;
    }

    @RequestMapping(value = "/addActivity", method = RequestMethod.POST)
    public ModelAndView addActivity(@ModelAttribute("activityForm") final Activity activity) {
        ModelAndView model = new ModelAndView("activities");
        this.saveActivityMod(activity);
        model.addObject("listActivities", this.getActivitiesMod());
        return model;
    }

    @RequestMapping(value = "/deleteActivity/{id}", method = RequestMethod.GET)
    public ModelAndView deleteActivity(@PathVariable(value = "id") final String id) {
        ModelAndView model = new ModelAndView("activities");
        this.deleteActivityMod(id);
        model.addObject("listActivities", this.getActivitiesMod());
        return model;
    }

    @RequestMapping(value = "/updateActivity", method = RequestMethod.POST)
    public ModelAndView updateActivity(@ModelAttribute("updateForm") final Activity activity) {
        ModelAndView model = new ModelAndView("activities");
        this.updateActivityMod(activity.getId() + "", activity);
        model.addObject("listActivities", this.getActivitiesMod());
        return model;
    }

    private void saveActivityMod(Activity activity) {
        String uri = url + "/addActivity/";
        RestTemplate restTemplate = new RestTemplate();
        Activity result = restTemplate.postForObject(uri, activity, Activity.class);
    }

    private void updateActivityMod(String id, Activity activity) {
        String uri = url + "/updateActivity/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        restTemplate.put(uri, activity, params);
    }

    private List<Activity> getActivitiesMod() {
        String uri = url + "/getActivities/";
        RestTemplate restTemplate = new RestTemplate();
        List<Activity> result = restTemplate.getForObject(uri, List.class);
        return result;
    }

    private void deleteActivityMod(String id) {
        String uri = url + "/deleteActivity/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        restTemplate.delete(uri, params);
    }

}
