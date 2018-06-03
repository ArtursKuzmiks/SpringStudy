package Example1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Artur Kuzmik on 18.27.5
 */

public class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        Samsung s7 = context.getBean(Samsung.class);
        s7.config();
    }
}
