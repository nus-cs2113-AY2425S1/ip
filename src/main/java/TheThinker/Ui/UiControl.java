package TheThinker.Ui;

import TheThinker.Command.TheThinker;

public class UiControl {

    public static void printGreeting() {
        UiControl.printSeparation();
        System.out.println("Hello! I'm " + TheThinker.NAME);
        System.out.println("What can I do for you?");
        UiControl.printSeparation();
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printSeparation() {
        System.out.println("_".repeat(60));
    }
}
