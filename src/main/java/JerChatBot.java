import java.util.Scanner;

public class JerChatBot {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] tasks = new String[100];
        int numOfTasks = 0;

        System.out.println("~~~~~~~~ Welcome to Jer Chat Bot ~~~~~~~~");
        System.out.println(" What you want?");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(" Finally done, k bye...");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                break;
            } else if (input.equals("list")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                for (int i = 0; i < numOfTasks; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        } else {
                tasks[numOfTasks] = input;
                numOfTasks++;
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("added: " + input);
                System.out.println(" And then what??");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
        in.close();
    }
}
