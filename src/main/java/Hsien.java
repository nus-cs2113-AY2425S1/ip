import java.util.Scanner;
import java.util.ArrayList;

public class Hsien {

    public static void printLogo() {
        System.out.println(" _   _         _____        _ _   _ ");
        System.out.println("| | | | ##### |_   _|##### |   \\ | |");
        System.out.println("| |_| |#        | |  #     | |\\ \\| |");
        System.out.println("|  _  | #####   | |  ##### | | \\ \\ |");
        System.out.println("| | | |      # _| |_ #     | |  \\  |");
        System.out.println("|_| |_| ##### |_____|##### |_|   \\_|\n");
    }

    public static void printLine() {
        System.out.println("\n" + "-".repeat(50) + "\n");
    }

    public static void printList(ArrayList<String> messages) {
        int counter = 1;
        for (String message : messages) {
            System.out.println(String.format("%s. %s", counter, message));
            counter += 1;
        }
    }

    public static void main(String[] args) {
        printLine();
        printLogo();
        // Greet
        System.out.println("Hello! I am Hsien, your personal chatbot");
        printLine();

        ArrayList<String> messages = new ArrayList<>();
        String message;
        Scanner in = new Scanner(System.in);

        while (true) {
            message = in.nextLine();
            if (message.equals("bye")) {
                // Exit
                System.out.println("Have a good day! Bye!");
                break;
            } else if (message.equals("list")) {
                printList(messages);
            } else {
                messages.add(message);
                System.out.println("Added message: " + message);
            }
            printLine();
        }

        in.close();
    }
}
