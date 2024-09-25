package aegis;

import java.util.Scanner;
import java.util.ArrayList;

import aegis.task.Task;

public class Ui {
    private final Scanner scanner;
    private final static String AEGIS_LOGO = """
                                                                            \s
                        **                                                  \s
                     *****                                  *               \s
                    *  ***                                 ***              \s
                       ***                                  *               \s
                      *  **                                          ****   \s
                      *  **          ***        ****      ***       * **** *\s
                     *    **        * ***      *  ***  *   ***     **  **** \s
                     *    **       *   ***    *    ****     **    ****      \s
                    *      **     **    ***  **     **      **      ***     \s
                    *********     ********   **     **      **        ***   \s
                   *        **    *******    **     **      **          *** \s
                   *        **    **         **     **      **     ****  ** \s
                  *****      **   ****    *  **     **      **    * **** *  \s
                 *   ****    ** *  *******    ********      *** *    ****   \s
                *     **      **    *****       *** ***      ***            \s
                *                                    ***                    \s
                 **                            ****   ***                   \s
                                             *******  **                    \s
                                            *     ****                      \s
                                                                            \s""";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println(AEGIS_LOGO);
        System.out.println("Hello! This is Aegis, an Anti-Shadow Suppression Weapon and a chatbot.");
        System.out.println("What can I do for you?");
        System.out.println();
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println("^_^ -------------------------------------------------- ^_^");
    }

    public void showLoadingError() {
        System.out.println(" No previous task list found. Starting fresh.");
    }

    public void showError(String message) {
        System.out.println();
        System.out.printf(" Anomaly detected: %s%n", message);
        System.out.println();
    }

    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        System.out.println();
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + matchingTasks.get(i));
        }
        System.out.println();
    }

    public void close() {
        scanner.close();
    }
}
