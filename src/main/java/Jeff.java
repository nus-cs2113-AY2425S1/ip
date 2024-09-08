import java.util.Scanner;
import java.util.Random;

public class Jeff {

    //Constants
    public static final String DIVIDER = "____________________________________________________________";
    public static final String  introText = """
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
                
                If you give me nonsense, I will echo whatever you say!
                Buuuuttt with random capitalisation!
                ____________________________________________________________
                """;

    public static final String exitText = """
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
                """;

    public static final int TODO_LENGTH = 5;
    public static final int DEADLINE_LENGTH = 9;
    public static final int EVENT_LENGTH = 6;
    public static final int SLASH_BY_LENGTH = 4;
    public static final int SLASH_FROM_LENGTH = 6;
    public static final int SLASH_TO_LENGTH = 4;

    //Randomly capitalises strings in echo
    private static String randomlyCapitalise(String line){ //Randomly capitalizes a string
        Random randomBool = new Random();
        StringBuilder randomString = new StringBuilder(line.length());
        for(int i = 0; i < line.length(); i++){
            if(randomBool.nextBoolean()) {
                randomString.append(Character.toUpperCase(line.charAt(i)));
            } else {
                randomString.append(Character.toLowerCase(line.charAt(i)));
            }
        }
        return randomString.toString();
    }

    //Prints out lists of tasks
    public static void printList(){
        for(int i = 1; i <= Task.getCount(); i++){
            System.out.print(System.lineSeparator() + i + "." + Task.getList()[i-1]);
        }
    }

    //Marks task as complete/uncomplete
    public static void markTask(String firstWord, String line){
        int dividerPosition = line.indexOf(" ");
        int taskNumber = Integer.parseInt(line.substring(dividerPosition + 1, dividerPosition + 2));
        Task t = Task.getList()[taskNumber - 1];
        if(firstWord.equals("mark")) {
            t.isDone = true;
            System.out.print("ogei marked task dOnE" + System.lineSeparator());
        }
        else{
            t.isDone = false;
            System.out.print("womp womp task not finished :(" + System.lineSeparator());
        }
        System.out.print(t);
    }

    //Prints a randomly capitalised string of whatever the user inputted
    public static void echo(String line){
        String echo = randomlyCapitalise(line);
        System.out.print("I echo:" + System.lineSeparator() + echo);
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
        }
    }

    //Instantiates Todo object
    public static Todo processTodo(String line){
        String description = line.substring(TODO_LENGTH);
        return new Todo(description);
    }

    //Instantiates Deadline object
    public static Deadline processDeadline(String line){
        int byIndex = line.indexOf("/by");
        String by = line.substring(byIndex + SLASH_BY_LENGTH);
        String description = line.substring(DEADLINE_LENGTH, byIndex - 1);
        return new Deadline(description, by);
    }

    //Instantiates Event object
    public static Event processEvent(String line){
        int fromIndex = line.indexOf("/from");
        int toIndex = line.lastIndexOf("/to");
        String from = line.substring(fromIndex + SLASH_FROM_LENGTH, toIndex - 1);
        String to = line.substring(toIndex + SLASH_TO_LENGTH);
        String description = line.substring(EVENT_LENGTH, fromIndex - 1);
        return new Event(description, from, to);
    }

    //Creates new objects based on the type of tasks
    public static void processTasks(String firstWord, String line){
        //Header text for task
        System.out.print("Haiyaa the following task needs to be done:" + System.lineSeparator());

        Task t = null;
        switch(firstWord) {
        case "todo":
            t = processTodo(line);
            break;
        case "deadline":
            t = processDeadline(line);
            break;
        case "event":
            t = processEvent(line);
            break;
        }
        System.out.print("  " + t + System.lineSeparator() +
                "Now you have " + Task.getCount() + " task in ur list");
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
                processCommands(firstWord, line);
                break;
            default:
                echo(line);
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
