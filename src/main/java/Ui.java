import java.util.Scanner;

public class Ui {
    private static InputParser inputParser;
    private static Scanner inputScanner;

    public Ui() {
        inputParser = new InputParser();
        inputScanner = new Scanner(System.in);
    }

    /**
     * Handles the main runtime loop for the UI
     * @param taskList The list of tasks we want use to for the program
     */
    public void run(TaskList taskList) {
        printGreeting();

        String inputLine;
        do {
            inputLine = inputScanner.nextLine();
            printDivider();
            try {
                inputParser.handleInput(inputLine, taskList);
            } catch (Exception e) {
                System.out.println("Please enter valid commands");
            }
            printDivider();
        } while (!inputLine.equals("bye"));

        printGoodbye();
        inputScanner.close();
    }

    private void printGreeting() {
        String logo =
                """
                         __  __   _   _           \s
                        |  \\/  | (_) | |          \s
                        | \\  / |  _  | | __  _   _\s
                        | |\\/| | | | | |/ / | | | |
                        | |  | | | | |   <  | |_| |
                        |_|  |_| |_| |_|\\_\\  \\__,_|
                        """;
        //Text to Ascii generated through https://patorjk.com/software/taag/
        System.out.println(logo);

        printDivider();
        System.out.println("Hello! I'm Miku\nWhat can I do for you?");
        printDivider();
    }

    private void printDivider() {
        System.out.println("____________________________________________________________");
    }

    private void printGoodbye() {
        System.out.println("Bye, see you later!");
        printDivider();
    }
}
