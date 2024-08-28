import java.util.Scanner;

public class Anke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Anke.");
        System.out.println("What can I do for you?\n");
        String line = "";
        while (!line.equals("bye")) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            System.out.println(line);
        }
        System.out.println("Bye. Hope to see you again soon!\n");

    }
}
