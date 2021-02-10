package network.rest;

import structures.IDataAccessor;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class RESTApp
{
    @Autowired
    private ApplicationContext applicationContext;

    public static void run(IDataAccessor db)
    {
        ConfigurableApplicationContext cx = SpringApplication.run(RESTApp.class);
        cx.getBean(UserControler.class).setDatabaseController(db);
    }
}
