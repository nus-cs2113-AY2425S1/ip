import java.util.Scanner;

public class Yapper {
    public static void main(String[] args) {
        String Greet = "____________________________________________________________\n"
                     + "Heyo! Imma Yapper!\n"
                     + "Whatchu wanna do?\n"
                     + "____________________________________________________________";
        String Exit = "____________________________________________________________\n"
                    + "Buh-Byeeee!!! Cya soon!\n"
                    + "____________________________________________________________";

        String[] tasks = new String[100];
        int taskCount = 0;

        System.out.println(Greet);
        Scanner in = new Scanner(System.in);
        while (true) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println(Exit);
                break;
            } else if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
                continue;
            }
            tasks[taskCount] = line;
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println("added: " + line);
            System.out.println("____________________________________________________________");
        }
    }
}
