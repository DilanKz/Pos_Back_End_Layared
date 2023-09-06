package lk.ijse.pos.listeners;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DefaultListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Initializer");

        ServletContext servletContext = servletContextEvent.getServletContext();

        BasicDataSource pool = new BasicDataSource();
        pool.setDriverClassName("com.mysql.cj.jdbc.Driver");
        pool.setUrl("jdbc:mysql://localhost:3306/webpos?allowPublicKeyRetrieval=true&useSSL=false");
        pool.setUsername("root");
        pool.setPassword("1234");
        pool.setInitialSize(3);
        pool.setMaxTotal(3);

        servletContext.setAttribute("dbcp",pool);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
