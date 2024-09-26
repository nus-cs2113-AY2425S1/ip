package Yappatron;

import java.util.Scanner;

public class Ui {

    Scanner in = new Scanner(System.in);
    public void printWelcomeMessage(){
        System.out.println("Hello! I'm Yappatron");
        System.out.println("What can I do for you?");
    }

    public String getInput(){
        String input;
        input = in.nextLine();
        return input;
    }

    public void printByeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showFileLoadingError(){
        System.out.println("File not found. Creating file now.");
    }

    public void printWriteFileError(){
        System.out.println("Error occured while writing!");
    }
    public void invalidCommand(){
        System.out.println("Invalid command! I do not understand what that means.");
    }

}
