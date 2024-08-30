import java.util.Scanner;

public class Miku {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TaskList taskList = new TaskList();

        printGreeting();

        String line = input.nextLine();
        printDivider();

        while(!line.equals("bye")){

            if (line.equals("list")){
                taskList.printTaskList();
            } else if(line.startsWith("mark")){
                taskList.attemptToMarkTask(line);
            } else if(line.startsWith("unmark")){
                taskList.attemptToUnmarkTask(line);
            } else{
                taskList.addTask(line);
            }

            printDivider();
            line = input.nextLine();
            printDivider();
        }

        System.out.println("Bye, see you later!");
        printDivider();

        input.close();
    }

    /**
     * Prints a greeting to the user
     */
    public static void printGreeting(){
        String logo =
            """
             __  __   _   _           \s
            |  \\/  | (_) | |          \s
            | \\  / |  _  | | __  _   _\s
            | |\\/| | | | | |/ / | | | |
            | |  | | | | |   <  | |_| |
            |_|  |_| |_| |_|\\_\\  \\__,_|
            """;
        //Text to Ascii generated through https://patorjk.com/software/taag/
        System.out.println(logo);

        printDivider();
        System.out.println("Hello! I'm Miku\nWhat can I do for you?");
        printDivider();
    }

    /**
     * Prints a line divider consisting of _ characters
     */
    public static void printDivider(){
        System.out.println("____________________________________________________________");
    }
}
