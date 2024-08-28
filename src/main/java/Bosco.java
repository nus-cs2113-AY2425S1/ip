import java.util.Scanner;

public class Bosco {
    private static void printHorizontalRule() {
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput;
        String userCommand;

        printHorizontalRule();
        System.out.println("\t Hello! I'm Bosco APD.");
        System.out.println("\t What can I do for you?");
        printHorizontalRule();

        while (true) {
            userInput = in.nextLine();
            userCommand = userInput.split("\\s+", 2)[0];
            if (userCommand.equals("bye")) {
                break;
            }
            printHorizontalRule();
            System.out.println("\t " + userInput);
            printHorizontalRule();
        }

        printHorizontalRule();
        System.out.println("\t Bye! Hope to see you again soon!");
        printHorizontalRule();
    }
}
