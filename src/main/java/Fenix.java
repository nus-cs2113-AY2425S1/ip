import java.util.Scanner;

public class Fenix {

    public static final int HORIZONTAL_LINE_LENGTH = 60;
    public static final String HORIZONTAL_LINE =
            "~".repeat(HORIZONTAL_LINE_LENGTH);
    public static final String GREETING =
            "Greetings. I am Fenix, your digital assistant.";
    public static final String SERVICE_PROMPT =
            "How may I be of service to you today?";
    public static final String FAREWELL =
            "It has been a pleasure assisting you. Farewell.";
    public static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(GREETING);
        System.out.println(SERVICE_PROMPT);
        System.out.println(HORIZONTAL_LINE);
        String userInput = IN.nextLine();
        // Exit the loop when the user says bye
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println(userInput);
            System.out.println(HORIZONTAL_LINE);
            userInput = IN.nextLine();
        }
        System.out.println(FAREWELL);
        System.out.println(HORIZONTAL_LINE);
        IN.close();
    }
}
