package ua.training.library.controller.listener;

import ua.training.library.controller.configuration.ResourceNames;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("applicationContextListener")
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        PropertyConfigurator.configure(ContextListener.class.getClassLoader().getResourceAsStream(ResourceNames.LOGGER_PROPERTIES));
        Logger logger = Logger.getLogger(ContextListener.class);
        logger.info("   ---==   START LOGGER SESSION (CONTEXT)   ==---");
        logger.info("Logger configured.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

}
