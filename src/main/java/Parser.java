/**
 * The Parser class is responsible for interpreting user input
 * commands and creating the corresponding Command objects.
 * It processes a given line of input and determines the appropriate
 * action based on the command type.
 */

public class Parser {
/**
 * Parses the specified input line and returns the corresponding
 * Command object based on the command keyword.
 * The method recognizes the following commands:
 *     todo - Adds a new todo task.
 *     deadline - Adds a task with a deadline.
 *     event - Adds an event with a start and end date.
 *     delete - Deletes a specified task.
 *     mark - Marks a task as completed.
 *     unmark - Unmarks a completed task.
 *     list - Lists all tasks.
 *     find - Finds a task based on a keyword.
 * If the input line does not correspond to a recognized command,
 * or if the required parameters are missing, an error
 * message is printed to the console.
 */

    public static Command parse(String line){
        String[] parts = line.split(" ");

        switch(parts[0]){
        case "todo":
            try{
                return new AddCommand(line);
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("Please specify todo <task> ");
            }
            break;


        case "deadline":
            try{
                return new AddDeadlineCommand(line);
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("Please specify deadline <task> /by <End Date> ");
            }
            break;


        case "event":
            try{
                return new AddEventCommand(line);
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("Please specify event <task> /from <Start Date> /to <End Date> ");
            }
            break;

        case "delete":
            try{
                return new DeleteCommand(line);
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("The selected item is not found");
            }
            break;

        case "mark":
            try {
                return new MarkCommand(line);
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("The selected item is not found");
            }
            break;

        case "unmark":
            try {
                return new UnmarkCommand(line);
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("The selected item is not found");
            }
            break;

        case "list":
            try {
                return new ListCommand();
            } catch (Exception e){
                System.out.println(" " + e.getMessage());
            }
            break;

        case "find":
            try{
                return new FindCommand(line);
            } catch (Exception e){
                System.out.println("The selected item is not found");
            }
            break;
        }
        return null;
    }
}