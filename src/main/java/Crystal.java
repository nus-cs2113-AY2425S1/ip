import java.util.Scanner;

public class Crystal {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("""
                ____________________________________________________________
                Hello! I'm Crystal.
                What can I do for you today?
                ____________________________________________________________
                """);
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println("____________________________________________________________\n"
                    + line + "\n"
                    + "____________________________________________________________");
            line = in.nextLine();
        }
        System.out.println("____________________________________________________________\n"
                + "Bye, hope to see you again soon!\n"
                + "____________________________________________________________");

    }
}
