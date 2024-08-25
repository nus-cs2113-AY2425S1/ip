import java.util.Scanner;

public class Luke {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";
        Scanner in = new Scanner(System.in);
        String line;
        boolean exit = false;
        String greeting = "____________________________________________________________\n" +
                " Hello! I'm Luke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greeting);

        while (!exit) {
            line = in.nextLine();
            if (line.equalsIgnoreCase("Bye")) {
                System.out.println(horizontalLine);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                exit = true;
                continue;
            }
            System.out.println(horizontalLine);
            System.out.println(line);
            System.out.println(horizontalLine);
        }
    }
}
