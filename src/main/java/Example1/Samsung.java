package Example1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Artur Kuzmik on 18.27.5
 */

@Component
public class Samsung {

    private
    MobileProcessor cpu;

    @Autowired
    public Samsung(@Qualifier("snapdragon") MobileProcessor cpu) {
        this.cpu = cpu;
    }

    public MobileProcessor getCpu() {
        return cpu;
    }

    public void setCpu(MobileProcessor cpu) {
        this.cpu = cpu;
    }

    public void config(){
        System.out.println("Hello");
        cpu.process();
    }
}
