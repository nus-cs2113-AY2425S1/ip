import java.util.Scanner;

public class Tommi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler();

        printIntroMessage();
        readInputStrings(scanner, inputHandler);
        printExitMessage();
    }

    private static void readInputStrings(Scanner scanner, InputHandler inputHandler) {
        String input = scanner.nextLine();
        while (!input.equals("bye"))
        {
            processInputCases(input);
            input = scanner.nextLine();
        }
    }

    private static void processInputCases(String input) {
        if (input.equals("list")) {
            InputHandler.listTasks();
            return;
        }

        String[] words = input.split(" ");
        int taskIndex;
        switch (words[0]) {
        case "mark":
                taskIndex = Integer.parseInt(words[1]) - 1;
                InputHandler.markTask(taskIndex);
                break;
        case "unmark":
                taskIndex = Integer.parseInt(input.substring(7)) - 1;
                InputHandler.unmarkTask(taskIndex);
                break;
        default:
                InputHandler.addTask(input);
        }
    }

    private static void printIntroMessage() {
        String intro = """
                 ______                  \s
                /_  __/__  __ _  __ _  (_)
                 / / / _ \\/  ' \\/  ' \\/ /\s
                /_/  \\___/_/_/_/_/_/_/_/ \s
                ____________________________________________________________
                Hello! I'm Tommi!
                What can I do for you?
                ____________________________________________________________
                """;
        System.out.println(intro);
    }

    private static void printExitMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
