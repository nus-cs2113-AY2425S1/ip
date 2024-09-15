import java.util.ArrayList;
import java.util.Scanner;
import freedom.exceptions.InvalidCommand;
import freedom.tasks.Task;
import freedom.tasks.ToDo;
import freedom.tasks.Deadline;
import freedom.tasks.Event;

public class Freedom {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    static int lastIndex = 0;

    static final String LOGO = "\t________________________________________\n";

    public static void main(String[] args) {
        final String START_MESSAGE = """
                \tHello! I'm Freedom
                \tWhat can I do for you?
                """;
        final String CLOSING_MESSAGE = "\tBye. Hope to see you again soon!\n";

        System.out.println(LOGO + START_MESSAGE + LOGO);
        Scanner in = new Scanner(System.in);

        // Super loop for getting inputs
        while (in.hasNextLine()) {
            String line = in.nextLine();

            if (line.equals("bye")) {
                break;
            }
            handleInput(line);
        }

        System.out.println(LOGO + CLOSING_MESSAGE + LOGO);
    }

    public static void handleInput(String input) {
        final int COMMAND_INDEX = 0;
        String[] words = input.split(" ");
        String description;

        try {
            switch (words[COMMAND_INDEX]) {
                case "list":
                    printList();
                    return;
                case "mark":
                case "unmark":
                case "delete":
                    editTask(words);
                    return;
                case "todo":
                    description = input.replaceFirst("todo", "");
                    tasks.add(new ToDo(description));
                    break;
                case "deadline":
                    description = input.replaceFirst("deadline", "");
                    tasks.add(new Deadline(description));
                    break;
                case "event":
                    description = input.replaceFirst("event", "");
                    tasks.add(new Event(description));
                    break;
                default:
                    throw new InvalidCommand();

            }
            System.out.println(LOGO + "\tGot it. I've added this task: ");
            System.out.println("\t  " + tasks.get(lastIndex).printLine());
            
            lastIndex++;

            System.out.println("\tNow you have " + (lastIndex) + " tasks in the list.\n" + LOGO);
        } catch (InvalidCommand e) {
            System.out.println(LOGO + "\tSorry! I do not understand your command");
            System.out.println(LOGO);
        } catch (Exception e) {
            System.out.print("");
        }
    }

    public static void printList() {
        int counter = 1;
        System.out.print(LOGO + "\tHere are the tasks in your list:\n");
        for (int i = 0; i < lastIndex; i++) {
            System.out.println("\t" + counter + "." + tasks.get(i).printLine());
            counter++;
        }
        System.out.println(LOGO);
    }

    public static void editTask(String[] words) {
        final int COMMAND_INDEX = 0;
        final int TASK_INDEX = 1;
        int listNumber = Integer.parseInt(words[TASK_INDEX]);
        int taskIndexInStorage = listNumber - 1;
        String message;
        Task taskToBeMarked;

        try {
            if (taskIndexInStorage >= lastIndex) {
                throw new ArrayIndexOutOfBoundsException();
            }
            taskToBeMarked = tasks.get(taskIndexInStorage);
            switch (words[COMMAND_INDEX]) {
                case "mark":
                    taskToBeMarked.markDone();
                    tasks.set(taskIndexInStorage, taskToBeMarked);
                    message = "\tNice! I've marked this task as done:";
                    break;
                case "unmark":
                    taskToBeMarked.markUndone();
                    tasks.set(taskIndexInStorage, taskToBeMarked);
                    message = "\tOk, I've marked this task as not done yet:";
                    break;
                case "delete":
                    tasks.remove(taskIndexInStorage);
                    message = "\tOk! This task has been deleted:";
                    lastIndex--;
                    break;
                default:
                    throw new Exception();
            }
            System.out.println(LOGO + message);
            System.out.println("\t  " + taskToBeMarked.printLine());
            System.out.println(LOGO);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print(LOGO);
            System.out.print("""
                    \tWe don't have that many tasks??
                    \tYou can use list to check
                    """);
            System.out.println(LOGO);
        } catch (Exception e) {
            System.out.print("");
        }
    }
}
