package jeff.helper;

import java.util.Scanner;

/**
 * The Ui class handles interactions with the user by providing messages
 * such as welcome, exit, errors, and capturing user input.
 */
public class Ui {

    public static final String DIVIDER = "____________________________________________________________";
    public static final String INTRO_TEXT = """
                ____________________________________________________________
                Hello! I'm JEFF!!!
                
                Here are the type of tasks I can track!!!
                'todo [description]'
                'deadline [description] /by [some date]'
                'event [description] /from [some date] /to [some date]'
                
                List of commands I support!!!!
                Type 'list' to display everything you've said!
                Type 'mark'/'unmark' to change the status of inputted tasks!
                Type 'delete' to delete a task in the list!
                Type 'find' to search for a keyword in the task list!
                Type 'bye' to exit!
                
                Follow the above formats closely!
                If there's an error, I will try and give you some indication!
                ____________________________________________________________
                """;

    public static final String EXIT_TEXT = """
                Bye. Hope to see you again soon!
                """;

    /**
     * Displays the introductory message to welcome the user to the application.
     */
    public void showWelcome(){
        System.out.print(INTRO_TEXT);
    }

    /**
     * Displays the exit message when the user quits the application.
     */
    public void showExit(){
        System.out.print(EXIT_TEXT);
    }

    /**
     * Displays a divider to separate sections in the user interface.
     */
    public void showDivider(){
        System.out.print(DIVIDER);
    }

    /**
     * Displays a new line in the user interface.
     */
    public void showNewLine(){
        System.out.print(System.lineSeparator());
    }

    /**
     * Displays an error message if there is an issue loading data.
     *
     * @param errorMessage the specific error message to display.
     */
    public void showLoadingError(String errorMessage){
        System.out.print(DIVIDER + System.lineSeparator() + "An error occurred while loading the data file"
                + System.lineSeparator() + errorMessage + System.lineSeparator());
    }

    /**
     * Reads the command inputted by the user and returns it as a string.
     *
     * @return the user's command as a string.
     */
    public String readCommand(){
        Scanner in = new Scanner(System.in);
        System.out.print("You say:" + System.lineSeparator());
        String line = in.nextLine();
        //Divider that comes after user input
        System.out.print(DIVIDER + System.lineSeparator());
        return line;
    }
}
