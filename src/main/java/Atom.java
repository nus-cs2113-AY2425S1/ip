import java.util.Scanner;
import java.util.Arrays;

public class Atom {

    public static final int TODO_START_INDEX = 5;
    public static final int DEADLINE_START_INDEX = 9;
    public static final int BY_START_INDEX_OFFSET = 4;
    public static final int EVENT_START_INDEX = 6;
    public static final int FROM_START_INDEX_OFFSET = 6;
    public static final int TO_START_INDEX_OFFSET = 4;

    public static void printDivider() {
        System.out.println("__________________________________________________");
    }

    private static void printExceptionMessage() {
        System.out.println("Oops! Incorrect command format.");
        System.out.println("Check out the user guide for command format!");
    }

    public static void printList(Task[] list) {
        int index = 1;

        System.out.println("Here is your list:\n");
        for (Task item : list) {

            System.out.print(index + "." + "[" + item.setTaskType() + "]" +
                    "[" + item.getStatus() + "] " + item.getItem());

            if (item.setTaskType().equals("D")) {
                System.out.print(" (by: " + item.getBy() + ")");
            } else if (item.setTaskType().equals("E")) {
                System.out.print(" (from: " + item.getFrom() + "to: " + item.getTo() + ")");
            }

            System.out.println();
            index++;
        }
    }

    public static void markAsDone(Task[] list, int id) {
        if (id >= Task.getTaskCount() || id < 0) {
            System.out.println("Whoops! Task id not found.");
            return;
        }

        Task currTask = list[id];
        currTask.markAsDone();

        System.out.println("Wonderful! Task successfully marked as DONE!");
        System.out.println("> [" + currTask.setTaskType() + "]["
                + currTask.getStatus() + "] " + currTask.getItem());

    }

    public static void markAsUndone(Task[] list, int id) {
        if (id >= Task.getTaskCount() || id < 0) {
            System.out.println("Whoops! Task id not found.");
            return;
        }

        Task currTask = list[id];
        currTask.markAsUndone();

        System.out.println("Got it. Task successfully marked as UNDONE!");
        System.out.println("> [" + currTask.setTaskType() + "]["
                + currTask.getStatus() + "] " + currTask.getItem());

    }

    public static void main(String[] args) {

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
        System.out.println("\nUSER GUIDE:");
        System.out.println("* \"bye\" -> exit program");
        System.out.println("* \"list\" -> view list of tasks");
        System.out.println("* \"mark <task id>\" -> mark task as DONE");
        System.out.println("* \"unmark <task id>\" -> mark task as UNDONE");
        System.out.println("* \"todo <task>\" -> set task as TODO");
        System.out.println("* \"deadline <task> /by <date/time>\" -> set task as DEADLINE");
        System.out.println("* \"event <task> /from <date/time> /to <date/time>\" -> set task as EVENT");

        printDivider();

        String line;
        Scanner scanner = new Scanner(System.in);
        Task[] tasksList = new Task[100];

        System.out.print("Enter command: ");

        line = scanner.nextLine().trim();
        String[] words = line.split(" ");
        String keyword = words[0].toLowerCase();

        while (!line.equalsIgnoreCase("bye")) {

            printDivider();

            if (line.equalsIgnoreCase("list")) {
                if (Task.getTaskCount() == 0) {
                    System.out.println("Oh oh! List is empty.");
                } else {
                    printList(Arrays.copyOf(tasksList, Task.getTaskCount()));
                }

            } else if (keyword.equals("mark") || (keyword.equals("unmark"))) {
                try {
                    int taskId = Integer.parseInt(words[1]) - 1;

                    if (keyword.equals("mark")) {
                        markAsDone(tasksList, taskId);
                    } else {
                        markAsUndone(tasksList, taskId);
                    }
                } catch (Exception exception) {
                    printExceptionMessage();
                }

            } else if (keyword.equals("todo")) {
                Todo todo = new Todo(line.substring(TODO_START_INDEX));
                tasksList[Task.getTaskCount() - 1] = todo;

                System.out.println("Gotcha! TODO task added to list!");
                System.out.println("> [" + todo.setTaskType() + "]" + "[ ] " + todo.getItem());
                System.out.println("You now have " + Task.getTaskCount() + " tasks in your list!");

            } else if (keyword.equals("deadline")) {
                try {
                    int deadlineEndIndex = line.indexOf('/');
                    String deadlineName = line.substring(DEADLINE_START_INDEX, deadlineEndIndex - 1);
                    String by = line.substring(deadlineEndIndex + BY_START_INDEX_OFFSET);

                    Deadline deadline = new Deadline(deadlineName, by);
                    tasksList[Task.getTaskCount() - 1] = deadline;

                    System.out.println("Gotcha! DEADLINE task added to list");
                    System.out.println("> [" + deadline.setTaskType() + "]" + "[ ] "
                            + deadline.getItem() + " (by: " + deadline.getBy() + ")");
                    System.out.println("You now have " + Task.getTaskCount() + " tasks in your list!");

                } catch (Exception exception) {
                    printExceptionMessage();
                }

            } else if (keyword.equals("event")) {
                try {
                    int eventEndIndex = line.indexOf('/');
                    String eventName = line.substring(EVENT_START_INDEX, eventEndIndex - 1);

                    int fromEndIndex = line.lastIndexOf('/');
                    String from = line.substring(eventEndIndex + FROM_START_INDEX_OFFSET, fromEndIndex);
                    String to = line.substring(fromEndIndex + TO_START_INDEX_OFFSET);

                    Event event = new Event(eventName, from, to);
                    tasksList[Task.getTaskCount() - 1] = event;

                    System.out.println("Gotcha! EVENT task added to list");
                    System.out.println("> [" + event.setTaskType() + "]" + "[ ] " + event.getItem()
                            + " (from: " + event.getFrom() + "to: " + event.getTo() + ")");
                    System.out.println("You now have " + Task.getTaskCount() + " tasks in your list!");

                } catch (Exception exception) {
                    printExceptionMessage();
                }

            } else {
                Task task = new Task(line);
                tasksList[Task.getTaskCount() - 1] = task;

                System.out.println("Added \"" + line + "\" to list");
            }

            printDivider();

            System.out.print("Enter command: ");

            line = scanner.nextLine().trim();
            words = line.split(" ");
            keyword = words[0].toLowerCase();
        }

        printDivider();

        System.out.println("Bye Bye. See ya soon!");

        printDivider();

    }
}
