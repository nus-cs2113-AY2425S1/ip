import java.util.Scanner;

public class Gertrude {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Gertrude.");
        System.out.println("What can I do for you?");

        while(true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            System.out.println("____________________________________________________________");
            if(line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            System.out.println(line);
            System.out.println("____________________________________________________________");
        }
    }
}
