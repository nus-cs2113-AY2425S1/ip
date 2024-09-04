import java.util.Scanner;

public class Ran {
    private static boolean isTerminated = false;
    private static int listCount = 0;
    private static Task[] list = new Task[100];
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

    public static void addTask(String input) {
        list[listCount] = new Task(input);
        System.out.println(LINE);
        System.out.println("\tUnderstood, I have noted down the following task:");
        System.out.println("\t " +  list[listCount]);
        listCount++;
        System.out.println("\tYou currently have " + listCount + 
                (listCount == 1 ? " task" : " tasks") + " in your list.");
        System.out.println(LINE);
    }

    public static void addTodo(String input) {
        input = input.substring(5);
        list[listCount] = new Todo(input);
        System.out.println(LINE);
        System.out.println("\tUnderstood, I have noted down the following task:");
        System.out.println("\t " + list[listCount]);
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
            addTodo(input);
        } else if (instruction.length > 1 && instruction[0].equals("mark")) {
            markTask(instruction[1]);
        } else if (instruction.length > 1 && instruction[0].equals("unmark")) {
            unmarkTask(instruction[1]);
        } else {
            addTask(input);
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
