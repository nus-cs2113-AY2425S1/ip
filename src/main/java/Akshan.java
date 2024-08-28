import java.util.Scanner;

public class Akshan {

    private static final String name = "Akshan";
    private static final String logo = """
 
             ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓███████▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░░▒▓███████▓▒░ \s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓████████▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓████████▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            """;


    private static void printLine(){
        System.out.println("_______________________________________________________________");
    }

    private static void init(){
        System.out.println(logo);
        printLine();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printLine();
    }

    private static void bye(){
        printLine();
        System.out.println( "Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String line;
        Scanner input = new Scanner(System.in);
        TaskList taskList = new TaskList();

        init();
        line = input.nextLine();

        while (!line.equals("bye")) {
            printLine();
            if (line.equals("list")) {
                taskList.printList();
            } else if (line.startsWith("mark")) {
                String[] splitInput = line.split(" ");
                int index = Integer.parseInt(splitInput[1]);
                taskList.setItemStatus(index, true);
            } else if (line.startsWith("unmark")) {
                String[] splitInput = line.split(" ");
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