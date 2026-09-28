package ie.pt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Annotation Driven Spring Configuration" );

        // String message = "This is a message";

        ApplicationContext ctx
                = new AnnotationConfigApplicationContext(AppConfig.class);

        String message = ctx.getBean(String.class);

        System.out.println(message);

        // enterprise software - don't instantiate  business logic objects
        // we get the framework (spring) to instantiate objects for us
        // Message m = new Message();

        Message m = ctx.getBean("welcomeMessage",Message.class);
        System.out.println(m);

        Message m2 = ctx.getBean("exitMessage",Message.class);
        System.out.println(m2);

        Message m3 = ctx.getBean(Message.class);
        System.out.println(m3);

        // enterprise sw - don't instantiate business logic objects
        //MessageManager mm = new MessageManager();

        MessageManager mm = ctx.getBean(MessageManager.class);
        mm.display();


        // autowired only works for
        // "spring managed beans"
        MessageManager mm2 = new MessageManager();
        mm2.display();
    }
}
