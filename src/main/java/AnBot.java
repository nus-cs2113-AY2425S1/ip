import java.util.Scanner;

public class AnBot {

    private static final String WELCOME_MESSAGE = "\t Hello! Welcome to "; 
    private static final String ASKING_MESSAGE  = "\t What can I do for you? "; 
    private static final String EXIT_MESSAGE = "\t Bye. Hope to see you again soon!"; 
    private static final String SEPARATOR = "\t ___________________________"; 
    public static void main(String[] args) {
        String logo = "AnBot";

        // Print the welcome messages
        System.out.println(SEPARATOR);
        System.out.println(WELCOME_MESSAGE + logo);
        System.out.println(ASKING_MESSAGE);
        System.out.println(SEPARATOR);

        // Read user input
        String input;
        Scanner in = new Scanner(System.in); 
        AddList addList = new AddList(); 

        while (true) {
            input = in.nextLine().trim();

            if (input.equals("bye")) {
                System.out.println(SEPARATOR);
                System.out.println(EXIT_MESSAGE); 
                System.out.println(SEPARATOR);
                break; 
            } else if (input.equals("list")) {
                addList.displayEntries(); 
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5).trim(); 
                addList.addTodo(description); 
            } else if (input.startsWith("deadline ")) {
                try {
                    String[] parts = input.substring(9).split(" /by "); 
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Input format is incorrect.");
                    }
                    addList.addDeadline(parts[0].trim(), parts[1].trim());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } 
            } else if (input.startsWith("event ")) {
                try {
                    String[] parts = input.substring(6).split(" /from | /to ");
                    if (parts.length != 3) {
                        throw new IllegalArgumentException("Input format is incorrect.");
                    }
                    addList.addEvent(parts[0].trim(), parts[1].trim(), parts[2].trim());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } 
            } else if (input.startsWith("mark ")) {
                String inputNumber = input.substring(5).trim(); 
                try {
                    int number = Integer.parseInt(inputNumber); 
                    addList.markAsDone(number); 
                } catch (NumberFormatException e) {
                    System.out.println("ERROR: The task index is not correct!");
                }
            } else if (input.startsWith("unmark ")) {
                String inputNumber = input.substring(7).trim(); 
                try { 
                    int number = Integer.parseInt(inputNumber); 
                    addList.unmarkAsDone(number); 
                } catch (NumberFormatException e) {
                    System.out.println("ERROR: The task index is not correct!");
                }
            } else {
                System.out.println("Error command. Please enter another input.");;
            }
        }
    }
}
