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

    public static void printGreeting() {
        System.out.println(INTENDATION + LOGO);
        System.out.println(INTENDATION + SEPARATOR);
        System.out.println(INTENDATION + "Hello I'm Joe.");
        System.out.println(INTENDATION + "How can I help you?");
        System.out.println(INTENDATION + SEPARATOR);
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

    public static void printFarewell() {
        System.out.println(INTENDATION + "See you soon!");
        System.out.println(INTENDATION + SEPARATOR);
    }


    // helper methods

    public static void chatAboutTasks(String input) {
        String[] tokens = input.split(" ");
        switch (tokens[0]) {
        case "unmark":
            try {
                int toDoNumber = Integer.parseInt(tokens[1].strip());
                toDoItemArrayList.get(toDoNumber - 1).setToDo(false);
                printReply(Integer.toString(toDoNumber), "Unmarked: ");
            } catch (Exception e) {
                printReply("Select a valid list number to unmark.", "Retry: ");
            }
            break;
        case "mark":
            try {
                int toDoNumber = Integer.parseInt(tokens[1].strip());
                toDoItemArrayList.get(toDoNumber - 1).setToDo(true);
                printReply(Integer.toString(toDoNumber), "Marked: ");
            } catch (Exception e) {
                printReply("Select a valid list number to unmark.", "Retry: ");
            }
            break;
        default:
            createNewTask(tokens[0], input);
        }
    }

    public static void createNewTask(String startToken, String input) {
        String itemDescription;
        Optional<Task> newItem;
        switch (startToken) {
        case "todo":
            itemDescription = Todo.extractDescription(input);
            newItem = Optional.of(new Todo(itemDescription));
            break;
        case "deadline":
            itemDescription = Deadline.extractDescription(input);
            String deadlineDate = Deadline.extractDeadlineDate(input).orElse("NA");
            newItem = Optional.of(new Deadline(itemDescription, deadlineDate));
            break;
        case "event":
            itemDescription = Event.extractDescription(input);
            String endDate = Event.extractEndDate(input).orElse("NA");
            String startDate = Event.extractStartDate(input).orElse("NA");
            newItem = Optional.of(new Event(itemDescription, startDate, endDate));
            break;
        default:
            newItem = Optional.empty();
        }

        newItem.ifPresentOrElse(
            task -> addToList(task),
                () -> printReply("Invalid creation command", "Retry:")
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

    public static void printReply(String input, String actionPerformed) {
        System.out.println(INTENDATION + actionPerformed + input);
        System.out.println(INTENDATION + SEPARATOR);
    }

}