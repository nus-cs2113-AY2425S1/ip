import java.util.Scanner;

public class Ellio {

    private static Task[] listTask = new Task[100];
    private static int numberTask = 0;
    public static final int DEADLINE_INPUT_SPACING = 9;
    public static final int EVENT_INPUT_SPACING = 6;


    public static void endProgram(){
        System.out.println(BotText.lineBorder + BotText.messageGoodbye + BotText.lineBorder);
    }

    public static void startProgram(){
        System.out.println(BotText.lineBorder + BotText.messageWelcome + BotText.lineBorder);
    }

    public static void printList(){
        System.out.print(BotText.lineBorder + BotText.messageList);
        for(int i = 0; i < numberTask; i++){
            System.out.println((i+1) + "." + listTask[i].getTask());
        }
        System.out.println(BotText.lineBorder);
    }

    public static String extractTaskDescriptionFromInput(String line){
        String[] words = line.split(" ");
        String resultString = "";
        for(int i = 1; i < words.length; i++){
            resultString += words[i] + " ";
        }
        return resultString;
    }

    public static void addToDo(String line){
        String description = line.replace("todo ", "");
        Todo newTodo = new Todo(description);
        listTask[numberTask] = newTodo;
        numberTask++;
        System.out.println(BotText.lineBorder + "Got it. I've added this task:\n  " + newTodo.getTask());
        System.out.println("Now you have " + numberTask + " tasks in the list.\n" + BotText.lineBorder);
    }

    public static void addDeadline(String line){
        // Find the indices of the first and second occurrences of "/"
        int firstSlashIndex = line.indexOf("/");

        // Extract the substrings based on the slash positions
        String description = line.substring(DEADLINE_INPUT_SPACING, firstSlashIndex).trim();
        String deadline = line.substring(firstSlashIndex + 1).trim();

        //add Semicolon behind by
        deadline = deadline.replace("by", "by:");

        Deadline newDeadline = new Deadline(description, deadline);
        listTask[numberTask] = newDeadline;
        numberTask++;
        System.out.println(BotText.lineBorder + "Got it. I've added this task:\n  " + newDeadline.getTask());
        System.out.println("Now you have " + numberTask + " tasks in the list.\n" + BotText.lineBorder);
    }

    public static void addEvent(String line){
        // Find the indices of the first and second occurrences of "/"
        int firstSlashIndex = line.indexOf("/");
        int secondSlashIndex = line.indexOf("/", firstSlashIndex + 1);

        // Extract the substrings based on the slash positions
        String description = line.substring(EVENT_INPUT_SPACING, firstSlashIndex).trim();
        String eventStart = line.substring(firstSlashIndex + 1, secondSlashIndex).trim();
        String eventEnd = line.substring(secondSlashIndex + 1).trim();

        //add Semicolon behind from and to
        eventStart = eventStart.replace("from", "from:");
        eventEnd = eventEnd.replace("to", "to:");

        Event newEvent = new Event(description, eventStart, eventEnd);
        listTask[numberTask] = newEvent;
        numberTask++;
        System.out.println(BotText.lineBorder + "Got it. I've added this task:\n  " + newEvent.getTask());
        System.out.println("Now you have " + numberTask + " tasks in the list.\n" + BotText.lineBorder);
    }

    public static void markList(int index){
        listTask[index-1].markTaskAsDone();
        System.out.println(BotText.lineBorder + BotText.messageMarked + "  " + listTask[index-1].getTask() + "\n" + BotText.lineBorder);
    }

    public static void unmarkList(int index){
        listTask[index-1].unmarkTaskAsDone();
        System.out.println(BotText.lineBorder + BotText.messageUnmark + "  " + listTask[index-1].getTask() + "\n" + BotText.lineBorder);
    }

    public static void getInput(){
        String input;
        Scanner in = new Scanner(System.in);
        input = (in.nextLine()).toLowerCase();

        while(!input.equals("bye")){
            printInput(input);
            input = in.nextLine().toLowerCase();
        }
    }

    public static void printInput(String input){
        if(input.equals("list")){
            printList();
        }
        else if(input.startsWith("mark")){
            String[] words = input.split(" ");
            markList(Integer.parseInt(words[1]));
        }
        else if(input.startsWith("unmark")){
            String[] words = input.split(" ");
            unmarkList(Integer.parseInt(words[1]));
        }
        else if(input.startsWith("todo")){
            addToDo(input);
        }
        else if(input.startsWith("deadline")){
            addDeadline(input);
        }
        else if(input.startsWith("event")){
            addEvent(input);
        }
    }

    public static void main(String[] args) {
        startProgram();
        getInput();
        endProgram();
    }
}
