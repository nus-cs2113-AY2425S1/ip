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

    public static void printList(ArrayList <Task> tasks) {
        System.out.println(SEPARATOR);
        if (tasks.size() == 0) {
            System.out.println("There is nothing on your list yet!");
        } else {
            System.out.println("Here's your list! (・∀・) Ready to tackle it?");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(SEPARATOR);
    }

    public void printError(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    public void printUnexpectedError() {
        System.out.println(SEPARATOR);
        System.out.println("Oh no! (＞﹏＜) Something went a little wrong...");
        System.out.println(SEPARATOR);
    }

}
