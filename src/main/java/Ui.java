import java.util.Scanner;

public class Ui {
    private static InputParser inputParser;
    private static Scanner inputScanner;

    public Ui() {
        inputParser = new InputParser();
        inputScanner = new Scanner(System.in);
    }

    public void run(TaskList taskList){
        printGreeting();

        String inputLine;
        do{
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

    /**
     * Prints a greeting to the user
     */
    public static void printGreeting() {
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

    /**
     * Prints a line divider consisting of _ characters
     */
    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    private static void printGoodbye() {
        System.out.println("Bye, see you later!");
        printDivider();
    }
}
