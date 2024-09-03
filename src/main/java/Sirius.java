import java.util.Scanner;
import java.lang.Integer;
public class Sirius {
    // some commands
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    
    // some regexes
    public static final String SPACE = " ";
    public static final String EMPTY = "";
    public static final String SLASH = "/";
    public static final String SAPERATOR = "-----------------------------";

    // data members
    private static int taskCounter = 0;
    private static boolean isExit = true;

    // some methods
    public static void sayHello(){
        System.out.println("""
                -----------------------
                Hello! I'm Sirius!
                What can I do for you?
                -----------------------
                """);
    }
    public static void sayGoodbye(){
        System.out.println("""
                -----------------------
                Bye! Hope to see you soon.
                -----------------------
                """);
        isExit = false;
    }
    public static void markTask(String[] commandPieces, Task[] list, boolean isMarked) {
        System.out.println(SAPERATOR);
        int taskNumber = Integer.parseInt(commandPieces[1]);
        if (taskNumber > taskCounter) {
            System.out.println("Oh, please enter an valid task number!");
        }
        else{
            if (isMarked){
                list[taskNumber-1].setMarked(true);
                System.out.println("Nice! I've marked this task as done:");
            }
            else{
                list[taskNumber-1].setMarked(false);
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println(list[taskNumber-1].toString());
        }
        System.out.println(SAPERATOR);
    }
    public static void addTask(String[] commandPieces, Task[] list){
        System.out.println(SAPERATOR);
        String commandPrefix = commandPieces[0];
        String taskName = commandPieces[1];
        switch (commandPrefix){
            case "deadline":
                list[taskCounter] = new Deadline(taskName, false, commandPieces[2] );
                break;
            case "event":
                list[taskCounter] = new Event(taskName, false, commandPieces[2], commandPieces[3]);
                break;
            default:
                list[taskCounter] = new Todo(taskName, false);
        }

        System.out.println("Got it. I've added this task:");
        System.out.println(list[taskCounter].toString());
        taskCounter++;
        System.out.println("Now you have " + taskCounter + " tasks in the list.");
        System.out.println(SAPERATOR);
    }
    public static void listTasks(Task[] list){
        System.out.println(SAPERATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(list[i].toString());
        }
        System.out.println(SAPERATOR);
    }
    public static String[] splitCommand(String userInput){
        String[] slashCommand = userInput.split(SLASH);
        String commandContent = slashCommand[0];
        String commandPrefix = commandContent.split(SPACE)[0];
        String taskName = commandContent.replace(commandPrefix, EMPTY).trim();

        if (slashCommand.length == 1){ //Todo
            String[] commandPieces = new String[2];
            commandPieces[0] = commandPrefix;
            commandPieces[1] = taskName;
            return commandPieces;
        }
        else if (slashCommand.length == 2){ //Deadline
            String[] commandPieces = new String[3];
            commandPieces[0] = commandPrefix;
            commandPieces[1] = taskName;
            commandPieces[2] = slashCommand[1].replace("by", EMPTY).trim();
            return commandPieces;
        }
        else if (slashCommand.length == 3){ //Event
            String[] commandPieces = new String[4];
            commandPieces[0] = commandPrefix;
            commandPieces[1] = taskName;
            commandPieces[2] = slashCommand[1].replace("from", EMPTY).trim();
            commandPieces[3] = slashCommand[2].replace("to", EMPTY).trim();
            return commandPieces;
        }
        else{
            return new String[0];
        }
    }

    public static void main(String[] args) {
        sayHello();
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        while (isExit) {
            String userInput = scanner.nextLine();
            String[] commandPieces = splitCommand(userInput);
            String commandPrefix = commandPieces[0];

            switch (commandPrefix) {
                case BYE:
                    sayGoodbye();
                    break;
                case LIST:
                    listTasks(list);
                    break;
                case MARK:
                    markTask(commandPieces, list, true);
                    break;
                case UNMARK:
                    markTask(commandPieces, list, false);
                    break;
                default:
                    addTask(commandPieces, list);
            }
            System.out.println();

        }
        scanner.close();
    }
}
