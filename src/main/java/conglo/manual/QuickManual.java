package conglo.manual;

public class QuickManual {

    /**
     * Prints a quick manual of instructions for the Conglo application.
     */
    public static void printManual() {
        System.out.println("Here's a quick manual to get you started:");
        System.out.println("1. Add a todo: todo [description]");
        System.out.println("2. Add a deadline: deadline [description] /by [deadline]");
        System.out.println("3. Add an event: event [description] /from [start time] /to [end time]");
        System.out.println("4. Mark a task as done: mark [task number]");
        System.out.println("5. Unmark a task: unmark [task number]");
        System.out.println("6. List all tasks: list");
        System.out.println("7. Exit the application: bye");
    }
}
