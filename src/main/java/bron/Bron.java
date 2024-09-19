package bron;

import java.util.ArrayList;
import bron.task.Task;

public class Bron {


    public static final String LOGO = """
              ____    ____      ____    _     _
             |  _ \\  |   _\\   /  __  \\ | \\   | |
             | |_) | | |_) |  | |  | | |  \\  | |\s
             |  _ <  | ___/   | |  | | | |\\\\ | |
             | |_) | | | \\ \\  | |__| | | | \\\\| |
             |____/  |_|  \\_\\  \\____/  |_|  \\__|
            """;

    public static void main(String[] args) {
        displayIntro();

        ArrayList<Task> tasks = new ArrayList<>();
        int taskCount = 0;

        ChatBotManager chatBotManager = new ChatBotManager(tasks, taskCount);
        chatBotManager.start();
    }

    private static Task[] initializeTasks() {
        return new Task[100];
    }

    private static void displayIntro() {
        System.out.println(LOGO + "Hello! I'm Bron\n" + "What can I do for you?\n");
    }
}
