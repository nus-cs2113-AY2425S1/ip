package wildpeace.task;

import initializer.Initializer;
import initializer.LLMChat;
import wildpeace.exceptions.EmptyCommandException;

import java.util.Scanner;

public class Echo {
    public static void echo(Scanner scanner) throws EmptyCommandException {
        String line;
        boolean exit = false;
        while (!exit) {
            Scanner in = scanner;
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                exit = true;
            }
            System.out.println(line);
        }
        Initializer.initialise(scanner, new LLMChat());
    }
}
