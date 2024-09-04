import java.util.ArrayList;
import java.util.Scanner;

public class King {
    private static final String NAME = "King";
    protected static int tasksCount = 0;
    protected static ArrayList<Task> tasks = new ArrayList<>();
    private final static String LINE = "____________________________________________________________\n";

    private static void toGreet() {
        String logo =
                "| |/ /|_ _|| \\| | / _` |\n" +
                "| ' <  | | | .` || (_| |\n" +
                "|_|\\_\\|___||_|\\_| \\__,_|\n";

        System.out.println("\nHello from\n" + logo);

        System.out.println(" Hello! I'm " + NAME + "\n" +
                           " What can I do for you?\n" + LINE);

        toChat();
    }

    private static void toChat() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                toExit();
                return;
            } else if (userInput.equals("list")) {
                printList();
            } else if (userInput.startsWith("mark")) {
                tasks.get(parseTaskIndex(userInput)).markAsDone();
            } else if (userInput.startsWith("unmark")) {
                tasks.get(parseTaskIndex(userInput)).markAsUndone();
            } else if (userInput.startsWith("todo")) {
                addToDoTask(userInput);
            } else if (userInput.startsWith("deadline")) {
                addDeadlineTask(userInput);
            } else if (userInput.startsWith("event")) {
                addEventTask(userInput);
            } else {
                addToDoTask(userInput);
            }
        }
        toExit();
    }

    private static void toExit() {
        System.out.println(LINE + " Bye. Hope to see you again soon!\n" + LINE);
    }


    private static int parseTaskIndex(String text) {
        return Integer.parseInt(text.split(" ")[1]) - 1;
    }


    private static void printList() {
        System.out.println(LINE + "Here are the tasks in your list:");

        for (int i = 0; i < tasksCount; i++) {
            int indexNum = i + 1;
            System.out.println(indexNum + ". " + tasks.get(i));
        }

        System.out.println(LINE);
    }


    private static void addToDoTask(String text) {
        tasksCount += 1;
        Task t = new Todo(text);
        tasks.add(t);
        printAddedTaskDescription(t);
    }

    private static void addDeadlineTask(String text) {
        tasksCount += 1;
        String[] taskSpecifics = text.split(" ");
        int seperationIndex = 0;
        String taskContent = "";
        String taskEndTime = "";

        for (int i = 0; i < taskSpecifics.length; i++) {
            if (taskSpecifics[i].equals("/by")) {
                seperationIndex = i;
            }
        }
        for (int i = 1; i < seperationIndex; i++) {
            taskContent += taskSpecifics[i];
            if (i < seperationIndex - 1) {
                taskContent += " ";
            }
        }
        for (int i = seperationIndex + 1; i < taskSpecifics.length; i++) {
            taskEndTime += taskSpecifics[i];
            if (i < taskSpecifics.length - 1) {
                taskEndTime += " ";
            }
        }

        Task t = new Deadline(taskContent, taskEndTime);
        tasks.add(t);
        printAddedTaskDescription(t);
    }

    private static void addEventTask(String text) {
        tasksCount += 1;
        String[] taskSpecifics = text.split(" ");
        int seperationIndexFirst = 0;
        int seperationIndexSecond = 0;
        String taskContent = "";
        String taskStartTime = "";
        String taskEndTime = "";

        for (int i = 0; i < taskSpecifics.length; i++) {
            if (taskSpecifics[i].equals("/from")) {
                seperationIndexFirst = i;
            } else if (taskSpecifics[i].equals("/to")) {
                seperationIndexSecond = i;
            }
        }
        for (int i = 1; i < seperationIndexFirst; i++) {
            taskContent += taskSpecifics[i];
            if (i < seperationIndexFirst - 1) {
                taskContent += " ";
            }
        }
        for (int i = seperationIndexFirst + 1; i < seperationIndexSecond; i++) {
            taskStartTime += taskSpecifics[i];
            if (i < seperationIndexSecond - 1) {
                taskStartTime += " ";
            }
        }
        for (int i = seperationIndexSecond + 1; i < taskSpecifics.length; i++) {
            taskEndTime += taskSpecifics[i];
            if (i < taskSpecifics.length - 1) {
                taskEndTime += " ";
            }
        }

        Task t = new Event(taskContent, taskStartTime, taskEndTime);
        tasks.add(t);
        printAddedTaskDescription(t);
    }

    private static void printAddedTaskDescription(Task t) {
        System.out.println(LINE + "Got it. I've added this task:\n   "
                           + t.getTaskDescription() +
                           "\nNow you have " + tasksCount + " tasks in the list.\n" + LINE);
    }


    public static void main(String[] args) {
        toGreet();
    }
}
