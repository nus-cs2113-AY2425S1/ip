package utils;

import java.util.ArrayList;

import Tasks.Task;

public class Ui {
    static final String LOGO =   
            "   ______      __                       \n"+
            "  / ____/_  __/ /_  ____  ____  ___     \n"+
            " / /   / / / / __ \\/ __ \\/ __ \\/ _ \\\n"+
            "/ /___/ /_/ / /_/ / /_/ / / / /  __/    \n"+
            "\\____/\\__,_/_.___/\\____/_/ /_/\\___/ \n";
    static final String CHAT_PREFIX = "\n(Cubone) ";
    static final String CHAT_BAR = "---------------------------------";

    /**
     * Prints a welcome message from Cubone.
     * The welcome message includes the Cubone logo and a chat bar.
     */
    public void showWelcomeMsg(boolean LogFileRead, ArrayList<Task> inputed_tasks) {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm Cubone");
        if (LogFileRead) {
            System.out.println("I have read " + inputed_tasks.size() + " tasks from the Log file");
        }
        System.out.println("What can I do for you?");
        System.out.println(CHAT_BAR);
    }

    /**
     * Prints a farewell message.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine(boolean prefix) {
        if (prefix){
            System.out.println(CHAT_BAR + CHAT_PREFIX);
        }else{
            System.out.println(CHAT_BAR);
        }
    }

    public void showLine() {
        System.out.println(CHAT_BAR);
    }
}
