package ui;

import java.util.Scanner;

public class UI {
    public static final int LINE_LENGTH = 60;
    private static Scanner in = new Scanner(System.in);

    /**
     * Prints the response to the console, wrapped with horizontal lines above 
     * and below the response.
     * 
     * @param response String to be printed.
     */
    public static void printResponse(String response) {
        System.out.println("-".repeat(LINE_LENGTH));
        System.out.println(response);
        System.out.println("-".repeat(LINE_LENGTH));
    }

    /**
     * Prints a greeting message to the console.
     * Displays the bot's logo and a welcome message.
     */
    public static void greet() {
        printResponse("""
        Hello, I'm AngelBot!
        (\\ -=- /)
        ( \\( )/ )
        (       )
         `>   <'
         /     \\
         `-._.-'
        How may I assist you today?""");
    }

    /**
     * Prints a farewell message and exits the program.
     */
    public static void sayBye() {
        printResponse("Bye, see you again soon!");
    }

    /**
     * Gets the user input from the console.
     * 
     * @return A string containing the user's input
     */
    public static String getUserInput() {
        return in.nextLine();
    }

    public static final String HELP_MESSAGE = """
    To add tasks:
        todo <description> : Adds a basic to-do task.
        deadline <description> /by <date> : Adds a task with a deadline. 
        event <description> /from <start> /to <end> : Adds an event task.

    Other commands:
        help : Displays this help message.
        list : Displays all tasks with their respective index.
        delete <task_number> : Deletes a task by its number.
        mark <task_number> : Marks a task as done.
        unmark <task_number> : Unmarks a task.
        find <search_term> : Finds tasks based on their name or description.
        bye : Exits the program.            
    """;
}
