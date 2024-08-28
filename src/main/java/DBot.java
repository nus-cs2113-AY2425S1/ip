import java.util.Scanner;

public class DBot {
    private static boolean isOn;

    public static void main(String[] args) {
        isOn = true;

        String greeting = "____________________________________________________________\n" +
                "Hello! I'm DBot\nWhat can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greeting);

        Scanner in = new Scanner(System.in);

        while (isOn) {
            System.out.print("Command: ");
            String line = in.nextLine();
            System.out.println("____________________________________________________________");
            if(line.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isOn = false;
            } else {
                System.out.println(line);
            }
            System.out.println("____________________________________________________________");
        }
    }
}
