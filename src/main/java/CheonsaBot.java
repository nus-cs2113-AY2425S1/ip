import java.util.Scanner;

public class CheonsaBot {
    public static String horizontalLine = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = """
                       (\\ -=- /)
                       ( \\( )/ )
                       (       )
                        `>   <'
                        /     \\
                        `-._.-'
                       """;
        System.out.println(horizontalLine);
        System.out.println("Hello, I'm 천사봇! (AngelBot)");
        System.out.print(logo);
        System.out.println("How may I assist you today?");
        System.out.println(horizontalLine);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();  // Read user input
            if (userInput.equalsIgnoreCase("bye")) {
                sayBye();
                break;
            }
            System.out.println(horizontalLine);
            System.out.println(userInput);
            System.out.println(horizontalLine);
        }
    }

    public static void sayBye() {
        System.out.println(horizontalLine);
        System.out.println("Bye, see you again soon!");
        System.out.println(horizontalLine);
    }
}
