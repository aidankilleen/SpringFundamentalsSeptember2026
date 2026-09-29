package ie.pt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamingApiInvestigation {

    public static void print(int i) {
        System.out.println(i);
    }
    public static void main(String[] args) {

        System.out.println("Streaming API Investigation");

        List<Integer> numbers = new ArrayList<Integer>();

        numbers.add(9);
        numbers.add(8);
        numbers.add(2);
        numbers.add(5);
        numbers.add(3);
        numbers.add(1);
        numbers.add(10);
        numbers.add(7);
        numbers.add(4);
        numbers.add(6);



        numbers.forEach(StreamingApiInvestigation::print);

        System.out.println("Using a lambda:");
        // if the lambda has a single parameter you can remove ()
        numbers.forEach((i) -> {
            System.out.println(i);
        });

        // if the lambda has a single parameter you can remove ()
        // if the lambda has a single line you can remove {} return and ;
        // if the lambda is just a single function call you can just provide
        // a reference to the function for example System.out::println
        numbers.forEach(System.out::println);

        System.out.println(numbers);

        numbers.sort(StreamingApiInvestigation::compareInts);

        System.out.println(numbers);

        numbers.sort((a, b) -> {
            System.out.printf("Comparing %d to %d\n", a, b);
            if (a < b) {
                return 1;
            } else if (a == b) {
                return 0;
            } else {
                return -1;
            }
        });
        System.out.println(numbers);
        // if the lambda has only 1 parameter you can remove the ()
        // rewrite the logic to be shorter if possible
        // if its a single line remove {} return and ;
        numbers.sort((a, b) -> a-b);
        System.out.println(numbers);


        List<Integer> evenNumbers = numbers.stream().filter((i) -> {
            if (i % 2 == 0) {
                return true;
            } else {
                return false;
            }
        }).toList();

        System.out.println(evenNumbers);

        // if lambda takes a single parameter remove ()
        // reduce the logic ideally to a single line
        // remove {} return ;
        List<Integer> oddNumbers = numbers.stream()
                                        .filter(i -> i % 2 == 1)
                                        .toList();

        System.out.println(oddNumbers);
        
        InMemoryUserDao dao = new InMemoryUserDao();

        List<User> users = dao.getUsers();

        System.out.println(users);

        List<User> inactiveUsers = users.stream().filter((user) -> {
            if (!user.isActive()) {
                return true;
            } else {
                return false;
            }
        }).toList();
        
        // terse version
        // single parameter - remove the ()
        // reduce the logic to a single line (if possible)
        // remove {} return ;
        // finally in this case - you can actually just provide a reference to the isActive
        // method in the User class
        List<User> activeUsers = users.stream()
                .filter(User::isActive)
                .toList();

        System.out.println(activeUsers);

        // exercise until ~10.40~ 10.45
        // get the list of users from the InMemory dao (maybe add a few extra users)
        // sort them by name
        // filter them by active flag
        // go through each and print out the name

       dao.getUsers()
                .stream()
                .sorted((u1, u2) -> u1.getName().compareTo(u2.getName()))
               .filter(User::isActive)
               .forEach(u -> System.out.println(u.getName()));

        // recommendation - get comfortable with this style of coding!

        //users.sort((u1, u2)->u1.getName().compareTo(u2.getName()));
/*
        List<User> sortedUsers = users.stream().sorted()
                .toList();
        System.out.println(sortedUsers);

        List<User> filteredUsers = sortedUsers.stream()
                                    .filter(user -> user.isActive())
                                    .toList();

        System.out.println(filteredUsers);

        filteredUsers.forEach(user-> System.out.println(user.getName()));
*/

    }
    public static int compareInts(int a, int b) {
        // returns a positive number if a > b
        // returns 0 if a == b
        // returns a negative number is a < b
        if (a < b) {
            return -1;
        } else if (a == b) {
            return 0;
        } else {
            return 1;
        }
    }
}
