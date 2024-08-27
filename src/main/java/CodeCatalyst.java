import org.w3c.dom.ls.LSInput;
import java.sql.SQLOutput;
import java.util.Scanner;

public class CodeCatalyst {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("        ________________________________________________________");
        System.out.println("        Hello, I'm CodeCatalyst.");
        System.out.println("        What can I do for you?");
        System.out.println("        ________________________________________________________\n");

        while (true) {
            String task = input.nextLine();
            System.out.println("        ________________________________________________________");
            if (task.equals("bye")) {
                System.out.println("        Bye. Hope to see you again soon!");
                System.out.println("        ________________________________________________________\n");
                break;
            }
            System.out.println("        " + task);
            System.out.println("        ________________________________________________________\n");
        }
    }
}
