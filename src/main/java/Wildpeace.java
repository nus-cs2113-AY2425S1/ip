import initializer.Initializer;
import wildpeace.exceptions.EmptyCommandException;

import java.util.Scanner;

public class Wildpeace {

    public static final String LOGO = "__        ___ _     _                           \n" +
            "\\ \\      / (_) | __| |_ __   ___  __ _  ___ ___ \n" +
            " \\ \\ /\\ / /| | |/ _` | '_ \\ / _ \\/ _` |/ __/ _ \\\n" +
            "  \\ V  V / | | | (_| | |_) |  __/ (_| | (_|  __/\n" +
            "   \\_/\\_/  |_|_|\\__,_| .__/ \\___|\\__,_|\\___\\___|\n" +
            "                     |_|                        ";

    public static void main(String[] args) throws EmptyCommandException {

        String logo = LOGO;
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);

        Initializer.initialise(scanner);
    }
}
// Dummy Commit to test Merging of different branches, to be deleted