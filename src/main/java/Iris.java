import java.util.Scanner;

public class Iris {
    public static boolean printText(String text) {
        boolean isEnded = false;
        if (text.equalsIgnoreCase("bye")) {
            text = "Bye. Hope to see you again soon!";
            isEnded = true;
        }
        System.out.println(text);
        System.out.println("---------------------------------------------");
        return isEnded;
    }
    public static void main(String[] args) {
        // Greets
        printText("Hello! I'm Iris!\n" + "What can I do for you?");
        boolean isEnded = false;
        while (!isEnded) {
            Scanner in = new Scanner(System.in); // Initialisation
            String line = in.nextLine();
            isEnded = printText(line);
        }
    }
}
