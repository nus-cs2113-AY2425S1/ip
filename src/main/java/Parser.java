public class Parser {

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
        }
        return null;
    }
}