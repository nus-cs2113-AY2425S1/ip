import utils.ActionManager;

import java.util.Scanner;

public class SuBOT {
    public static final String SEPARATOR = "-------------------------------";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greeting();
        System.out.println(SEPARATOR);
        while (ActionManager.isRunning) {
            String command = sc.nextLine();
            System.out.println(SEPARATOR);
            ActionManager.process(command);
            System.out.println(SEPARATOR);
        }
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
