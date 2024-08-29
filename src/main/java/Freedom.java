import java.util.Scanner;

public class Freedom {
    public static void main(String[] args) {
        String logo = "_________________________________________\n";
        String message = """
                \tHello! I'm Freedom
                \tWhat can I do for you?
                """;
        String closing = "\tBye. Hope to see you again soon!\n";

        System.out.println(logo + message + logo);

        while (true) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            if (line.equals("bye")) {
                break;
            }
            String command = "\t" + line + "\n";
            System.out.println(logo + command + logo);
        }

        System.out.println(logo + closing + logo);
    }
}
