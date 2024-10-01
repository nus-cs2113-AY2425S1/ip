package melchizedek;

import melchizedek.task.TaskList;

import java.util.ArrayList;

public class Ui {
    public static final String SEPARATOR = "\t____________________________________________________________";

    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public static void sayHelloToUser() {
        printSeparator();
        System.out.println("\tHello! I'm Melchizedek.");
        System.out.println("\tHow can I be a blessing to you?");
        printSeparator();
    }

    public static void sayByeToUser() {
        System.out.println("\tGoodbye. Hope to see you again soon! May peace be upon you.");
        printSeparator();
    }

    public static void printInvalidCommand() {
        System.out.println("\tSorry but I don't understand what you mean :(");
        System.out.println("\t\"help\" to get a list of commands.");
    }

    public static void listCommands() {
        System.out.println("\tHere is the list of commands:");
        System.out.println("\t  to add a todo: todo DESCRIPTION");
        System.out.println("\t  to add a deadline: deadline DESCRIPTION /by BY");
        System.out.println("\t  to add an event: event DESCRIPTION /from FROM /to TO");
        System.out.println("\t  to delete a task: delete TASK_NUMBER");
        System.out.println("\t  to mark a task as done: mark TASK_NUMBER");
        System.out.println("\t  to unmark a task as done: unmark TASK_NUMBER");
        System.out.println("\t  to display all tasks on the list: list");
        System.out.println("\t  to exit: bye");
    }

    public static void printDirError() {
        System.err.println("Error. No directory could be created.");
        printSeparator();
    }

    public static void printFileError() {
        System.err.println("Error. No file could be created or found.");
        printSeparator();
    }

    public static void printTaskError() {
        System.err.println("Error. Unable to process task.");
        printSeparator();
    }

    public static void printWriteError() {
        System.err.println("Error. Cannot write to file.");
        printSeparator();
    }

    public static void printTaskList(TaskList taskList) {
        int taskCount = taskList.getTaskCount();
        if (taskCount == 0) {
            System.out.println("\tNo tasks added!");
            return;
        }

        System.out.println("\tSure! Here are the tasks on your list:");

        for (int i = 0; i < taskCount; i++) {
            System.out.print("\t" + (i + 1) + ".");
            System.out.println(taskList.getTaskToString(i));
        }
    }

    public static void printTask(String taskString) {
        System.out.println("\t  " + taskString);
    }

    public static void printFilteredTaskList(ArrayList<String> filteredTaskList) {
        int taskCount = filteredTaskList.size();
        if (taskCount == 0) {
            System.out.println("\tNo matching tasks!");
            return;
        }

        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print("\t" + (i + 1) + ".");
            System.out.println(filteredTaskList.get(i));
        }
    }

    public static void printNumberOfTasks(int taskCount) {
        if (taskCount != 1) {
            System.out.println("\tNow you have " + taskCount + " tasks in the list.");
        } else {
            System.out.println("\tNow you have " + taskCount + " task in the list.");
        }
    }

    public static void printAddedTask(String taskString, int taskCount) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + taskString);
        printNumberOfTasks(taskCount);
    }

    public static void printDeletedTask(String taskString) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + taskString);
    }

    public static void printMarkExample() {
        System.out.println("\tExample: mark 3");
    }

    public static void printUnmarkExample() {
        System.out.println("\tExample: unmark 2" );
    }

    public static void printTodoExample() {
        System.out.println("\tExample: todo read lecture notes");
    }

    public static void printDeadlineExample() {
        System.out.println("\tExample: deadline coding assignment /by 12pm");
    }

    public static void printEventExample() {
        System.out.println("\tExample: event coding lecture /from 2pm /to 4pm" );
    }

    public static void printDeleteExample() {
        System.out.println("\tExample: delete 1");
    }

    public static void printFindExample() {
        System.out.println("\tExample: find notes");
    }

    public static void printTaskHasAlreadyBeenMarked() {
        System.out.println("\tSure! But it was already marked as done?");
    }

    public static void printTaskMarkedAsDone() {
        System.out.println("\tGreat! I've marked this task as done:");
    }

    public static void printTaskHasAlreadyBeenUnmarked() {
        System.out.println("\tHmmm... it was not done in the first place.");
    }

    public static void printTaskUnmarkedAsDone() {
        System.out.println("\tOK, I've marked this task as undone:");
    }

    public static void printUnableToProcessWithoutKey(String key) {
        System.out.println("\tUh oh! I cannot process this task without the key " + key + "!");
    }

    public static void printInvalidTaskNumberException() {
        System.out.println("\tUh oh! Please input a valid task number!");
    }

    public static void printUnspecifiedTaskNumber(String command) {
        System.out.println("\tOh no! Please specify which task number to " + command + ".");
    }

    public static void printUnableToProcessWithoutDescription(String taskType) {
        System.out.println("\tUh oh! I cannot create a " + taskType + " with no description!");
    }

    public static void printUnableToFindWithoutKeyword() {
        System.out.println("\tOh no! I cannot search without a keyword!");
    }
}
