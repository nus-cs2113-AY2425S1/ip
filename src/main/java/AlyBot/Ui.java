package AlyBot;

import Task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner in = new Scanner(System.in);
    private final String LINE_SEPARATOR = "=".repeat(112);

    public void printLine() {
        System.out.println(LINE_SEPARATOR);
    }

    public void showWelcome() {
        String LOGO = "    _      _     _   _\n"
                + "   / \\    | |   \\ \\ / /\n"
                + "  / _ \\   | |    \\ V / \n"
                + " / ___ \\  | |__   | |  \n"
                + "/_/   \\_\\ |____|  |_|  \n";
        System.out.println("Hello, my name is \n"+ LOGO);
        printLine();
    }

    public String readCommand() {
        String input = in.nextLine();
        return input.trim();
    }

    public void startingMessage() {
        System.out.println("I'm hungry, what do you want?");
        printLine();
        help();
    }

    public void help() {
        System.out.println("Possible commands: \n"
                + "1. 'echo' followed by input to receive echo\n"
                + "2. 'list' to see your list of tasks\n"
                + "3. 'todo/deadline/event' to add that type of task\n"
                + "4. 'mark/unmark' with a number to toggle that task's status\n"
                + "5. 'delete' with a number to delete that task\n"
                + "6. 'filter' with a date to see all tasks scheduled on that date\n"
                + "7. 'help' to see the command list\n"
                + "8. 'exit' to exit");
    }

    public void showLoadingError() {
        System.out.println("Oops! Something went wrong...");
    }

    public void showError (String message) {
        System.out.println(message);
    }

    public void addMessage(String instructions) {
        System.out.println("Added this task: " + instructions.trim());
    }

    public void showTaskSize(int taskSize) {
        System.out.println("You have " + taskSize + " tasks in your list now.");
    }

    public void showList(TaskList taskList) throws AlyException {
        int count = 1;
        System.out.println("Your task list:");
        try {
            for (Task listItem : taskList.getList()) {
                System.out.println(count + "." + listItem);
                count++;
            }
        } catch (Exception e) {
            throw new AlyException("I am not sure what happened lmao", e);
        }
        if (count == 1) {
            System.out.println("No tasks means can lepak!");
        } else {
            System.out.println("Wah shag, good luck with your tasks bro!");
        }
    }

    public void showStatusChange(TaskList taskList, int indexNumToToggle, String firstWord) throws AlyException {
        if (firstWord.equals("mark")) {
            System.out.println("\"" + taskList.find(indexNumToToggle).getDescription() + "\" marked as done!");
        } else if (firstWord.equals("unmark")) {
            System.out.println("\"" + taskList.find(indexNumToToggle).getDescription() + "\" marked as undone!");
        } else {
            throw new AlyException("Something went wrong!");
        }
    }

    public void showDelete(TaskList taskList, int indexNumToDelete) {
        System.out.println("Deleted this task: " + taskList.find(indexNumToDelete).getDescription());
    }
}
