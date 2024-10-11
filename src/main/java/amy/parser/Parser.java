package amy.parser;
import amy.Amy;
import amy.exception.AmyException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a parser that parses the user input and executes the corresponding action.
 * The parser is able to parse the command type and arguments from the user input.
 * The parser is able to execute the corresponding action based on the command type.
 */
public class Parser {
    private String commandType;
    private ArrayList<String> args;
    private String parseErrorMsg = "Unable to parse request. T_T";
    private String pattern;
    /**
     * Parses the request type from the user input.
     * @param request
     * @return The type of request (i.e list, greet, bye, mark, unmark, delete, deadline, todo, event, find)
     */
    public String parseRequestType(String request){
        this.commandType = request.split(" ")[0];
        return this.commandType;
    }

    /**
     * Parses the list of arguments from the user input based on type of commandType input
     * @param request The user input
     * @param pattern The corresponding pattern for the commandType
     * @return The list of arguments for the commandType specified
     * @throws AmyException If the format of the request is incorrect
     */
    private static ArrayList<String> getArgument(String request, String pattern) throws AmyException{
        ArrayList<String> argList = new ArrayList<>();
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(request);
        if(!matcher.matches()){
            throw new AmyException("Wrong format given! Would you like to try again? \\ o /");
        } else{
            for(int i = 1; i <= matcher.groupCount(); i++){
                argList.add(matcher.group(i));
            }
        }
        return argList;
    }

    /**
     * Parses the user input and sets the commandType and args based on different pattern of inputted command.
     * @param request The user input
     * @throws AmyException 
     */
    public void parse(String request) throws AmyException{
        if(request.length() == 0){
            throw new AmyException("Please input a request! (O_O)");
        }
        commandType = parseRequestType(request);
        switch (commandType){
            case "list", "greet", "bye" -> pattern = "";
            case "delete" -> pattern = "delete\\s(.+)";
            case "mark" -> pattern = "mark\\s(.+)";
            case "unmark" -> pattern = "unmark\\s(.+)";
            case "deadline" -> pattern = "deadline\\s(.+)\\s/by\\s(.+)";
            case "todo" -> pattern = "todo\\s(.+)";
            case "event" -> pattern = "event\\s(.+)\\s/from\\s(.+)\\s/to\\s(.+)";
            case "find" -> pattern = "find\\s(.+)";
            default -> throw new AmyException(parseErrorMsg);
        }
        if(pattern.length() > 0){
            args = getArgument(request, pattern);
        } else{
            args = new ArrayList<>();
        }
    }

    /**
     * Executes the corresponding action based on the commandType.
     * @throws AmyException If the commandType is not recognized or if the format of the request is incorrect
     */
    public void chooseAction() throws AmyException{
        try{
            switch(commandType){
                case "greet" -> Amy.doGreeting();
                case "bye" -> Amy.exit();
                case "list" -> Amy.showTaskList();
                case "mark" -> Amy.markTask(Integer.parseInt(args.get(0)) - 1, true);
                case "unmark" -> Amy.markTask(Integer.parseInt(args.get(0)) - 1, false);
                case "todo" -> Amy.addTodo(args.get(0));
                case "deadline" -> Amy.addDeadline(args.get(0), args.get(1));
                case "event" -> Amy.addEvent(args.get(0), args.get(1), args.get(2));
                case "delete" -> Amy.deleteTask(Integer.parseInt(args.get(0)) - 1);
                case "find" -> Amy.findTask(args.get(0));
                default -> throw new AmyException("I didn't understand what you meant (TT_TT) I'm still learning so do try again next time!");
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println("Please input the correct format for your request! (ToT)");
        } catch(NumberFormatException e){
            System.out.println("Please input a number for this request (O_O);;");
        } catch (AmyException e){
            System.out.println(e.getMessage());
        }
    }

}
