import java.util.Scanner;
import java.util.ArrayList;

public class Joe {
    private static String INTENDATION = "      ";
    private static String SEPARATOR = "_________________________________________________";
    private static String[] USER_COMMANDS = {"bye", "list"};
    private static ArrayList<ToDoItem> toDoItemArrayList = new ArrayList<ToDoItem>();


    public static void main(String[] args) {
        String logo = INTENDATION + "     _            \n"
            + INTENDATION + "    (_)           \n"
            + INTENDATION + "     _  ___   ___ \n"
            + INTENDATION + "    | |/ _ \\ / _ \\\n"
            + INTENDATION + "    | | (_) |  __/\n"
            + INTENDATION + "    | |\\___/ \\___|\n"
            + INTENDATION + "   _/ |           \n"
            + INTENDATION + "  |__/            \n";
        System.out.println(INTENDATION + "Hello from\n" + logo);
        printGreeting();
        chatWithJoe();
        printFarewell();
    }

    public static void printReply(String input, String actionPerformed) {
        System.out.println(INTENDATION + actionPerformed + input);
        System.out.println(INTENDATION + SEPARATOR);
    }

    public static void printGreeting() {
        System.out.println(INTENDATION + SEPARATOR);
        System.out.println(INTENDATION + "Hello I'm Joe.");
        System.out.println(INTENDATION + "How can I help you?");
        System.out.println(INTENDATION + SEPARATOR);
    }

    public static void printFarewell() {
        System.out.println(INTENDATION + "See you soon!");
        System.out.println(INTENDATION + SEPARATOR);
    }

    public static void addToList(String input) {
        ToDoItem item = new ToDoItem(input);
        toDoItemArrayList.add(item);
    }

    public static void  printList() {
        int counter = 0;
        for (ToDoItem input : toDoItemArrayList) {
            counter +=1;
            String checkBox;
            if (input.isToDo) {
                checkBox = " [" + "not done" + "]";
            } else {
                checkBox = " [" + "done" + "]";
            }
            System.out.println(INTENDATION + counter  + checkBox
                    + ": " + input.itemDescription);
        }
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
                if (input.contains("unmark")) {
                    String[] tokens = input.split(" ");
                    try{
                        int toDoNumber = Integer.parseInt(tokens[1].strip());
                        toDoItemArrayList.get(toDoNumber-1).isToDo = false;
                        printReply(Integer.toString(toDoNumber),"Unmarked: ");
                    } catch(Exception e) {
                        printReply("Select a valid list number to unmark.", "Retry: ");
                    }
                } else if (input.contains("mark")) {
                    try {
                        String[] tokens = input.split(" ");
                        int toDoNumber = Integer.parseInt(tokens[1].strip());
                        toDoItemArrayList.get(toDoNumber - 1).isToDo = true;
                        printReply(Integer.toString(toDoNumber), "Marked: ");
                    } catch (Exception e) {
                        printReply("Select a valid list number to unmark.", "Retry: ");
                    }
                } else {
                    addToList(input);
                    printReply(input, "Added: ");
                }
            }
        }
    }
}
