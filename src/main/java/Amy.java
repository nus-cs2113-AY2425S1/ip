import java.sql.SQLOutput;
import java.util.Scanner;

public class Amy {
    public static final String name = "Amy";
    private static final int MAX_TASKS = 100;
    private static Task[] taskList = new Task[MAX_TASKS];
    private static boolean isExit = false;
    private static int taskNum = 0;
    public static void showTaskList(){
        String line = "Let's try to get this done! You got this (๑•̀ㅂ•́)و✧";
        String noTask = "You don't have anything in your TDL! Take some rest for now (✿◡‿◡)";

        if(taskNum == 0){
            System.out.println(noTask);
        } else{
            System.out.println(line);
        }

        for(int i = 0; i<taskNum; i++){
            System.out.println(String.valueOf(i+1) + '.' + taskList[i]);
        }
    }
    public static void addTaskList(String input){
        taskList[taskNum++] = new Task(input);
        System.out.println("I added <" + input + "> to the todo-list! ☆*: .｡. o(≧▽≦)o .｡.:*☆");
    }
    public static void doGreeting(){
        String greet = "Hello! I'm " + name + " ✿" + "\nWhat can I do for you? (❁´◡`❁) \n";
        System.out.println(greet);
    }
    public static void exit(){
        isExit = true;
        String bye = "Bye. Hope to see you again soon! (*/ω＼*)\n";
        System.out.println(bye);
    }
    public static void filler(){
        String filler = "____________________________________________________________" + "\n";
        System.out.println(filler);
    }
    public static void markTaskDone(int taskNo) {
        if(!isValidTask(taskNo)){
            return;
        }

        String line = "Good job ( •̀ ω •́ )y You have been working hard~";

        taskList[taskNo].markAsDone();
        System.out.println(line);
        System.out.println(taskList[taskNo]);

    }
    public static void markTaskUndone(int taskNo){
        if(!isValidTask(taskNo)){
            return;
        }
        String line = "Okay, let's do this again o(*￣▽￣*)ブ";

        taskList[taskNo].markAdUndone();
        System.out.println(line);
        System.out.println(taskList[taskNo]);
    }
    public static boolean isValidTask(int taskNo){
        if(taskNo < 0 || taskNo >= taskNum){
            System.out.println("I didn't find that task on your list. Wanna try again? (┬┬﹏┬┬)");
            return false;
        } else {
            return true;
        }
    }
    public static String[] requestParser(String request){
        String key = request.split(" ")[0];
        String[] args = new String[100];
        switch(key){
            case "mark", "unmark":
                args[0] = request.split(" ")[1]; //1 argument
                break;
            case "todo":
                args[0] = request.substring(5);
                break;
            case "deadline":
                String[] parts = request.split(" /by ");
                args[0] = parts[0].substring(9);
                args[1] = parts[1];
                break;
            case "event":
                parts = request.split(" /from | /to ");
                args[0] = parts[0].substring(6);
                args[1] = parts[1];
                args[2] = parts[2];
                break;
        }
        return args;
    }
    public static void addDeadline(String[] input){
        taskList[taskNum++] = new Deadline(input[0], input[1]);
        System.out.println("I added <" + taskList[taskNum-1] + "> to the todo-list! ☆*: .｡. o(≧▽≦)o .｡.:*☆");
        System.out.println("Now you have " + taskNum + " tasks in the todolist. Good luck!");
    }
    public static void addEvent(String[] input){
        taskList[taskNum++] = new Event(input[0], input[1], input[2]);
        System.out.println("I added <" + taskList[taskNum-1] + "> to the todo-list! ☆*: .｡. o(≧▽≦)o .｡.:*☆");
        System.out.println("Now you have " + taskNum + " tasks in the todolist. Good luck!");
    }
    public static void addTodo(String[] input){
        taskList[taskNum++] = new Todo(input[0]);
        System.out.println("I added <" + taskList[taskNum-1] + "> to the todo-list! ☆*: .｡. o(≧▽≦)o .｡.:*☆");
        System.out.println("Now you have " + taskNum + " tasks in the todolist. Good luck!");
    }

    public static void chooseAction(String request){
        String key = request.split(" ")[0];
        filler();
        switch(key){
            case "greet" -> doGreeting();
            case "bye" -> exit();
            case "list" -> showTaskList();
            case "mark" -> markTaskDone(Integer.parseInt(requestParser(request)[0]));
            case "unmark" -> markTaskUndone(Integer.parseInt(requestParser(request)[0]));
            case "todo" -> addTodo(requestParser(request));
            case "deadline" -> addDeadline(requestParser(request));
            case "event" -> addEvent(requestParser(request));
            default -> System.out.println("I didn't understand what you meant （；´д｀）ゞ I'm still learning so do try again next time!");
        }
        filler();
    }
    public static void main(String[] args) {
        chooseAction("greet");
        Scanner scanner = new Scanner(System.in);
        while(!isExit){
            String request = scanner.nextLine().trim();
            chooseAction(request);
        }
    }
}
