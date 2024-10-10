import java.util.Scanner;

public class Ui {

    private static final String WELCOME_MESSAGE = "\t Hello! Welcome to "; 
    private static final String ASKING_MESSAGE  = "\t What can I do for you? "; 
    private static final String EXIT_MESSAGE = "\t Bye. Hope to see you again soon!"; 
    private static final String SEPARATOR = "\t ___________________________"; 
    private static final String LOGO = "AnBot"; 

    private Scanner scanner; 

    public Ui() {
        this.scanner = new Scanner(System.in);  
    }

    public void printWelcome() {
        System.out.println(SEPARATOR);
        System.out.println(WELCOME_MESSAGE + LOGO);
        System.out.println(ASKING_MESSAGE);
        System.out.println(SEPARATOR);
    }

    public void printExit() {
        System.out.println(SEPARATOR);
        System.out.println(EXIT_MESSAGE); 
        System.out.println(SEPARATOR);
    }

    public void printError(String message) {
        System.out.println("ERROR: " + message); 
    }

    public String readInput() {
        return scanner.nextLine().trim(); 
    }
}
