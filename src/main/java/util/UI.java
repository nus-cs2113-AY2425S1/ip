package util;

import java.util.Scanner;

public final class UI {
    public static final String SEPARATOR = "-------------------------------";
    private static final Scanner sc = new Scanner(System.in);
    public static void greeting() {
        final String logo = """
                       ____  ____  ______
           _______  __/ __ )/ __ \\/_  __/
          / ___/ / / / __  / / / / / /
         (__  ) /_/ / /_/ / /_/ / / /
        /____/\\__,_/_____/\\____/ /_/
        """;

        System.out.println(logo);
        System.out.println("How can I help you today?");
        System.out.println(UI.SEPARATOR);
    }

    public static void goodbye() {
        System.out.println("Bye bye...");
        System.out.println("System shutting down...");
        System.out.println("Goodbye world!");
    }

    /**
     * Get command from user input
     * @return First line of user input as input command
     */
    public static String getCommand() {
        return sc.nextLine();
    }

    /**
     * Display exception in a user-friendly way
     * @param e Exception needs to be displayed
     */
    public static void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Display exception in a user-friendly way
     * @param padding String to add before displaying messages
     * @param e Exception needs to be displayed
     */
    public static void showError(String padding, Exception e) {
        System.out.println(padding + e.getMessage());
    }
}
