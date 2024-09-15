package jeff;

import jeff.exception.TaskDescriptionException;
import jeff.exception.TaskFieldException;
import jeff.task.Deadline;
import jeff.task.Event;
import jeff.task.Task;
import jeff.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Jeff {

    //ArrayList of tasks
    private static ArrayList<Task> taskList = new ArrayList<>();

    //Constants
    private static final String DIVIDER = "____________________________________________________________";
    private static final String  introText = """
                ____________________________________________________________
                Hello! I'm JEFF!!!
                
                Here are the type of tasks I can track!!!
                'todo [description]'
                'deadline [description] /by [some date]'
                'event [description] /from [some date] /to [some date]'
                
                List of commands I support!!!!
                Type 'list' to display everything you've said!
                Type 'mark'/'unmark' to change the status of inputted tasks!
                Type 'bye' to exit!
                
                Follow the above formats closely!
                If you give me nonsense, I will call you out!
                Buuuuttt I will give you some indication of your error!
                ____________________________________________________________
                """;

    private static final String exitText = """
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
                """;

    private static final int TODO_LENGTH = 4;
    private static final int DEADLINE_LENGTH = 8;
    private static final int EVENT_LENGTH = 5;
    private static final int SLASH_BY_LENGTH = 3;
    private static final int SLASH_FROM_LENGTH = 5;
    private static final int SLASH_TO_LENGTH = 3;

    //Prints out lists of tasks
    public static void printList(){
        for(int i = 1; i <= taskList.size(); i++){
            System.out.print(System.lineSeparator() + i + "." + taskList.get(i-1));
        }
    }

    //Processes taskNumber and throws an exception if any
    private static int getTaskNumber(String line) throws IllegalArgumentException,
            IndexOutOfBoundsException{
        int dividerPosition = line.indexOf(" ");

        String taskNumberString = line.substring(dividerPosition + 1).trim();
        //If no number is given, or a non-number is inputted, throw exception
        if(!taskNumberString.matches("[0-9]+") || taskNumberString.isEmpty()){
            throw new IllegalArgumentException();
        }
        int taskNumber = Integer.parseInt(taskNumberString);

        if(taskNumber > taskList.size()){
            throw new IndexOutOfBoundsException();
        }
        return taskNumber;
    }

    //Marks task as complete/uncomplete, catches any errors thrown
    public static void markTask(String firstWord, String line) {
        try {
            int taskNumber = getTaskNumber(line);
            Task t = taskList.get(taskNumber - 1);
            if(firstWord.equals("mark")) {
                t.setIsDone(true);
                System.out.print("ogei marked task dOnE");
            }
            else{
                t.setIsDone(false);
                System.out.print("womp womp task not finished :(");
            }
            System.out.print(System.lineSeparator() + t);
        } catch(IllegalArgumentException e) {
            System.out.print("can u plsplspls give me a number!!!");
        } catch(IndexOutOfBoundsException e){
            System.out.print("u tryna mark a task number that isn't in the list...");
        }
    }

    //Deletes the specified task, catches any error thrown
    private static void deleteTask(String firstWord, String line) {
        try {
            int taskNumber = getTaskNumber(line);
            Task t = taskList.get(taskNumber - 1);
            taskList.remove(taskNumber - 1);
            System.out.print("i have reeeemoved the taskkk: " +
                    System.lineSeparator() + t);
        } catch(IllegalArgumentException e) {
            System.out.print("eh how to delete a non-number task...");
        } catch(IndexOutOfBoundsException e){
            System.out.print("how to delete a non-existent task...");
        }
        System.out.print(System.lineSeparator() +
                "YAYYYY!!! Only " + taskList.size() + " task(s) left in ur list!");
    }

    public static void invalidInput(){
        System.out.print("eh can you give me one of the inputs i specified above...");
    }

    public static void processCommands(String firstWord, String line){
        switch(firstWord){
        case "list":
            System.out.print("orh hor never finish ur tasks:");
            printList();
            break;
        case "mark":
        case "unmark":
            markTask(firstWord, line);
            break;
        case "delete":
            deleteTask(firstWord, line);
            break;
        default:
            System.out.print("Some command not processed...");
        }
    }

    //Returns the description of a task, returns an empty string otherwise
    public static String processTaskDescription(String line , int start, int end){
        //If the task fields are empty, set the end number to the length of the line
        if(start > end){
            end = line.length();
        }
        //Remove the empty space before the description
        String description = line.substring(start, end).trim();
        //Ensuring the description is not just empty spaces
        if(description.isEmpty()){
            return "";
        }
        return description;
    }

    //Returns the field of the task, returns an empty string otherwise
    public static String processTaskField(String line, int fieldIndex, int start, int end){
        if(fieldIndex == -1){
            return "";
        }
        if(start > end){
            end = line.length();
        }
        String field = line.substring(start, end).trim();
        if(field.isEmpty()){
            return "";
        }
        //If field is valid, remove the empty space before the field
        return field;
    }

    //Instantiates Todo object
    public static Todo processTodo(String line) throws TaskDescriptionException {
        String description = processTaskDescription(line, TODO_LENGTH, line.length());
        if(description.isEmpty()) {
            throw new TaskDescriptionException("todo");
        }
        return new Todo(description);
    }

    //Instantiates Deadline object
    public static Deadline processDeadline(String line) throws TaskDescriptionException,
            TaskFieldException {
        int byIndex = line.indexOf("/by");
        String description = processTaskDescription(line, DEADLINE_LENGTH, byIndex);
        if(description.isEmpty()) {
            throw new TaskDescriptionException("deadline");
        }
        String by = processTaskField(line, byIndex, byIndex + SLASH_BY_LENGTH, line.length());
        if(by.isEmpty()) {
            throw new TaskFieldException("'by'");
        }
        return new Deadline(description, by);
    }

    //Creates the Exception message for Event fields (if any)
    public static String eventExceptionMessage(boolean from, boolean to){
        StringBuilder errMsg = new StringBuilder();
        if (from) {
            errMsg.append("'from'");
        }

        if (from && to) {
            errMsg.append(" and ");
        }

        if (to) {
            errMsg.append("'to'");
        }
        return errMsg.toString();
    }

    //Instantiates Event object
    public static Event processEvent(String line) throws TaskDescriptionException,
            TaskFieldException{
        int fromIndex = line.indexOf("/from");
        int toIndex = line.lastIndexOf("/to");
        String description = processTaskDescription(line, EVENT_LENGTH, fromIndex - 1);
        if(description.isEmpty()) {
            throw new TaskDescriptionException("event");
        }
        String from = processTaskField(line, fromIndex, fromIndex + SLASH_FROM_LENGTH, toIndex - 1);
        String to = processTaskField(line, toIndex, toIndex + SLASH_TO_LENGTH, line.length());
        String errMsg = eventExceptionMessage(from.isEmpty(), to.isEmpty());
        if(!errMsg.isEmpty()) {
            throw new TaskFieldException(errMsg);
        }
        return new Event(description, from, to);
    }

    //Creates new objects based on the type of tasks
    public static void processTasks(String firstWord, String line) {
        Task t;
        try {
            switch (firstWord) {
            case "todo":
                t = processTodo(line);
                break;
            case "deadline":
                t = processDeadline(line);
                break;
            case "event":
                t = processEvent(line);
                break;
            default:
                t = null;
                break;
            }

            //Adding the different tasks to taskList once processed.
            taskList.add(t);

            //Prints the following text if the user inputs the task with the correct fields
            System.out.print("Haiyaa the following task needs to be done:" + System.lineSeparator()
                    + "  " + t + System.lineSeparator() +
                    "Now you have " + taskList.size() + " task(s) in ur list");

        //Otherwise it will print what the user has done wrong, and how to rectify it
        } catch (TaskDescriptionException e) {
            System.out.print("Oi!!! Your " + e.getMessage() + " must have a description la...");
        } catch (TaskFieldException e) {
            System.out.print("walao eh... your task is missing " + e.getMessage() + " " + "field(s) la..." +
                    " follow the format can anot");
        }
    }

    //Returns the first word of a String
    public static String processLine(String line) {
        int firstSpace = line.indexOf(" ");
        if (firstSpace != -1) {
            return line.substring(0, firstSpace);
        } else {
            return line; // In case there's only one word with no spaces
        }
    }

    public static void runBot(){
        Scanner in = new Scanner(System.in);
        String line;
        System.out.print("You say:" + System.lineSeparator());

        //returns it 'bye' is inputted
        while(!(line = in.nextLine()).equals("bye")){
            //Divider that comes after user input
            System.out.print(DIVIDER + System.lineSeparator());
            //Getting first word of user input
            String firstWord = processLine(line);

            //Carry out different actions based on the first word
            switch(firstWord) {
            case "todo":
            case "deadline":
            case "event":
                processTasks(firstWord, line);
                break;
            case "list":
            case "mark":
            case "unmark":
            case "delete":
                processCommands(firstWord, line);
                break;
            default:
                invalidInput();
            }
            //Prints divider and header for next user input
            System.out.print(System.lineSeparator() + DIVIDER + System.lineSeparator() +
                    "You say:" + System.lineSeparator());
        }
    }

    //Prints Intro text, runs the chatbot, prints the Exit text
    public static void main(String[] args) {
        System.out.print(introText);
        runBot();
        System.out.println(exitText);
    }
}
