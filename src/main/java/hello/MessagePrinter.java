package hello;

/**
 * @author Artur Kuzmik on 18.26.5
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagePrinter {

    final private MessageService service;

    @Autowired
    public MessagePrinter(MessageService service) {
        this.service = service;
    }

    void printMessage() {
        System.out.println(this.service.getMessage());
    }
}
