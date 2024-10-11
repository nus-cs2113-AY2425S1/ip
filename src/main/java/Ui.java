import java.io.IOException;

public class Ui {

    public static void printEntry() {
        printSeparator();
        System.out.println("Hello!");
        System.out.println("I am ListBot, how can I help?");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    public static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    public static void printExit() {
        printSeparator();
        System.out.println("Your list has been saved successfully.");
        printSeparator();
        System.out.println("Let me know if I can help again!");
        System.out.println("Bye!");
        printSeparator();
    }

    public static void printAddConfirm(TaskList.Task task) throws IOException {
        printSeparator();
        System.out.println("Added: " + task.toString());
        printSeparator();
        Storage.clear();
        Storage.save();
    }

    public static void printMarkConfirm(TaskList.Task task) throws IOException {
        printSeparator();
        System.out.println("Marked the following task: ");
        System.out.println(task.toString());
        printSeparator();
        Storage.clear();
        Storage.save();
    }

    public static void printUnmarkConfirm(TaskList.Task task) throws IOException {
        printSeparator();
        System.out.println("Unmarked the following task:");
        System.out.println(task.toString());
        printSeparator();
        Storage.clear();
        Storage.save();
    }

    public static void printDeleteConfirm(TaskList.Task task) throws IOException {
        printSeparator();
        System.out.println("Deleted the following task:");
        System.out.println(task.toString());
        printSeparator();
        Storage.clear();
        Storage.save();
    }

    public static void printInvalidCommand() {
        printSeparator();
        System.out.println("I don't understand.");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    public static void printInvalidDeadline() {
        printSeparator();
        System.out.println("This is an invalid deadline.");
        System.out.println("Here's the deadline creation syntax: ");
        System.out.println("deadline *task* /by *by*");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    public static void printInvalidTodo() {
        printSeparator();
        System.out.println("This is an invalid todo.");
        System.out.println("Here's the todo creation syntax: ");
        System.out.println("todo *task*");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    public static void printInvalidEvent() {
        printSeparator();
        System.out.println("This is an invalid event.");
        System.out.println("Here's the event creation syntax: ");
        System.out.println("event *task* /from *from* /to *to*");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    public static void printInvalidMark() {
        printSeparator();
        System.out.println("This is an invalid mark statement.");
        System.out.println("Here's the mark syntax: ");
        System.out.println("mark *task_number*");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    public static void printInvalidUnmark() {
        printSeparator();
        System.out.println("This is an invalid unmark statement.");
        System.out.println("Here's the unmark syntax: ");
        System.out.println("unmark *task_number*");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    public static void printInvalidDelete() {
        printSeparator();
        System.out.println("This is an invalid delete statement.");
        System.out.println("Here's the delete syntax: ");
        System.out.println("delete *task_number*");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    public static void printNonExistentTask() {
        printSeparator();
        System.out.println("This task doesn't exist!");
        printSeparator();
    }

    public static void printAlreadyMarked() {
        printSeparator();
        System.out.println("This task is already marked!");
        printSeparator();
    }

    public static void printAlreadyUnmarked() {
        printSeparator();
        System.out.println("This task is already unmarked!");
        printSeparator();
    }

    public static void printEmptyList() {
        printSeparator();
        System.out.println("The list is empty!");
        printSeparator();
    }

    public static void printSaveFileCreated() {
        printSeparator();
        System.out.println("Save file created successfully.");
        printSeparator();
    }

    public static void printLoadConfirm() {
        System.out.println("I've loaded the below save file:");
        for (int i = 0; i < TaskList.listCount; i += 1) {
            System.out.println(TaskList.tasks[i].toString());
        }
        printSeparator();
    }

    public static void printHelp() {
        printSeparator();
        System.out.println("All actions:");
        System.out.println("To add a deadline: deadline *task* /by *by*");
        System.out.println("To add a todo: todo *task*");
        System.out.println("To add a event: event *task* /from *from* /to *to*");
        System.out.println("To mark a task: mark *task_number*");
        System.out.println("To unmark a task: unmark *task_number*");
        System.out.println("To delete a task: delete *task_number*");
        System.out.println("To list all tasks: list");
        System.out.println("To exit: bye");
        printSeparator();
    }

    public static void printNoTaskFound() {
        printSeparator();
        System.out.println("I found no matching tasks in the list!");
        printSeparator();
    }

}
