import java.util.Scanner;
import java.util.Random;

public class Lia {

    public static void main(String[] args) {
        // Customizing the chatbot
        String botName = "Lia";
        Scanner scanner = new Scanner(System.in);
        String input;

        // Array of random responses for unrecognized commands
        String[] randomResponses = {
                "Hmm... That's interesting!",
                "Wow! Never heard of that one.",
                "You're full of surprises today!",
                "I need to look that up, hold on...",
        };

        // Greet the user with enthusiasm
        printLine();
        System.out.println(" Hey there! I'm " + botName);
        System.out.println(" Ready to take on the world? Just let me know what you need!");
        printLine();

        // Chatbot loop: keep asking for input until "bye" is entered
        while (true) {
            input = scanner.nextLine().trim();

            // If the user types "bye", end the loop with a warm farewell
            if (input.equalsIgnoreCase("bye")) {
                printLine();
                System.out.println(" It's been fun! Take care and come back soon!");
                printLine();
                break;
            }

            // If the user types "list", give a custom motivational response
            if (input.equalsIgnoreCase("list")) {
                printLine();
                System.out.println(" Ah, a list! Let’s get things organized, shall we?");
                printLine();
            } else {
                // Randomly choose a fun response for unknown commands
                printLine();
                System.out.println(" " + input);
                System.out.println(" " + getRandomResponse(randomResponses));
                printLine();
            }
        }

        // Close the scanner to prevent resource leak
        scanner.close();
    }

    // Function to print the line
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    // Function to select a random fun response
    public static String getRandomResponse(String[] responses) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(responses.length);
        return responses[randomIndex];
    }
}
