package bron;

import java.util.ArrayList;

import bron.storage.FileStorage;
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

        FileStorage storage = new FileStorage();
        ArrayList<Task> tasks = storage.load();

        ChatBotManager chatBotManager = new ChatBotManager(tasks, tasks.size());
        chatBotManager.start();
    }

    private static void displayIntro() {
        System.out.println(LOGO + "Hello! I'm Bron\n" + "What can I do for you?\n");
    }
}
