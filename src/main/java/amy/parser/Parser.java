package parser;
import amy.Amy;
import exception.AmyException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Parser {
    private String commandType;
    private ArrayList<String> args;
    private String parseErrorMsg = "Unable to parse request.";
    public String parseRequestType(String request){
        this.commandType = request.split(" ")[0];
        return this.commandType;
    }
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
        System.out.println(argList);
        return argList;
    }
    public void parseMark(String request) throws AmyException{
        final String MARK_PATTERN = "mark\\s(.+)";
        args = getArgument(request, MARK_PATTERN);
    }
    public void parseUnmark(String request) throws AmyException{
        final String UNMARK_PATTERN = "unmark\\s(.+)";
        args = getArgument(request, UNMARK_PATTERN);
    }
    public void parseDelete(String request) throws AmyException{
        final String DELETE_PATTERN = "delete\\s(.+)";
        args = getArgument(request, DELETE_PATTERN);
    }
    public void parseDeadline(String request) throws AmyException{
        final String DEADLINE_PATTERN = "deadline\\s(.+)\\s/by\\s(.+)";
        args = getArgument(request, DEADLINE_PATTERN);
    }
    public void parseTodo(String request) throws AmyException{
        final String TODO_PATTERN = "todo\\s(.+)";
        args = getArgument(request, TODO_PATTERN);
    }
    public void parseEvent(String request) throws AmyException{
        final String EVENT_PATTERN = "event\\s(.+)\\s/from\\s(.+)\\s/to\\s(.+)";
        args = getArgument(request, EVENT_PATTERN);
    }
    public void parse(String request) throws AmyException{
        if(request.length() == 0){
            throw new AmyException("Please input a request! (O_O)");
        }
        commandType = parseRequestType(request);
        switch (commandType){
            case "list", "greet", "bye" -> args = new ArrayList<>();
            case "delete" -> parseDelete(request);
            case "mark" -> parseMark(request);
            case "unmark" -> parseUnmark(request);
            case "deadline" -> parseDeadline(request);
            case "todo" -> parseTodo(request);
            case "event" -> parseEvent(request);
            default -> throw new AmyException(parseErrorMsg);
        }
    }

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
