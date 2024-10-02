package wildpeace.echo;

import initializer.Initializer;
import wildpeace.exceptions.EmptyCommandException;
import java.util.Scanner;

/**
 * Echoes user input back to the console. Continues to echo until the user inputs "bye".
 * Once "bye" is input, it will exit the loop and reinitialize the program.
 */
public class Echo {

    /**
     * Repeatedly echoes user input back to the console until the user types "bye".
     * After exiting, it reinitializes the program by calling {@link Initializer#initialise(Scanner)}.
     *
     * @param scanner The scanner used to read user input.
     * @throws EmptyCommandException If there is an issue during reinitialization.
     */
    public static void echo(Scanner scanner) throws EmptyCommandException {
        String line;
        boolean exit = false;
        while (!exit) {
            line = scanner.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                exit = true;
            }
            System.out.println(line);
        }
        Initializer.initialise(scanner);
    }
}
