import utils.ActionManager;

import java.util.Scanner;

import static utils.ActionManager.process;
import static utils.ActionManager.save;
import static utils.ActionManager.load;

public class SuBOT {
    public static final String SEPARATOR = "-------------------------------";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        load();
        greeting();
        System.out.println(SEPARATOR);
        while (ActionManager.isRunning) {
            String command = sc.nextLine();
            System.out.println(SEPARATOR);
            process(command);
            System.out.println(SEPARATOR);
        }
        save();
        goodbye();
    }

    private static void goodbye() {
        System.out.println("Bye bye...");
        System.out.println("System shutting down...");
        System.out.println("Goodbye world!");
    }

    private static void greeting() {
        final String logo = """
                       ____  ____  ______
           _______  __/ __ )/ __ \\/_  __/
          / ___/ / / / __  / / / / / /
         (__  ) /_/ / /_/ / /_/ / / /
        /____/\\__,_/_____/\\____/ /_/
        """;

        System.out.println(logo);
        System.out.println("How can I help you today?");
    }
}
