import java.util.Scanner;

public class Anke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Anke.");
        System.out.println("What can I do for you?\n");
        String line = "";
        Task[] tasks = new Task[100];
        int count = 0;
        while (!line.equals("bye")) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
                System.out.println("");
                continue;
            } else if (line.length() > 5 && line.substring(0, 4).equals("mark") && Integer.parseInt(line.substring(5)) <= count && Integer.parseInt(line.substring(5)) > 0) {
                tasks[Integer.parseInt(line.substring(5)) - 1].setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + tasks[Integer.parseInt(line.substring(5)) - 1].getStatusIcon() + "] " + tasks[Integer.parseInt(line.substring(5)) - 1].getDescription() + "\n");
                continue;
            } else if (line.length() > 7 && line.substring(0, 6).equals("unmark") && Integer.parseInt(line.substring(7)) <= count && Integer.parseInt(line.substring(7)) > 0) {
                tasks[Integer.parseInt(line.substring(7)) - 1].setDone(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + tasks[Integer.parseInt(line.substring(7)) - 1].getStatusIcon() + "] " + tasks[Integer.parseInt(line.substring(7)) - 1].getDescription() + "\n");
                continue;
            }
            Task task = new Task(line);
            tasks[count] = task;
            count++;
            System.out.println("added: " + line + "\n");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
