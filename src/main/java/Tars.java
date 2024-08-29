import java.util.Scanner;

public class Tars {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // Opening speech.
        System.out.println("------------------------------------------------------------");
        System.out.println("Hello! I'm Tars.");
        System.out.println("Ready to conquer the world? Or maybe just help with something smaller? What can I do for you?");
        System.out.println("------------------------------------------------------------");

        // Waiting for user input.
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true)
        {
            input = scanner.nextLine();
            if (input.equals("bye"))
            {
                System.out.println("------------------------------------------------------------");
                System.out.println("    Oh no, you're leaving already? Fine, be that way. But don't forget, I'll be right here waiting... forever!");
                System.out.println("------------------------------------------------------------");
                break;
            }
            else
            {
                System.out.println("------------------------------------------------------------");
                System.out.println("    " + input);
                System.out.println("------------------------------------------------------------");
            }
        }

    }
}