package commands;

public class HelpCommand {
    public void execute(){
        System.out.print("""
    Here is a list of possible commands:

      1. list
        - Lists out the tasks

      2. bye
        - Exits the program

      3. todo /desc <desc>
        - Creates a Todo task

      4. deadline /desc <desc> /by <dueBy>
        - Creates a Deadline task

      5. event /desc <desc> /from <start> /to <end>
        - Creates a Event task

      6. delete <index>
        - Deletes task at index (1-based indexing)

      7. mark <index>
        - Marks a task at index (1-based indexing)

      8. unmark <index>
        - Unmarks a task at index (1-based indexing)

      9. find /match <searchTerm>
        - Finds a task containing search term (case insensitive)
    """);
    }
}
