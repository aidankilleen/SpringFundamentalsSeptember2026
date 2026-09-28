package ie.pt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Spring Hello World!" );

        // in normal code we instantiate an object and then use it
        // String message = "Welcome to the spring framework";

        // in enterprise software we get the framework to instantiate our objects for us
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("beans.xml");

        String message = ctx.getBean(String.class);
        System.out.println(message);

        // in enterprise sw we don't instantiate our business objects
        // Message m = new Message("Spring Framework", "Welcome to the Spring Framework");

        Message m1 = ctx.getBean("Message1", Message.class);
        System.out.println(m1);

        Message m2 = ctx.getBean("Message2", Message.class);
        System.out.println(m2);
    }
}
