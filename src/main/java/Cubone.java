import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Cubone {
    public static void main(String[] args) {
        final String LOGO =   
            "   ______      __                       \n"+
            "  / ____/_  __/ /_  ____  ____  ___     \n"+
            " / /   / / / / __ \\/ __ \\/ __ \\/ _ \\\n"+
            "/ /___/ /_/ / /_/ / /_/ / / / /  __/    \n"+
            "\\____/\\__,_/_.___/\\____/_/ /_/\\___/ \n";
        final String CHAT_PREFIX = "\n(Cubone) ";
        final String CHAT_BAR = "---------------------------------";
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm Cubone\nWhat can I do for you?\n" + CHAT_BAR);

        // list to store user input
        ArrayList<Task> inputed_tasks = new ArrayList<Task>();

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
                    System.out.println(CHAT_BAR + CHAT_PREFIX + "Bye. Hope to see you again soon!\n" + CHAT_BAR);
                    isWorking = false;
                    break;
                case "list":
                    // print all user inputs
                    System.out.println(CHAT_BAR + CHAT_PREFIX + "Here are the tasks in your list:");
                    for (int i = 0; i < inputed_tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + inputed_tasks.get(i).toString());
                    }
                    System.out.println(CHAT_BAR);
                    break;
                case "mark":
                    // mark task as done
                    int markIndex = Integer.parseInt(command[1]);
                    inputed_tasks.get(markIndex - 1).markAsDone();
                    System.out.println(CHAT_BAR + CHAT_PREFIX + "Nice! I've marked this task as done:\n" + inputed_tasks.get(markIndex - 1).toString() + "\n" + CHAT_BAR);
                    break;
                case "unmark":
                    // mark task as undone
                    int unmarkIndex = Integer.parseInt(command[1]);
                    inputed_tasks.get(unmarkIndex - 1).markAsUndone();
                    System.out.println(CHAT_BAR + CHAT_PREFIX + "OK, I've marked this task as not done yet:\n" + inputed_tasks.get(unmarkIndex - 1).toString() + "\n" + CHAT_BAR);
                    break;
                case "todo":
                    // add todo task
                    if (command[1].length() == 1) {
                        System.out.println(CHAT_BAR + CHAT_PREFIX + "☹ OOPS!!! The description of a todo cannot be empty.\n" + CHAT_BAR);
                    } else {
                        inputed_tasks.add(new Todo(command[1]));
                        System.out.println(CHAT_BAR + CHAT_PREFIX + "Got it. I've added this Todo:\n" + inputed_tasks.get(inputed_tasks.size() - 1).toString() + "\n" +
                                "now you have " + inputed_tasks.size() + " tasks in the list\n" + CHAT_BAR);
                    }
                    break;
                case "deadline":
                    // add deadline task
                    // usage: deadline <description> /by <date>
                    Pattern deadlinePattern = Pattern.compile("(.+?) /by (.+)");
                    Matcher deadlineMatcher = deadlinePattern.matcher(command[1]);
                    if (deadlineMatcher.find()) {
                        inputed_tasks.add(new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2)));
                        System.out.println(CHAT_BAR + CHAT_PREFIX + "Got it. I've added this task:\n" + inputed_tasks.get(inputed_tasks.size() - 1).toString() + "\n" +
                                "now you have " + inputed_tasks.size() + " tasks in the list\n" + CHAT_BAR);
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
                        inputed_tasks.add(new Event(eventMatcher.group(1), eventMatcher.group(2), eventMatcher.group(3)));
                        System.out.println(CHAT_BAR + CHAT_PREFIX + "Got it. I've added this task:\n" + inputed_tasks.get(inputed_tasks.size() - 1).toString() + "\n" +
                                "now you have " + inputed_tasks.size() + " tasks in the list\n" + CHAT_BAR);
                    } else {
                        System.out.println(CHAT_BAR + CHAT_PREFIX + "☹ OOPS!!! Someting missing, usage: event <description> /from <date> /to <date>\n" + CHAT_BAR);
                    }
                    break;
                default:
                    // add user input into list
                    inputed_tasks.add(new Task(command[0]));
                    System.out.println(CHAT_BAR + CHAT_PREFIX + "added: " + command[0] + "\n" + CHAT_BAR);
                    break;
            }
        }
    }
}
