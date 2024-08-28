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
            String[] splitInput = line.split(" ");
            printLine();

            if (line.equals("list")) {
                taskList.printList();
            } else if (splitInput.length == 2 && line.startsWith("mark")) {
                int index = Integer.parseInt(splitInput[1]);
                taskList.setItemStatus(index, true);
            } else if (splitInput.length == 2 && line.startsWith("unmark")) {
                int index = Integer.parseInt(splitInput[1]);
                taskList.setItemStatus(index, false);
            } else {
                taskList.addItem(line);
                System.out.println("added: " + line);
            }
            printLine();
            line = input.nextLine();
        }
        bye();
    }
}