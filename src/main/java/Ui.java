import java.util.Scanner;

public class Ui {
    private static final String ENCLOSURE = "------------------------------";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }

    public void printWelcomeMessage() {
        printEnclosure();
        System.out.println("Hello, I am Diana! A personal assistant that helps you keep track of your tasks :)");
        System.out.println("Here are the list of things that Diana can do for you");
        printListofCommands();
        printEnclosure();
    }

    public void printListofCommands() {
        System.out.println("1. Create a todo task eg. [todo read book]");
        System.out.println("2. Create an event task eg. [event read book /from 2pm /to 4pm]");
        System.out.println("3. Create a deadline task eg. [deadline read book /by 2pm]");
        System.out.println("4. Type either mark or unmark to indicate the completion rate of your task");
        System.out.println("5. Delete a task eg. [delete 1]");
        System.out.println("6. Type list to view the your list of tasks.");
        System.out.println("7. Type bye to exit the programme");
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    public void printEnclosure() {
        System.out.println(ENCLOSURE);
    }

    public void printGoodByeMessage() {
        System.out.println("Goodbye!");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks, reverting to empty list");
    }

}
