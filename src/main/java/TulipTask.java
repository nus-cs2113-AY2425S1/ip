import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class TulipTask {
    public static void main(String[] args) {
        String logo = "                                         \n" +
                "--.--     |    o     --.--          |    \n" +
                "  |  .   .|    .,---.  |  ,---.,---.|__/ \n" +
                "  |  |   ||    ||   |  |  ,---|`---.|  \\ \n" +
                "  `  `---'`---'`|---'  `  `---^`---'`   `\n" +
                "                |";

        System.out.println(logo);

        System.out.println("--------------------------------------------");
        System.out.println("Hello, I'm TulipTask");
        System.out.println("What can I do for you today?");
        System.out.println("--------------------------------------------");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (Objects.equals(input.toLowerCase(), "bye")) {
                break;
            }

            echo(input);
        }

        System.out.println("--------------------------------------------");
        System.out.println("Bye! Hope to see you again soon :)");
        System.out.println("--------------------------------------------");

    }

    public static void echo (String input) {
        System.out.println("--------------------------------------------");
        System.out.println(input);
        System.out.println("--------------------------------------------");
    }
}
