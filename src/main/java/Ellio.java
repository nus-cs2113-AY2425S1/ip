import java.util.Scanner;

public class Ellio {

    public static final int DEADLINE_INPUT_SPACING = 9;
    public static final int EVENT_INPUT_SPACING = 6;
    public static final int MAX_TASK = 100;
    private static Task[] listTasks = new Task[MAX_TASK];
    private static int numberTask = 0;

    public static void endProgram(){
        System.out.println(BotText.lineBorder + BotText.messageGoodbye + BotText.lineBorder);
    }

    public static void startProgram(){
        System.out.println(BotText.lineBorder + BotText.messageWelcome + BotText.lineBorder);
    }

    public static void printList(){
        System.out.print(BotText.lineBorder + BotText.messageList);
        for(int i = 0; i < numberTask; i++){
            System.out.println((i+1) + "." + listTasks[i].getTask());
        }
        System.out.println(BotText.lineBorder);
    }

    public static void addToDo(String line){
        String description = line.replace("todo ", "");
        Todo newTodo = new Todo(description);
        listTasks[numberTask] = newTodo;
        numberTask++;
        System.out.println(BotText.lineBorder + "Got it. I've added this task:\n  " + newTodo.getTask());
        System.out.println("Now you have " + numberTask + " tasks in the list.\n" + BotText.lineBorder);
    }

    public static void addDeadline(String line){
        Deadline newDeadline = formatDeadline(line);
        listTasks[numberTask] = newDeadline;
        numberTask++;
        System.out.println(BotText.lineBorder + "Got it. I've added this task:\n  " + newDeadline.getTask());
        System.out.println("Now you have " + numberTask + " tasks in the list.\n" + BotText.lineBorder);
    }

    private static Deadline formatDeadline(String line) {
        // Find the indices of the first and second occurrences of "/"
        int firstSlashIndex = line.indexOf("/");

        // Extract the substrings based on the slash positions
        String description = line.substring(DEADLINE_INPUT_SPACING, firstSlashIndex).trim();
        String deadline = line.substring(firstSlashIndex + 1).trim();

        //add Semicolon behind by
        deadline = deadline.replace("by", "by:");

        return new Deadline(description, deadline);
    }

    public static void addEvent(String line){
        Event newEvent = formatEvent(line);
        listTasks[numberTask] = newEvent;
        numberTask++;
        System.out.println(BotText.lineBorder + "Got it. I've added this task:\n  " + newEvent.getTask());
        System.out.println("Now you have " + numberTask + " tasks in the list.\n" + BotText.lineBorder);
    }
    
    private static Event formatEvent(String line) {
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

        return new Event(description, eventStart, eventEnd);
    }

    public static void markList(int index){
        listTasks[index-1].markTaskAsDone();
        System.out.println(BotText.lineBorder + BotText.messageMarked + "  " + listTasks[index-1].getTask() + "\n" + BotText.lineBorder);
    }

    public static void unmarkList(int index){
        listTasks[index-1].unmarkTaskAsDone();
        System.out.println(BotText.lineBorder + BotText.messageUnmark + "  " + listTasks[index-1].getTask() + "\n" + BotText.lineBorder);
    }

    public static void getInput(){
        String input;
        Scanner in = new Scanner(System.in);
        input = (in.nextLine()).toLowerCase();

        while(!input.equals("bye")){
            printInput(input);
            input = in.nextLine();
        }
    }

    public static void printInput(String input){
        if(input.equals("list")){
            printList();
        }
        else if(input.startsWith("mark")){
            String indexNum = getTaskIndex(input);
            markList(Integer.parseInt(indexNum));
        }
        else if(input.startsWith("unmark")){
            String indexNum = getTaskIndex(input);
            unmarkList(Integer.parseInt(indexNum));
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

    private static String getTaskIndex(String input) {
        String[] words = input.split(" ");
        return words[1];
    }

    public static void main(String[] args) {
        startProgram();
        getInput();
        endProgram();
    }
}
