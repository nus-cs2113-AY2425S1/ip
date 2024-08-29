import java.util.Scanner;
import java.util.ArrayList;

public class Crystal {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        System.out.println("""
                ____________________________________________________________
                Hello! I'm Crystal.
                What can I do for you today?
                ____________________________________________________________
                """);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
                System.out.println("____________________________________________________________");
            } else {
                list.add(line);
                System.out.println("____________________________________________________________\n"
                        + "added: " + line + "\n"
                        + "____________________________________________________________");
            }
            line = in.nextLine();

        }
        System.out.println("____________________________________________________________\n"
                + "Bye, hope to see you again soon!\n"
                + "____________________________________________________________");

    }
}
