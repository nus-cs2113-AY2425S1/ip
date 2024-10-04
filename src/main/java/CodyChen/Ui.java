package CodyChen;

import CodyChen.Task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner in = new Scanner(System.in);
    public void showWelcome() {
        System.out.println("   _____          _        _____ _                \n" +
                "  / ____|        | |      / ____| |               \n" +
                " | |     ___   __| |_   _| |    | |__   ___ _ __  \n" +
                " | |    / _ \\ / _` | | | | |    | '_ \\ / _ \\ '_ \\ \n" +
                " | |___| (_) | (_| | |_| | |____| | | |  __/ | | |\n" +
                "  \\_____\\___/ \\__,_|\\__, |\\_____|_| |_|\\___|_| |_|\n" +
                "                     __/ |                        \n" +
                "                    |___/                         ");
        System.out.println(">CodyChen: \n\tCody Chen Welcomes You! Please enter a command:");
    }

    public String readCommand() {
        String line = in.nextLine();
        System.out.println(">You:\n\t" + line + "\n--------------------------------------------------------------");
        return line;
    }

    public void showEnd() {
        System.out.println("   ▄██████▄   ▄██████▄   ▄██████▄  ████████▄  ▀█████████▄  ▄██   ▄      ▄████████ \n" +
                "  ███    ███ ███    ███ ███    ███ ███   ▀███   ███    ███ ███   ██▄   ███    ███ \n" +
                "  ███    █▀  ███    ███ ███    ███ ███    ███   ███    ███ ███▄▄▄███   ███    █▀  \n" +
                " ▄███        ███    ███ ███    ███ ███    ███  ▄███▄▄▄██▀  ▀▀▀▀▀▀███  ▄███▄▄▄     \n" +
                "▀▀███ ████▄  ███    ███ ███    ███ ███    ███ ▀▀███▀▀▀██▄  ▄██   ███ ▀▀███▀▀▀     \n" +
                "  ███    ███ ███    ███ ███    ███ ███    ███   ███    ██▄ ███   ███   ███    █▄  \n" +
                "  ███    ███ ███    ███ ███    ███ ███   ▄███   ███    ███ ███   ███   ███    ███ \n" +
                "  ████████▀   ▀██████▀   ▀██████▀  ████████▀  ▄█████████▀   ▀█████▀    ██████████ \n" +
                "                                                                                  ");
        System.out.println(">CodyChen: \n\tThank You for using CodyChen. Have a nice day.");
    }

    public void listPopulated(TaskList taskList) {
        System.out.println(">CodyChen: \n\tCodyChen has returned your list as follows:");
        int loop = 1;
        ArrayList<Task> tasks = taskList.getTask();
        for (Task task : tasks) {
            System.out.print(loop + "."); // Prints object Array
            System.out.print("[" + task.getType() + "]" + task.getStatusIcon() + task.getDescription());
            switch (task.getType()) {
            case 'T':
                System.out.println();
                break;
            case 'D':
                System.out.println("(by: " + task.formattedDeadline() + ")");
                break;
            case 'E':
                System.out.println("(by: " + task.formattedDeadline() + task.formattedEvent());
                break;
            }
            loop += 1;
        }
        System.out.println(">CodyChen: \n\tDone! Please enter the next command:");
    }

    public void toFind(TaskList taskList, String wordtoFind) {
        System.out.println(">CodyChen: \n\tShowing relevant searches ");
        int loop = 1;
        ArrayList<Task> tasks = taskList.getTask();
        for (Task task : tasks) {
            if (task.getDescription().contains(wordtoFind)) {
                System.out.print(loop + "."); // Prints object Array
                System.out.print("[" + task.getType() + "]" + task.getStatusIcon() + task.getDescription());
                switch (task.getType()) {
                case 'T':
                    System.out.println();
                    break;
                case 'D':
                    System.out.println("(by: " + task.formattedDeadline() + ")");
                    break;
                case 'E':
                    System.out.println("(by: " + task.formattedDeadline() + task.formattedEvent());
                    break;
                }
            }
            loop += 1;
        }
        System.out.println(">CodyChen: \n\tDone! Please enter the next command:");
    }

    public void listEmpty() {
        System.out.println(">CodyChen: \n\tDone! But...Your list seems lonely here >< Time to add some tasks!");
    }

    public void showTodoAdded(TaskList tasks) {
        int index = tasks.size() - 1;
        System.out.println(">CodyChen:\n\t CodyChen has added this TODO task for you:");
        System.out.println("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription());
        System.out.println(">CodyChen: \n\tDone! Please enter the next command:");
    }

    public void showDeadlineAdded(TaskList tasks) {
        int index = tasks.size() - 1;
        System.out.println(">CodyChen:\n\t CodyChen has added this DEADLINE task for you:");
        System.out.println("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription() +
                "(by: " + tasks.getTask(index).formattedDeadline() + ")");
        System.out.println(">CodyChen: \n\tDone! Please enter the next command:");
    }

    public void showEventAdded(TaskList tasks) {
        int index = tasks.size() - 1;
        System.out.println(">CodyChen:\n\t CodyChen has added this EVENT task for you:");
        System.out.println("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription() +
                "(by: " + tasks.getTask(index).formattedDeadline() +
                tasks.getTask(index).formattedEvent());
        System.out.println(">CodyChen: \n\tDone! Please enter the next command:");
    }

    public void showTaskDeleted(TaskList tasks, int index) {
        switch (tasks.getTask(index).getType()) {
        case 'T':
            System.out.println(">CodyChen:\n\t CodyChen has removed this task for you:");
            System.out.print((index + 1) + ".");
            System.out.print("[" + tasks.getTask(index).getType() + "]" +
                    tasks.getTask(index).getStatusIcon() +
                    tasks.getTask(index).getDescription());
            System.out.println();
            break;
        case 'D':
            System.out.println(">CodyChen:\n\t CodyChen has removed this task for you:");
            System.out.print((index + 1) + ".");
            System.out.print("[" + tasks.getTask(index).getType() + "]" +
                    tasks.getTask(index).getStatusIcon() +
                    tasks.getTask(index).getDescription());
            System.out.println("(by: " + tasks.getTask(index).formattedDeadline() + ")");
            break;
        case 'E':
            System.out.println(">CodyChen:\n\t CodyChen has removed this task for you:");
            System.out.print((index + 1) + ".");
            System.out.print("[" + tasks.getTask(index).getType() + "]" +
                    tasks.getTask(index).getStatusIcon() +
                    tasks.getTask(index).getDescription());
            System.out.println("(by: " + tasks.getTask(index).formattedDeadline() +
                    tasks.getTask(index).formattedEvent());
            break;
        }
        System.out.println(">CodyChen: \n\tDone! Please enter the next command:");
    }

    public void showTaskMarked(TaskList tasks, int index) {
        System.out.println(">CodyChen:\n\t CodyChen has marked this task for you:");
        System.out.print("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription());
        switch (tasks.getTask(index).getType()) {
        case 'T':
            System.out.println();
            break;
        case 'D':
            System.out.println("(by: " + tasks.getTask(index).formattedDeadline() + ")");
            break;
        case 'E':
            System.out.println("(by: " + tasks.getTask(index).formattedDeadline() +
                    tasks.getTask(index).formattedEvent());
            break;
        }
        System.out.println(">CodyChen: \n\tDone! Please enter the next command:");
    }

    public void showTaskUnmarked(TaskList tasks, int index) {
        System.out.println(">CodyChen:\n\t CodyChen has unmarked this task for you:");
        System.out.print("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription());
        switch (tasks.getTask(index).getType()) {
        case 'T':
            System.out.println();
            break;
        case 'D':
            System.out.println("(by: " + tasks.getTask(index).formattedDeadline() + ")");
            break;
        case 'E':
            System.out.println("(by: " + tasks.getTask(index).formattedDeadline() +
                    tasks.getTask(index).formattedEvent());
            break;
        }
        System.out.println(">CodyChen: \n\tDone! Please enter the next command:");
    }
}
