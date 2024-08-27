import org.w3c.dom.ls.LSInput;
import java.sql.SQLOutput;
import java.util.Scanner;

public class CodeCatalyst {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;

        System.out.println("        ________________________________________________________");
        System.out.println("         Hello, I'm CodeCatalyst.");
        System.out.println("         What can I do for you?");
        System.out.println("        ________________________________________________________\n");

        while (true) {
            String task = input.nextLine();
            System.out.println("        ________________________________________________________");
            if (task.equals("bye")) {
                System.out.println("         Bye. Hope to see you again soon!");
                System.out.println("        ________________________________________________________\n");
                break;
            } else if (task.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("         " + (i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[taskCount] = task;
                taskCount += 1;
                System.out.println("         added: " + task);
            }
            System.out.println("        ________________________________________________________\n");
        }
    }
}
