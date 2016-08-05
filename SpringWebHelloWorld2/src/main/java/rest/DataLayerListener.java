/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import persistence.ActivityDAO;

/**
 *
 * @author dishelt
 */
public class DataLayerListener implements ServletContextListener {

    private ServletContext servletContext;
    private ApplicationContext context;

    /**
     * @param event
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        this.servletContext = event.getServletContext();
        this.context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        ActivityDAO dao = (ActivityDAO) context.getBean("activityDao");
        this.servletContext.setAttribute("dao", dao);
    }

    /**
     * @param arg0
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        this.servletContext.removeAttribute("datalayer");
    }

}
