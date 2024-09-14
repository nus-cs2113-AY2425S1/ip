import java.util.Scanner;
import java.util.Arrays;

public class Quag {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.println("_______________________________________");
        System.out.println("Hello! I'm QUAG!");
        System.out.println("What can I do for you today? quag");
        System.out.println("_______________________________________");

        while (true) {
            userInput = scanner.nextLine();
            String command = getCommand(userInput);
            switch (command) {
                case "quag":
                    exitProgram();
                    return;
                case "list":
                    displayList();
                    break;
                case "mark":
                    markTask(userInput);
                    break;
                case "unmark":
                    unmarkTask(userInput);
                    break;
                case "todo":
                    addTodo(userInput);
                    break;
                case "deadline":
                    addDeadline(userInput);
                    break;
                case "event":
                    addEvent(userInput);
                    break;
                case "delete":
                    deleteTask(userInput);
                    break;
                default:
                    displayCommandList(userInput);
                    break;
            }
        }
    }

    private static String getCommand(String userInput) {
        //find first space
        int firstSpaceIndex = userInput.indexOf(" ");
        // first word is the command
        if (firstSpaceIndex != -1) {
            return userInput.substring(0, firstSpaceIndex).toLowerCase();
        }
        // entire userInput is the command
        else {
            return userInput.toLowerCase();
        }
    }


    private static void exitProgram() {
        System.out.println("_______________________________________");
        System.out.println("See you soon! quag quag");
        System.out.println("_______________________________________");
    }

    private static void displayList() {
        ListUtils.displayList();
    }


    private static void markTask(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length >= 2) {
            try {
                int taskIndex = Integer.parseInt(parts[1]);
                ListUtils.markAsDone(taskIndex);
            } catch (NumberFormatException e) {
                System.out.println("That's not a quaggin number!!");
            }
        } else {
            System.out.println("There's no quaggin number!!");
        }
    }
    private static void unmarkTask(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length >= 2) {
            try {
                int taskIndex = Integer.parseInt(parts[1]);
                ListUtils.markAsNotDone(taskIndex);
            } catch (NumberFormatException e) {
                System.out.println("That's not a quaggin number!!");
            }
        } else {
            System.out.println("There's no quaggin number!!");
        }
    }

    private static void addTodo(String userInput) {
        //description is from parts[1] to end
        String description = userInput.substring("todo".length()).trim();
        ListUtils.addTodoToList(description);
    }

    private static void addDeadline(String userInput) {
        //description is from parts[1] until / is detected
        //deadline is after /

        // int to denote length and position of "/by" separator
        int byLength = 4;
        int byIndex = userInput.indexOf("/by");

        //find position of /by separator
        if (byIndex != -1) {
            //extract the description and deadline
            String description = userInput.substring("deadline".length(), byIndex).trim();
            // Skip over "/by "
            String by = userInput.substring(byIndex + byLength).trim();
            ListUtils.addDeadlineToList(description, by);
        } else {
            System.out.println("That's not a valid quaggin format for a deadline!!");
        }

    }

    private static void addEvent(String userInput) {
        //description is from parts[1] until first /
        //from is in between first and second /
        //to is after second /

        // int to denote length and position of "/from" and "/to" separator
        int fromIndex = userInput.indexOf("/from");
        int fromLength = 6;
        int toIndex = userInput.indexOf("/to");
        int toLength = 4;

        if (fromIndex != -1 && toIndex != -1 && toIndex > fromIndex) {
            // extract description, from and to
            String description = userInput.substring("event".length(), fromIndex).trim();
            String from = userInput.substring(fromIndex + fromLength, toIndex).trim();
            String to = userInput.substring(toIndex + toLength).trim();
            ListUtils.addEventToList(description, from, to);
        }  else {
            System.out.println("That's not a valid quaggin format for an event!!");
        }

    }

    private static void deleteTask(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length >= 2) {
            try {
                int taskIndex = Integer.parseInt(parts[1]);
                ListUtils.deleteTask(taskIndex);
            } catch (NumberFormatException e) {
                System.out.println("That's not a quaggin number!!");
            }
        } else {
            System.out.println("There's no quaggin number!!");
        }
    }

    private static void displayCommandList(String userInput) {
        //displays all the commands available and their descriptions
        System.out.println(userInput + " is not a valid command!");
        System.out.println("list of all quaggin commands:");
        System.out.println("______________________________________________________________________________");
        System.out.println("list: lists out all your tasks");
        System.out.println("mark <index>: marks task corresponding to index");
        System.out.println("unmark <index>: unmarks task corresponding to index");
        System.out.println("todo <description>: adds task type todo to list");
        System.out.println("deadline <description> /by <date>: adds task type deadline to list");
        System.out.println("event <description> /from <date> /to <date>: adds task type event to list");
        System.out.println("delete <index>: delete task corresponding to index");
        System.out.println("quag: exit program");
        System.out.println("______________________________________________________________________________");

    }
}