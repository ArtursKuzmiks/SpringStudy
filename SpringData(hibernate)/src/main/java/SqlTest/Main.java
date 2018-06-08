package SqlTest;

import SqlTest.App.App;
import SqlTest.AppConfig.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


import java.io.IOException;


/**
 * @author Artur Kuzmik on 18.7.6
 */

public class Main {

    public static void main(String[] args) throws IOException {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(App.class).run();
    }
}
