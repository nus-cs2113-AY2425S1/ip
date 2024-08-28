import java.util.Scanner;

public class Anke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Anke.");
        System.out.println("What can I do for you?\n");
        String line = "";
        String[] lines = new String[100];
        int count = 0;
        while (!line.equals("bye")) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + lines[i]);
                }
                System.out.println("");
                continue;
            }
            lines[count] = line;
            count++;
            System.out.println("added: " + line + "\n");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
