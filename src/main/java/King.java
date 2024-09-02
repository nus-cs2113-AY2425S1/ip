import java.util.ArrayList;
import java.util.Scanner;

public class King {
    public static final String name = "King";
    public static int repeatCount = 0;
    public static int tasksCount = 0;
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void greet() {
        String logo =
                """
                        | |/ /|_ _|| \\| | / _` |
                        | ' <  | | | .` || (_| |
                        |_|\\_\\|___||_|\\_| \\__,_|
                        """;

        System.out.println("Hello from\n" + logo);

        System.out.println(" Hello! I'm " + name + "\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        chat();
    }

    public static void chat() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.equals("bye")) {
            exit();
        } else if (userInput.equals("list")) {
            printList();
            chat();
        } else if (userInput.startsWith("mark")) {
            tasks.get(parseTaskIndex(userInput)).markAsDone();
            chat();
        } else if (userInput.startsWith("unmark")) {
            tasks.get(parseTaskIndex(userInput)).markAsUndone();
            chat();
        } else if (userInput.startsWith("todo")) {
            addToDoTask(userInput);
            chat();
        } else if (userInput.startsWith("deadline")) {
            addDeadlineTask(userInput);
            chat();
        } else if (userInput.startsWith("event")) {
            addEventTask(userInput);
            chat();
        } else {
            addToDoTask(userInput);
            chat();
        }

    }

    public static void exit() {
        System.out.println("____________________________________________________________\n");

        if (repeatCount >= 5) {
            System.out.println(" I am repeating no more!!!\n");
        } else {
            System.out.println(" Bye. Hope to see you again soon!\n");
        }

        System.out.println("____________________________________________________________");
    }


    public static int parseTaskIndex(String text) {
        return Integer.parseInt(text.split(" ")[1]) - 1;
    }


    public static void printList() {
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list:");

        for (int i = 0; i < tasksCount; i++) {
            int indexNum = i + 1;
            System.out.println(indexNum + ". " + tasks.get(i));
        }

        System.out.println("____________________________________________________________");
    }


    public static void addToDoTask(String text) {
        tasksCount += 1;
        Task t = new Todo(text);
        tasks.add(t);
        printAddedTaskDescription(t);
    }

    public static void addDeadlineTask(String text) {
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

    public static void addEventTask(String text) {
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

    public static void printAddedTaskDescription(Task t) {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n   "
                + t.getTaskDescription() +
                "\nNow you have " + tasksCount + " tasks in the list.");

        System.out.println("____________________________________________________________\n");
    }


    public static void main(String[] args) {
        greet();
    }
}
