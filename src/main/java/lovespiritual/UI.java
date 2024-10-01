package lovespiritual;

import lovespiritual.task.Task;

import java.util.ArrayList;

public class UI {
    private static final String SEPARATOR = "_".repeat(30);

    public static void printExitScreen() {
        System.out.println(SEPARATOR);
        System.out.println("Goodbye! (｡•‿•｡) Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    public static void printWelcomeScreen() {
        System.out.println(SEPARATOR);
        System.out.println("Hiya! (✿◠‿◠) I'm lovespiritual, ready to help!");
        System.out.println("How can I assist? (•‿•) I'm all ears!");
        System.out.println(SEPARATOR);
    }

}
