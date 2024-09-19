package atom;

import atom.exception.EmptyDeadlineException;
import atom.exception.EmptyEventException;
import atom.exception.EmptyTaskIdException;
import atom.exception.EmptyTodoException;
import atom.exception.InvalidDeadlineFormatException;
import atom.exception.InvalidEventFormatException;
import atom.exception.TaskIdOutOfBoundsException;
import atom.task.Deadline;
import atom.task.Event;
import atom.task.Task;
import atom.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class Atom {

    public static final int MAX_NUMBER_OF_TASKS = 100;
    public static final int TODO_START_INDEX = 5;
    public static final int DEADLINE_START_INDEX = 9;
    public static final int BY_START_INDEX_OFFSET = 4;
    public static final int EVENT_START_INDEX = 6;
    public static final int FROM_START_INDEX_OFFSET = 6;
    public static final int TO_START_INDEX_OFFSET = 4;

    public static void printDivider() {
        System.out.println("__________________________________________________");
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
                System.out.print(" (from: " + item.getFrom() + " to: " + item.getTo() + ")");
            }

            System.out.println();
            index++;
        }
    }

    public static void markAsDone(Task[] list, int id) {
        Task currTask = list[id];
        currTask.markAsDone();

        System.out.println("Wonderful! Task successfully marked as DONE!");
        System.out.println("> [" + currTask.setTaskType() + "]["
                + currTask.getStatus() + "] " + currTask.getItem());

    }

    public static void markAsUndone(Task[] list, int id) {
        Task currTask = list[id];
        currTask.markAsUndone();

        System.out.println("Got it. Task successfully marked as UNDONE!");
        System.out.println("> [" + currTask.setTaskType() + "]["
                + currTask.getStatus() + "] " + currTask.getItem());
    }

    private static void addTodoTask(String line, Task[] tasksList) {
        Todo todo = new Todo(line.substring(TODO_START_INDEX));
        tasksList[Task.getTaskCount() - 1] = todo;

        System.out.println("Gotcha! TODO task added to list!");
        System.out.println("> [" + todo.setTaskType() + "]" + "[ ] " + todo.getItem());
        System.out.println("You now have " + Task.getTaskCount() + " tasks in your list!");
    }

    private static void addDeadlineTask(String deadlineName, String by, Task[] tasksList) {
        Deadline deadline = new Deadline(deadlineName.trim(), by.trim());
        tasksList[Task.getTaskCount() - 1] = deadline;

        System.out.println("Gotcha! DEADLINE task added to list");
        System.out.println("> [" + deadline.setTaskType() + "]" + "[ ] "
                + deadline.getItem() + " (by: " + deadline.getBy() + ")");
        System.out.println("You now have " + Task.getTaskCount() + " tasks in your list!");
    }

    private static void addEventTask(String eventName, String from, String to, Task[] tasksList) {
        Event event = new Event(eventName.trim(), from.trim(), to.trim());
        tasksList[Task.getTaskCount() - 1] = event;

        System.out.println("Gotcha! EVENT task added to list");
        System.out.println("> [" + event.setTaskType() + "]" + "[ ] " + event.getItem()
                + " (from: " + event.getFrom() + " to: " + event.getTo() + ")");
        System.out.println("You now have " + Task.getTaskCount() + " tasks in your list!");
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
        Task[] tasksList = new Task[MAX_NUMBER_OF_TASKS];

        File folder = new File("./data");
        File file = new File("./data/AtomList.txt");

        //create directory if it does not exist
        if (folder.mkdir()) {
            System.out.println("Folder data created.");
        } else {
            System.out.println("Folder already exists.");
        }

        //create txt file if it does not exist
        try {
            if (file.createNewFile()) {
                System.out.println("File created.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Oops!! An error occurred.");
        }

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
                    if (words.length == 1) {
                        throw new EmptyTaskIdException();
                    }

                    int taskId = Integer.parseInt(words[1]) - 1;

                    if (taskId >= Task.getTaskCount() || taskId < 0) {
                        throw new TaskIdOutOfBoundsException();
                    }

                    if (keyword.equals("mark")) {
                        markAsDone(tasksList, taskId);
                    } else {
                        markAsUndone(tasksList, taskId);
                    }

                } catch (EmptyTaskIdException e) {
                    System.out.println("Please specify the task id!!");
                } catch (NumberFormatException e) {
                    System.out.println("Task id must be a number!");
                } catch (TaskIdOutOfBoundsException e) {
                    System.out.println("Invalid task id");
                    System.out.println("-> Use the \"list\" command to view your tasks");
                }

            } else if (keyword.equals("todo")) {
                try {
                    if (words.length == 1) {
                        throw new EmptyTodoException();
                    }

                    addTodoTask(line, tasksList);

                } catch (EmptyTodoException e) {
                    System.out.println("Erm... what's the name of the task again??");
                }

            } else if (keyword.equals("deadline")) {
                try {
                    if (words.length == 1) {
                        throw new EmptyDeadlineException();
                    }

                    int deadlineEndIndex = line.indexOf("/by ");
                    if (deadlineEndIndex == -1) {
                        throw new InvalidDeadlineFormatException();
                    }

                    String deadlineName = line.substring(DEADLINE_START_INDEX, deadlineEndIndex);
                    String by = line.substring(deadlineEndIndex + BY_START_INDEX_OFFSET);

                    if (deadlineName.trim().isEmpty()) {
                        System.out.println("Hey!! Your deadline task is missing!!");
                    } else {
                        addDeadlineTask(deadlineName, by, tasksList);
                    }

                } catch (EmptyDeadlineException e) {
                    System.out.println("Huh?? Cannot identify deadline task..");
                } catch (InvalidDeadlineFormatException e) {
                    System.out.println("Incorrect command format!! Try again..\n");
                    System.out.println("Maybe this might be of assistance:");
                    System.out.println("-> \"deadline <task> /by <date/time>\"");
                }

            } else if (keyword.equals("event")) {
                try {
                    if (words.length == 1) {
                        throw new EmptyEventException();
                    }

                    int eventEndIndex = line.indexOf("/from ");
                    if (eventEndIndex == -1) {
                        throw new InvalidEventFormatException();
                    }

                    String eventName = line.substring(EVENT_START_INDEX, eventEndIndex);

                    int fromEndIndex = line.lastIndexOf("/to ");
                    if (fromEndIndex == -1) {
                        throw new InvalidEventFormatException();
                    }

                    String from = line.substring(eventEndIndex + FROM_START_INDEX_OFFSET, fromEndIndex);
                    String to = line.substring(fromEndIndex + TO_START_INDEX_OFFSET);

                    if (eventName.trim().isEmpty()) {
                        System.out.println("Really?! An event without a name??");
                    } else {
                        addEventTask(eventName, from, to, tasksList);
                    }

                } catch (EmptyEventException e) {
                    System.out.println("Guess what's missing?? Your event task silly!!");
                } catch (InvalidEventFormatException e) {
                    System.out.println("Incorrect command format!! Try again..\n");
                    System.out.println("Maybe this might be of assistance:");
                    System.out.println("-> \"event <task> /from <date/time> /to <date/time>\"");
                }

            } else {
                System.out.println("Me no understand what you saying...");
            }

            printDivider();

            System.out.print("Enter command: ");

            line = scanner.nextLine().trim();
            words = line.split(" ");
            keyword = words[0].toLowerCase();
        }

        printDivider();

        //write data into AtomList.txt file
        try {
            FileWriter fw = new FileWriter(file);

            Task[] populatedTasksList = Arrays.copyOf(tasksList, Task.getTaskCount());
            for (Task task : populatedTasksList) {
                String separator = " | ";
                String doneStatus = (task.getStatus().equals("X")) ? "1" : "0";

                fw.write(task.setTaskType() + separator + doneStatus
                        + separator + task.getItem());

                if (task.setTaskType().equals("D")) {
                    fw.write(separator + task.getBy());
                } else if (task.setTaskType().equals("E")) {
                    fw.write(separator + task.getFrom() + "-" + task.getTo());
                }

                fw.write(System.lineSeparator());
            }

            fw.close();

        } catch (IOException e) {
            System.out.println("Oh no!! Something went wrong..");
        }

        System.out.println("Bye Bye. See ya soon!");

        printDivider();

    }
}
