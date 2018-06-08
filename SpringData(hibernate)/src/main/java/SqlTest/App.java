package SqlTest;

import SqlTest.AppConfig.AppConfig;
import SqlTest.Service.CustomerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Artur Kuzmik on 18.7.6
 */

public class App {

    public static void main(String[] args) throws IOException {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(CustomerService.class).run();
    }
}
