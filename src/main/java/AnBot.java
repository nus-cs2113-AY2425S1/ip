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
            input = in.nextLine();

            if (input.equals("bye")) {
                System.out.println(SEPARATOR);
                System.out.println(EXIT_MESSAGE); 
                System.out.println(SEPARATOR);
                break; 
            } else if (input.equals("list")) {
                addList.displayEntries(); 
            } else if (input.startsWith("mark ")) {
                String inputNumber = input.substring(5).trim(); 
                System.out.println(inputNumber);
                int number = Integer.parseInt(inputNumber); 
                addList.markAsDone(number); 
            } else if (input.startsWith("unmark ")) {
                String inputNumber = input.substring(7).trim(); 
                System.out.println(inputNumber);
                int number = Integer.parseInt(inputNumber); 
                addList.unmarkAsDone(number); 
            } else {
                addList.addEntry(input); 
            }
        }
    }
}
