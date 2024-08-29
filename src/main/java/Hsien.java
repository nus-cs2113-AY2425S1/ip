import java.util.Scanner;

public class Hsien {

    public static void printLine() {
        System.out.println("-".repeat(50) + "\n");
    }

    public static void printLogo() {
        System.out.println(" _   _         _____        _ _   _ ");
        System.out.println("| | | | ##### |_   _|##### |   \\ | |");
        System.out.println("| |_| |#        | |  #     | |\\ \\| |");
        System.out.println("|  _  | #####   | |  ##### | | \\ \\ |");
        System.out.println("| | | |      # _| |_ #     | |  \\  |");
        System.out.println("|_| |_| ##### |_____|##### |_|   \\_|\n");
    }

    public static void main(String[] args) {
        printLine();
        printLogo();
        // Greet
        System.out.println("Hello! I am Hsien, your personal chatbot\n");
        printLine();

        String message;
        Scanner in = new Scanner(System.in);

        while (true) {
            message = in.nextLine();
            if (message.equals("bye")) {
                // Exit
                System.out.println("Have a good day! Bye!");
                printLine();
                break;
            } else {
                System.out.println(message + "\n");
                printLine();
            }
        }

        in.close();
    }
}
