import java.io.IOException;
import java.util.Scanner;

public class AnBot {

    private static final String WELCOME_MESSAGE = "\t Hello! Welcome to "; 
    private static final String ASKING_MESSAGE  = "\t What can I do for you? "; 
    private static final String EXIT_MESSAGE = "\t Bye. Hope to see you again soon!"; 
    private static final String SEPARATOR = "\t ___________________________"; 
    private static final String FILE_PATH = "data/AnBot.txt"; 
    
    public static void main(String[] args) {
        String logo = "AnBot";
        
        // Print the welcome messages
        System.out.println(SEPARATOR);
        System.out.println(WELCOME_MESSAGE + logo);
        System.out.println(ASKING_MESSAGE);
        System.out.println(SEPARATOR);

        FileClass filePath = new FileClass(FILE_PATH);
        AddList addList = new AddList(filePath);

        // Read user input
        String input;
        Scanner in = new Scanner(System.in); 

        while (true) {
            try {
                input = in.nextLine().trim();

                if (input.equals("bye")) {
                    System.out.println(SEPARATOR);
                    System.out.println(EXIT_MESSAGE); 
                    System.out.println(SEPARATOR);
                    try {
                        filePath.write(addList.getTasks()); 
                    } catch (IOException e) {
                        System.out.println("Error saving tasks to file.");
                    }
                    break; 
                } else if (input.equals("list")) {
                    addList.displayEntries(); 
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(5).trim(); 
                    addList.addTodo(description); 
                    filePath.write(addList.getTasks()); 
                } else if (input.startsWith("deadline ")) {
                    try {
                        String[] parts = input.substring(9).split(" /by "); 
                        if (parts.length != 2) {
                            throw new IllegalArgumentException("Input format is incorrect.");
                        }
                        addList.addDeadline(parts[0].trim(), parts[1].trim());
                        filePath.write(addList.getTasks()); 
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
                        filePath.write(addList.getTasks()); 
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                } else if (input.startsWith("mark ")) {
                    String inputNumber = input.substring(5).trim(); 
                    try {
                        int number = Integer.parseInt(inputNumber); 
                        addList.markAsDone(number); 
                        filePath.write(addList.getTasks()); 
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: The task index is not correct!");
                    }
                } else if (input.startsWith("unmark ")) {
                    String inputNumber = input.substring(7).trim(); 
                    try { 
                        int number = Integer.parseInt(inputNumber); 
                        addList.unmarkAsDone(number); 
                        filePath.write(addList.getTasks()); 
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: The task index is not correct!");
                    }
                } else if (input.startsWith("delete ")) {
                    String inputNumber = input.substring(7).trim(); 
                    try {
                        int number = Integer.parseInt(inputNumber); 
                        addList.delete(number);
                        filePath.write(addList.getTasks()); 
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: The task index is not correct!");
                    }
                } else {
                    throw new AnBotException("Error command. Please enter another input."); 
                }
            } catch(AnBotException e) {
                System.out.println(e.getMessage());
            } catch(IOException e) {
                System.out.println("Error saving tasks: " + e.getMessage());
            }
        }
    }
}
