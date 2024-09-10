import java.util.Scanner;

public class Ran {
    private static boolean isTerminated = false; 
    private static int listCount = 0;
    private static final int MAX_TASK_LIST_SIZE = 100;
    private static Task[] list = new Task[MAX_TASK_LIST_SIZE];
    private static final String LINE = "\t____________________________________________________________";

    public static void greet() {
        System.out.println(LINE);
        String logo = "\t     ___           ___           ___\n"
                + "\t    /\\  \\         /\\  \\         /\\__\\\n"
                + "\t   /::\\  \\       /::\\  \\       /::|  |\n"
                + "\t  /:/\\:\\  \\     /:/\\:\\  \\     /:|:|  |\n"
                + "\t /::\\~\\:\\  \\   /::\\~\\:\\  \\   /:/|:|  |__\n"
                + "\t/:/\\:\\ \\:\\__\\ /:/\\:\\ \\:\\__\\ /:/ |:| /\\__\\\n"
                + "\t\\/_|::\\/:/  / \\/__\\:\\/:/  / \\/__|:|/:/  /\n"
                + "\t   |:|::/  /       \\::/  /      |:/:/  /\n"
                + "\t   |:|\\/__/        /:/  /       |::/  /\n"
                + "\t   |:|  |         /:/  /        /:/  /\n"
                + "\t    \\|__|         \\/__/         \\/__/\n";
        System.out.println(logo + "\tHello, I'm Ran.");
        System.out.println("\tHow may I assist you?");
        System.out.println(LINE);
    }

    public static void bidFarewell() {
        System.out.println(LINE);
        System.out.println("\tFarewell. May we meet again!");
        System.out.println(LINE);
    }

    public static void printAddedTask() { 
        System.out.println(LINE);
        System.out.println("\tUnderstood, I have noted down the following task:");
        System.out.println("\t " +  list[listCount - 1]);
        // Conditional operator to pluralize "task" when listCount above 1
        System.out.println("\tYou currently have " + listCount + 
                (listCount <= 1 ? " task" : " tasks") + " in your list.");
        System.out.println(LINE);
    }

    // Process task based on its type into relevant fields to be added
    public static void processTask(String input, TaskType type) throws MissingArgumentException {
        String description = input;
        switch (type) {
        case TODO:
            // Take string after "todo" word
            description = input.substring(5);
            list[listCount] = new Todo(description);
            break;
        case DEADLINE:
            int byIndex = input.indexOf("/by");
            if (byIndex == -1) {
                throw new MissingArgumentException(CommandType.DEADLINE);
            }
            // Take string between "deadline" and "/by"
            description = input.substring(9, byIndex - 1);
            // Take string after "/by"
            String by = input.substring(byIndex + 4);
            list[listCount] = new Deadline(description, by);
            break;
        case EVENT:
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            if (fromIndex == -1 || toIndex == -1) {
                throw new MissingArgumentException(CommandType.EVENT);
            }
            // Take string between "event" and "/from"
            description = input.substring(6, fromIndex - 1);
            // Take string between "/from" and "/to"
            String from = input.substring(fromIndex + 6, toIndex - 1);
            // Take string after "/to"
            String to = input.substring(toIndex + 4);
            list[listCount] = new Event(description, from, to);
            break;
        case UNDEFINED:
        default:
            list[listCount] = new Task(input);
        }
        listCount++;
        printAddedTask();
    }

    public static void showList() throws EmptyListException {
        if (listCount == 0) {
            throw new EmptyListException();
        }
        System.out.println(LINE);
        for (int i = 0; i < listCount; i++) {
            System.out.println("\t" + (i + 1) + "." + list[i]);
        }
        System.out.println(LINE);
    }

    public static void markTask(String taskNum) throws OutOfListBoundsException {
        int taskNumber = Integer.parseInt(taskNum) - 1;
        if (taskNumber >= listCount || taskNumber < 0) {
            throw new OutOfListBoundsException();
        }
        list[taskNumber].setAsDone();
        System.out.println(LINE);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + list[taskNumber]);
        System.out.println(LINE);
    }

    public static void unmarkTask(String taskNum) throws OutOfListBoundsException {
        int taskNumber = Integer.parseInt(taskNum) - 1;
        if (taskNumber >= listCount || taskNumber < 0) {
            throw new OutOfListBoundsException();
        }
        list[taskNumber].setAsUndone();
        System.out.println(LINE);
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + list[taskNumber]);
        System.out.println(LINE);
    }
    
    // Read user input for command, throw exception for invalid commands
    public static void executeCommand(String input, String[] instruction) 
            throws MissingCommandException, MissingDescriptionException, EmptyListException,
            OutOfListBoundsException, MissingArgumentException {
        if (input.equals("bye")) {
            isTerminated = true;
        } else if (input.equals("list")) {
            showList();
        } else if (instruction[0].equals("todo")) {
            if (instruction.length > 1) {
                processTask(input, TaskType.TODO);
            } else {
                throw new MissingDescriptionException(TaskType.TODO);
            }
        } else if (instruction[0].equals("deadline")) {
            if (instruction.length > 1) {
                processTask(input, TaskType.DEADLINE);
            } else {
                throw new MissingDescriptionException(TaskType.DEADLINE);
            }
        } else if (instruction[0].equals("event")) {
            if (instruction.length > 1) {
                processTask(input, TaskType.EVENT);
            } else {
                throw new MissingDescriptionException(TaskType.EVENT);
            }
        } else if (instruction[0].equals("mark")) {
            try {
                markTask(instruction[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingArgumentException(CommandType.MARK);
            }
        } else if (instruction[0].equals("unmark")) {
            try {
                unmarkTask(instruction[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingArgumentException(CommandType.UNMARK);
            }
        } else {
            throw new MissingCommandException();
        }	
    }

    // Method chooses appropriate response for user input based on set pattern
    public static void processInput(String input) {
        String[] instruction = input.split(" ");
        try {
            executeCommand(input, instruction);
        } catch (MissingCommandException e) {
            System.out.println(LINE);
            System.out.println("\tHmmmm, it appears that you didn't give an appropriate command.");
            System.out.println("\tHere's some you can consider:");
            System.out.println("\ttodo, deadline, event, mark, unmark, bye");
            System.out.println(LINE);
        } catch (MissingDescriptionException e) {
            System.out.println(LINE);
            System.out.println("\tPlease provide a description for your " 
                    + e.getTypeString() + " command.");
            System.out.println(LINE);
        } catch (EmptyListException e) {
            System.out.println(LINE);
            System.out.println("\tAh, it seems your list is empty. Why not give it some substance?");
            System.out.println(LINE);
        } catch (OutOfListBoundsException e) {
            System.out.println(LINE);
            System.out.println("\tWoah, that index is out of the bounds of your list.");
            System.out.println("\tAccessing it would have torn a gap in this computer simulation,");
            System.out.println("\tcausing a premature termination, resulting in an incident.");
            System.out.println("\tThat, I cannot allow.");
            System.out.println(LINE);
        } catch (NumberFormatException e) {
            System.out.println(LINE);
            System.out.println("\tPlease input your index as a valid integer.");
            System.out.println(LINE);
        } catch (MissingArgumentException e) {
            System.out.println(LINE);
            System.out.println("\tThere appears to be something wrong with command's arguments.");
            System.out.println(LINE);
        }
    }

    public static void main(String[] args) {
        greet();
        
        // Take in user input from the terminal
        String input;
        Scanner in = new Scanner(System.in);
        
        // Process user input line by line until terminating command is given
        while(!isTerminated) {
            input = in.nextLine();
            processInput(input);
        }

        bidFarewell();
    }
}
