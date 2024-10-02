package atom;

import atom.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public void printDivider() {
        System.out.println("__________________________________________________");
    }

    public void showWelcome() {
        printDivider();

        String logo = "    ____   __________  ________  __       __\n"
                + "   / __ \\ |___    ___||  ____  ||  \\     /  |\n"
                + "  / /__\\ \\    |  |    | |    | || |\\\\   //| |\n"
                + " / /    \\ \\   |  |    | |____| || | \\\\_// | |\n"
                + "/_/      \\_\\  |__|    |________||_|  \\_/  |_|\n";

        System.out.println(logo);

        printDivider();

        System.out.println("Hey there! I'm your friendly chatbot, ATOM!");
        System.out.println("How can i assist you today?");

        printDivider();
    }

    public void showUserGuide() {
        printDivider();

        System.out.println("\nUSER GUIDE:");
        System.out.println("* \"bye\" -> exit program");
        System.out.println("* \"list\" -> view list of tasks");
        System.out.println("* \"mark <task id>\" -> mark task as DONE");
        System.out.println("* \"unmark <task id>\" -> mark task as UNDONE");
        System.out.println("* \"todo <task>\" -> set task as TODO");
        System.out.println("* \"deadline <task> /by <date/time>\" -> set task as DEADLINE");
        System.out.println("* \"event <task> /from <date/time> /to <date/time>\" -> set task as EVENT");
        System.out.println("* \"delete <task id>\" -> delete task from list");
    }

    public String readCommand() {
        String userInput;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter command: ");
        userInput = scanner.nextLine().trim();

        return userInput;
    }

    public void printList(TaskList tasks) {
        if (tasks.getTasksListSize() == 0) {
            System.out.println("Oh oh! List is empty.");
            return;
        }

        int index = 1;

        System.out.println("Here is your list:\n");
        for (Task item : tasks.getTasksList()) {

            System.out.print(index + "." + "[" + item.setTaskType() + "]" +
                    "[" + item.getStatus() + "] " + item.getItem());

            if (item.setTaskType().equals("D")) {
                System.out.print(" (by: " + item.getDueDate() + ")");
            } else if (item.setTaskType().equals("E")) {
                System.out.print(" (from: " + item.getStartDate() + " to: " + item.getEndDate() + ")");
            }

            System.out.println();
            index++;
        }
    }

    public void showLoadingError() {
        System.out.println("File contents corrupted. Error loading contents to list.. :(");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showNumberFormatExceptionError() {
        System.out.println("Task id must be a number!");
        System.out.println("-> Pssst, just a reminder, I'm SPACE sensitive!!");
    }

    public void showMissingDeadlineNameMessage() {
        System.out.println("Hey!! Your deadline task is missing!!");
    }

    public void showMissingEventNameMessage() {
        System.out.println("Really?! An event without a name??");
    }

    public void showInvalidCommandMessage() {
        System.out.println("Me no understand what you saying...");
    }

    public void showTaskStatusMessage(TaskList tasks, int taskId, String command) {
        ArrayList<Task> tasksList = tasks.getTasksList();
        Task currTask = tasksList.get(taskId);

        if (command.equals("mark")) {
            System.out.println("Wonderful! Task successfully marked as DONE!");
        } else {
            System.out.println("Got it. Task successfully marked as UNDONE!");
        }
        System.out.print("> [" + currTask.setTaskType() + "]["
                + currTask.getStatus() + "] " + currTask.getItem());

        if (currTask.setTaskType().equals("D")) {
            System.out.print(" (by: " + currTask.getDueDate() + ")");
        } else if (currTask.setTaskType().equals("E")) {
            System.out.print(" (from: " + currTask.getStartDate() + " to: " +
                    currTask.getEndDate() + ")");
        }

        System.out.println();
    }

    public void showDeleteTaskMessage(TaskList tasks, int taskId) {
        ArrayList<Task> tasksList = tasks.getTasksList();
        Task currTask = tasksList.get(taskId);

        System.out.println("Okie Dokie!! Removed task from list:");
        System.out.print("> [" + currTask.setTaskType() + "]" +
                "[" + currTask.getStatus() + "] " + currTask.getItem());

        if (currTask.setTaskType().equals("D")) {
            System.out.print(" (by: " + currTask.getDueDate() + ")");
        } else if (currTask.setTaskType().equals("E")) {
            System.out.print(" (from: " + currTask.getStartDate() + " to: " + currTask.getEndDate() + ")");
        }

        System.out.println();
        System.out.println("You now have " + tasksList.size() + " tasks in your list.");
    }

    public void showExitMessage() {
        System.out.println("Bye Bye. See ya soon!");
    }
}
