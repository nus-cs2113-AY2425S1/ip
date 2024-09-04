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
        System.out.println(logo + "\tHello! I'm Ran.");
        System.out.println("\tWhat can I do for you?");
        System.out.println(LINE);
    }

    public static void bidFarewell() {
        System.out.println(LINE);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void processTask(String input, TaskType type) {
        String description = input;
        switch (type) {
        case TODO:
            description = input.substring(5);
            list[listCount] = new Todo(description);
            break;
        case DEADLINE:
            int byIndex = input.indexOf("/by");
            description = input.substring(9, byIndex - 1);
            String by = input.substring(byIndex + 4);
            list[listCount] = new Deadline(description, by);
            break;
        case EVENT:
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            description = input.substring(6, fromIndex - 1);
            String from = input.substring(fromIndex + 6, toIndex - 1);
            String to = input.substring(toIndex + 4);
            list[listCount] = new Event(description, from, to);
            break;
        case UNDEFINED:
        default:
            list[listCount] = new Task(input);
        }
        System.out.println(LINE);
        System.out.println("\tUnderstood, I have noted down the following task:");
        System.out.println("\t " +  list[listCount]);
        listCount++;
        System.out.println("\tYou currently have " + listCount + 
                (listCount == 1 ? " task" : " tasks") + " in your list.");
        System.out.println(LINE);
    }

    public static void showList() {
        System.out.println(LINE);
        for (int i = 0; i < listCount; i++) {
            System.out.println("\t" + (i + 1) + "." + list[i]);
        }
        System.out.println(LINE);
    }

    public static void markTask(String taskNum) {
        int taskNumber = Integer.parseInt(taskNum) - 1;
        list[taskNumber].setAsDone();
        System.out.println(LINE);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + list[taskNumber]);
        System.out.println(LINE);
    }

    public static void unmarkTask(String taskNum) {
        int taskNumber = Integer.parseInt(taskNum) - 1;
        list[taskNumber].setAsUndone();
        System.out.println(LINE);
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + list[taskNumber]);
        System.out.println(LINE);
    }

    public static void processInput(String input) {
        String[] instruction = input.split(" ");
        if (input.equals("bye")) {
            isTerminated = true;
        } else if (input.equals("list")) {
            showList();
        } else if (instruction[0].equals("todo")) {
            processTask(input, TaskType.TODO);
        } else if (instruction[0].equals("deadline")) {
            processTask(input, TaskType.DEADLINE);
        } else if (instruction[0].equals("event")) {
            processTask(input, TaskType.EVENT);
        } else if (instruction.length > 1 && instruction[0].equals("mark")) {
            markTask(instruction[1]);
        } else if (instruction.length > 1 && instruction[0].equals("unmark")) {
            unmarkTask(instruction[1]);
        } else {
            processTask(input, TaskType.UNDEFINED);
        }	
    }

    public static void main(String[] args) {
        greet();

        String input;
        Scanner in = new Scanner(System.in);

        while(!isTerminated) {
            input = in.nextLine();
            processInput(input);
        }

        bidFarewell();
    }
}
