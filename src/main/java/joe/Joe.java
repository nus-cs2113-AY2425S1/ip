package joe;

import java.util.Optional;
import java.util.Scanner;
import java.util.ArrayList;

public class Joe {
    private static String INTENDATION = "      ";
    private static String SEPARATOR = "_________________________________________________";
    private static ArrayList<Task> toDoItemArrayList = new ArrayList<Task>();
    private static final String LOGO = "    (_)           \n"
            + INTENDATION + "     _  ___   ___ \n"
            + INTENDATION + "    | |/ _ \\ / _ \\\n"
            + INTENDATION + "    | | (_) |  __/\n"
            + INTENDATION + "    | |\\___/ \\___|\n"
            + INTENDATION + "   _/ |           \n"
            + INTENDATION + "  |__/            \n";

    public static void main(String[] args) {
        printGreeting();
        chatWithJoe();
        printFarewell();
    }

    public static void chatWithJoe() {
        //get and echo user input
        Scanner in = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            input = in.nextLine();
            switch (input) {
            case "bye":
                break;
            case "list":
                printList();
                break;
            default:
                chatAboutTasks(input);
            }
        }
    }


    // helper methods

    public static void chatAboutTasks(String input) {
        String[] tokens = input.split(" ");
        String commandToken = tokens[0]; 
        switch (commandToken) {
        case "unmark":
            try {
                int toDoNumber = Integer.parseInt(tokens[1].strip());
                toDoItemArrayList.get(toDoNumber - 1).setToDo(false);
                printReply(Integer.toString(toDoNumber), "Unmarked: ");
            } catch (NumberFormatException e) {
                printReply("Invalid input. Second token is not a number.", "Retry: ");
            } catch (IndexOutOfBoundsException e) {
                printReply("No todo with this number exists.", "Retry: ");
            }
            break;
        case "mark":
            try {
                int toDoNumber = Integer.parseInt(tokens[1].strip());
                toDoItemArrayList.get(toDoNumber - 1).setToDo(true);
                printReply(Integer.toString(toDoNumber), "Marked: ");
            } catch (NumberFormatException e) {
                printReply("Invalid input. Second token is not a number.", "Retry: ");
            } catch (IndexOutOfBoundsException e) {
                printReply("No todo with this number exists.", "Retry: ");
            }
            break;
        case "delete":
            try {
                int toDoNumber = Integer.parseInt(tokens[1].strip());
                Task itemToDelete = toDoItemArrayList.get(toDoNumber -1);
                toDoItemArrayList.remove(toDoNumber - 1);
                printReply(itemToDelete.toString(), "Deleted:");
            } catch (NumberFormatException e) {
                printReply("Invalid input: Second token is not a number.", "Retry: ");
            } catch (IndexOutOfBoundsException e) {
                printReply("No todo with this number exists.", "Retry: ");
            }
            break;
        default:
            try {
                createNewTask(commandToken, input);
            } catch (EmptyTaskException e) {
                printReply("A task of type " + commandToken + " cannot be empty.", "Retry: ");

        }
        }
    }

    public static void createNewTask(String commandToken, String input) throws EmptyTaskException {
        String itemDescription;
        Optional<Task> newItem;
        switch (commandToken) {
        case "todo":
            itemDescription = Todo.extractDescription(input);
            if (itemDescription.length() > 0) {
                newItem = Optional.of(new Todo(itemDescription));
            } else {
                throw new EmptyTaskException();
            }
            break;
        case "deadline":
            itemDescription = Deadline.extractDescription(input);
            if (itemDescription.length() > 0) {
                String deadlineDate = Deadline.extractDeadlineDate(input).orElse("NA");
                newItem = Optional.of(new Deadline(itemDescription, deadlineDate));
            } else {
                throw new EmptyTaskException();
            }
            break;
        case "event":
            itemDescription = Event.extractDescription(input);
            if (itemDescription.length() > 0) {
                String endDate = Event.extractEndDate(input).orElse("NA");
                String startDate = Event.extractStartDate(input).orElse("NA");
                newItem = Optional.of(new Event(itemDescription, startDate, endDate));
            } else {
                throw new EmptyTaskException();
            }
            break;
        default:
            newItem = Optional.empty();
        }

        // Catch the case when no valid command was used via Optional
        newItem.ifPresentOrElse(
            task -> addToList(task),
                () -> printReply("I do not understand: " + input + ". \n" +
                        INTENDATION + "Please use: todo, event or deadline for creating tasks \n" +
                        INTENDATION + "and list, bye, unmark and mark otherwise. ", "Retry: ")
        );
    }

    public static void addToList(Task task) {
        toDoItemArrayList.add(task);
         printReply(task.getItemDescription(), "Added: ");
    }

    public static void  printList() {
        int counter = 0;
        for (Task task : toDoItemArrayList) {
            counter += 1;
            System.out.println(INTENDATION + counter
                    + ": " + task.toString());
        }
    }

    public static void printGreeting() {
        System.out.println(INTENDATION + LOGO);
        System.out.println(INTENDATION + SEPARATOR);
        System.out.println(INTENDATION + "Hello I'm joe.Joe.");
        System.out.println(INTENDATION + "How can I help you?");
        System.out.println(INTENDATION + SEPARATOR);
    }

    public static void printFarewell() {
        System.out.println(INTENDATION + "See you soon!");
        System.out.println(INTENDATION + SEPARATOR);
    }

    public static void printReply(String input, String actionPerformed) {
        System.out.println(INTENDATION + actionPerformed + input);
        System.out.println(INTENDATION + SEPARATOR);
    }


}