import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Cubone {
    static final String LOGO =   
            "   ______      __                       \n"+
            "  / ____/_  __/ /_  ____  ____  ___     \n"+
            " / /   / / / / __ \\/ __ \\/ __ \\/ _ \\\n"+
            "/ /___/ /_/ / /_/ / /_/ / / / /  __/    \n"+
            "\\____/\\__,_/_.___/\\____/_/ /_/\\___/ \n";
    static final String CHAT_PREFIX = "\n(Cubone) ";
    static final String CHAT_BAR = "---------------------------------";

    // list to store user input
    static ArrayList<Task> inputed_tasks = new ArrayList<Task>();

    public static void sayWelcomeMsg() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm Cubone\nWhat can I do for you?\n" + CHAT_BAR);
    }
    
    public static void sayBye() {
        System.out.println(CHAT_BAR + CHAT_PREFIX + "Bye. Hope to see you again soon!\n" + CHAT_BAR);
    }

    public static void listTasks() {
        System.out.println(CHAT_BAR + CHAT_PREFIX + "Here are the tasks in your list:\n" + CHAT_BAR);
        for (int i = 0; i < inputed_tasks.size(); i++) {
            System.out.println((i + 1) + ". " + inputed_tasks.get(i).toString());
        }
        System.out.println(CHAT_BAR);
    } 

    public static void markTaskAsDone(int index) {
        inputed_tasks.get(index).markAsDone();
        System.out.println(CHAT_BAR + CHAT_PREFIX + "Nice! I've marked this task as done:\n" + inputed_tasks.get(index).toString() + "\n" + CHAT_BAR);
    }

    public static void markTaskAsUndone(int index) {
        inputed_tasks.get(index).markAsUndone();
        System.out.println(CHAT_BAR + CHAT_PREFIX + "Nice! I've marked this task as undone:\n" + inputed_tasks.get(index).toString() + "\n" + CHAT_BAR);
    }

    public static void addTodoTask(String description) {
        inputed_tasks.add(new Todo(description));
        System.out.println(CHAT_BAR + CHAT_PREFIX + "Got it. I've added this Todo:\n" + inputed_tasks.get(inputed_tasks.size() - 1).toString() + "\n" +
                "now you have " + inputed_tasks.size() + " tasks in the list\n" + CHAT_BAR);
    }

    public static void addDeadlineTask(String description, String by) {
        inputed_tasks.add(new Deadline(description, by));
        System.out.println(CHAT_BAR + CHAT_PREFIX + "Got it. I've added this Deadline:\n" + inputed_tasks.get(inputed_tasks.size() - 1).toString() + "\n" +
                "now you have " + inputed_tasks.size() + " tasks in the list\n" + CHAT_BAR);
    }

    public static void addEventTask(String description, String from, String to) {
        inputed_tasks.add(new Event(description, from, to));
        System.out.println(CHAT_BAR + CHAT_PREFIX + "Got it. I've added this Event:\n" + inputed_tasks.get(inputed_tasks.size() - 1).toString() + "\n" +
                "now you have " + inputed_tasks.size() + " tasks in the list\n" + CHAT_BAR);
    }

    public static void addTask(String description) {
        inputed_tasks.add(new Task(description));
        System.out.println(CHAT_BAR + CHAT_PREFIX + "Got it. I've added this task:\n" + inputed_tasks.get(inputed_tasks.size() - 1).toString() + "\n" +
                "now you have " + inputed_tasks.size() + " tasks in the list\n" + CHAT_BAR);
    }

    public static void main(String[] args) {
        sayWelcomeMsg();

        // loop for user input
        boolean isWorking = true;
        Scanner sc = new Scanner(System.in);
        while (isWorking) {
            // String input = sc.nextLine();
            String fullCommand = sc.nextLine();
            String[] command = fullCommand.split(" ", 2);
            switch (command[0]) {
                case "bye":
                    // exit loop
                    sayBye();
                    isWorking = false;
                    break;
                case "list":
                    // list all tasks
                    listTasks();
                    break;
                case "mark":
                    // mark task as done
                    int markIndex = Integer.parseInt(command[1]);
                    markTaskAsDone(markIndex - 1);
                    break;
                case "unmark":
                    // mark task as undone
                    int unmarkIndex = Integer.parseInt(command[1]);
                    markTaskAsUndone(unmarkIndex - 1);
                    break;
                case "todo":
                    // add todo task
                    if (command[1].length() == 1) {
                        System.out.println(CHAT_BAR + CHAT_PREFIX + "☹ OOPS!!! The description of a todo cannot be empty.\n" + CHAT_BAR);
                    } else {
                        addTodoTask(command[1]);
                    }
                    break;
                case "deadline":
                    // add deadline task
                    // usage: deadline <description> /by <date>
                    Pattern deadlinePattern = Pattern.compile("(.+?) /by (.+)");
                    Matcher deadlineMatcher = deadlinePattern.matcher(command[1]);
                    if (deadlineMatcher.find()) {
                        addDeadlineTask(deadlineMatcher.group(1), deadlineMatcher.group(2));
                    } else {
                        System.out.println(CHAT_BAR + CHAT_PREFIX + "☹ OOPS!!! Someting missing, usage: deadline <description> /by <date>\n" + CHAT_BAR);
                    }
                    break;
                case "event":
                    // add event task
                    // usage: event <description> /from <date> /to <date>
                    Pattern eventPattern = Pattern.compile("(.+?) /from (.+?) /to (.+)");
                    Matcher eventMatcher = eventPattern.matcher(command[1]);
                    if (eventMatcher.find()) {
                        addEventTask(eventMatcher.group(1), eventMatcher.group(2), eventMatcher.group(3));
                    } else {
                        System.out.println(CHAT_BAR + CHAT_PREFIX + "☹ OOPS!!! Someting missing, usage: event <description> /from <date> /to <date>\n" + CHAT_BAR);
                    }
                    break;
                default:
                    // add user input into list
                    addTask(command[0]);
                    break;
            }
        }
    }
}
