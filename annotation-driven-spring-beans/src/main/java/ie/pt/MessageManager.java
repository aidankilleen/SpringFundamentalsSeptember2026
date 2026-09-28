package ie.pt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageManager {

    @Autowired
    Message m;

    public MessageManager() {
        //m = new Message("MessageManager", "This is the message manager");
    }

    public void display() {

        System.out.println("Message Manager:");
        System.out.println(m);
    }
}
