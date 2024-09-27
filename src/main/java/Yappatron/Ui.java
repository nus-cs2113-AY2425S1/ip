package Yappatron;

import java.util.Scanner;

/**
 * Responsible for interacting with the user.
 */
public class Ui {

    Scanner in = new Scanner(System.in);

    /**
     * Prints a welcome message on execution of program
     */
    public void printWelcomeMessage(){
        System.out.println("Hello! I'm Yappatron");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads user input command.
     * @return User input
     */
    public String getInput(){
        String input;
        input = in.nextLine();
        return input;
    }

    /**
     * Prints a bye message before exiting the program
     */
    public void printByeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints an error message if the txt file is not found
     */
    public void showFileLoadingError(){
        System.out.println("File not found. Creating file now.");
    }

    /**
     * Prints an error if unable to write to txt file
     */
    public void printWriteFileError(){
        System.out.println("Error occured while writing!");
    }

    /**
     * Prints a message to inform user that an invalid command has been entered.
     */
    public void invalidCommand(){
        System.out.println("Invalid command! I do not understand what that means.");
    }

}
