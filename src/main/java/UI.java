/**
 * Handles common operations while outputting information.
 */

public class UI {
    public static final String DIVIDER_LINE = "____________________________________________________________";

    /**
     * Prints a single line of output with dividers.
     */
    public static void printContent(String content) {
        System.out.println(DIVIDER_LINE);
        System.out.println(content);
        System.out.println(DIVIDER_LINE);
    }

    /**
     * Prints the help manual.
     */
    public static void printHelp() {
        System.out.println(DIVIDER_LINE);
        System.out.println("Welcome to Aerus, your ultimate task manager and planner!\n");
        System.out.println("Aerus offers the following commands:");
        System.out.println("todo     [description]                          : note down a todo task");
        System.out.println("deadline [description] /by [time]               : note down a deadline task");
        System.out.println("event    [description] /from [time] /to [time]  : note down an event task");
        System.out.println("list                                            : view all your tasks");
        System.out.println("search   [input query]                          : search for tasks by description");
        System.out.println("mark     [task number]                          : mark a task as done");
        System.out.println("unmark   [task number]                          : mark a task as undone");
        System.out.println("delete   [task number]                          : delete a task from Aerus");
        System.out.println("bye / quit                                      : Exit the program");
        System.out.println("\nAerus saves all your tasks so you don't have to worry about missing anything!");
        System.out.println("Happy planning!");
        System.out.println(DIVIDER_LINE);
    }

    /**
     * Greets the user when the program starts.
     */
    public static void greetUser() {
        printContent("Hello! I'm Aerus!\nWhat can I do for you?\nType \"help\" to get started!");
    }

    /**
     * Print a message indicating the program's termination.
     */
    public static void exitProgram() {
        printContent("See you next time!");
    }

}