package Example1;


import org.springframework.stereotype.Component;

/**
 * @author Artur Kuzmik on 18.27.5
 */

@Component
public class Snapdragon implements MobileProcessor {

    @Override
    public void process() {
        System.out.println("Worls best CPU");
    }
}
