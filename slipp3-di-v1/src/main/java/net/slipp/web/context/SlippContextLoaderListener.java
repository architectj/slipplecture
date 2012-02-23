package net.slipp.web.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.slipp.context.ApplicationContext;

public class SlippContextLoaderListener implements ServletContextListener {
    public static final String SLIPP_CONTEXT_NAME = "SLIPP_CONTEXT";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext ac = new ApplicationContext();
        ac.init();
        
        ServletContext sc = sce.getServletContext();
        sc.setAttribute(SLIPP_CONTEXT_NAME, ac);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
