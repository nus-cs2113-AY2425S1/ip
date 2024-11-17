package conglo.ui;

/**
 * Provides a manual with basic instructions for using the Conglo application.
 */
public class Manual {

    /**
     * Prints a quick manual of instructions for the Conglo application.
     */
     public static void printManual() {
         String[] manual = {
                 "Here's a quick manual to get you started:",
                 "1. Add a todo: todo [description]",
                 "2. Add a deadline: deadline [description] /by [dd-MM-yyyy HHmm]",
                 "3. Add an event: event [description] /from [start time] /to [end time]",
                 "4. Mark a task as done: mark [task number]",
                 "5. Unmark a task: unmark [task number]",
                 "6. Delete a task: delete [task number]",
                 "7. List all tasks: list",
                 "8. Find matching keyword: find [keyword]",
                 "9. Exit the application: bye"
         };

         for (String line : manual) {
             System.out.println(line);
         }
    }
}
