import java.util.Scanner;

public class Echo {
    public static void echo(Scanner scanner)
    {
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
