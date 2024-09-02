import java.util.Scanner;

public class Akshan {

    private static final String NAME = "Akshan";
    private static final String LOGO = """
 
             ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓███████▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░░▒▓███████▓▒░ \s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓████████▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓████████▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            """;


    /**
     * Prints a horizontal line.
     */
    private static void printLine(){
        System.out.println("_______________________________________________________________");
    }

    /**
     * Initializes the bot, prints the logo and a welcome message.
     */
    private static void init(){
        System.out.println(LOGO);
        printLine();
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Exit bot, prints goodbye sequence.
     */
    private static void bye(){
        printLine();
        System.out.println( "Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        String line;
        Scanner input = new Scanner(System.in);
        TaskList taskList = new TaskList();

        init();
        line = input.nextLine();

        while (!line.equals("bye")) {
            String[] splitInput = line.split("/");
            printLine();

            if (line.equals("list")) {
                taskList.printList();
            } else if (line.startsWith("mark") && splitInput[0].split(" ").length == 2) {
                int index = Integer.parseInt(splitInput[0].split(" ")[1]);
                taskList.setItemStatus(index, true);
            } else if (line.startsWith("unmark") && splitInput[0].split(" ").length == 2) {
                int index = Integer.parseInt(splitInput[0].split(" ")[1]);
                taskList.setItemStatus(index, false);
            } else if (line.startsWith("todo")) {
                Todo todo = new Todo(line.split(" ", 2)[1]);

                taskList.addItem(todo);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            } else if (line.startsWith("deadline")) {
                String[] description = line.split(" /by ", 2);
                description[0] = description[0].split(" ", 2)[1];
                Deadline deadline = new Deadline(description[0], description[1]);

                taskList.addItem(deadline);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            } else if (line.startsWith("event")) {
                String[] description = line.split(" /", 3);
                description[0] = description[0].split(" ", 2)[1];
                description[1] = description[1].split(" ", 2)[1];
                description[2] = description[2].split(" ", 2)[1];
                Event event = new Event(description[0], description[1], description[2]);

                taskList.addItem(event);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + event);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            } else {
                System.out.println("Uh oh, no command found in: " + line);
            }

            printLine();
            line = input.nextLine();
        }
        bye();
    }
}