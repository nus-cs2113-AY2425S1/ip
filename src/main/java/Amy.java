import java.util.ArrayList;
import java.util.Scanner;

public class Amy {
    public static final String name = "Amy";
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static boolean isExit = false;
    public static void showTaskList(){
        String line = "Let's try to get this done! You got this (๑•̀ㅂ•́)و✧";
        String noTask = "You don't have anything in your TDL! Take some rest for now (✿◡‿◡)";

        if(taskList.isEmpty()){
            System.out.println(noTask);
        } else{
            System.out.println(line);
        }

        for(int i = 0; i< taskList.size(); i++){
            System.out.println(String.valueOf(i+1) + '.' + taskList.get(i));
        }
    }
    public static void addTaskList(Task task){
        taskList.add(task);
        System.out.println("I added <" + task + "> to the todo-list! ☆*: .｡. o(≧▽≦)o .｡.:*☆");
        System.out.println("Now you have " + taskList.size() + " tasks in the todolist. Good luck!");
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

        taskList.get(taskNo).markAsDone();
        System.out.println(line);
        System.out.println(taskList.get(taskNo));

    }
    public static void markTaskUndone(int taskNo){
        if(!isValidTask(taskNo)){
            return;
        }
        String line = "Okay, let's do this again o(*￣▽￣*)ブ";

        taskList.get(taskNo).markAdUndone();
        System.out.println(line);
        System.out.println(taskList.get(taskNo));
    }

    /***
     * Check for out of bounds task numbers.
     * @param taskNo task number
     * @return if task number is valid or not
     */
    public static boolean isValidTask(int taskNo){
        if(taskNo < 0 || taskNo >= taskList.size()){
            System.out.println("I didn't find that task on your list. Wanna try again? (┬┬﹏┬┬)");
            return false;
        } else {
            return true;
        }
    }
    public static String[] requestParser(String request){
        String key = request.split(" ")[0];
        String[] args = new String[100];
        String[] parts;
        switch(key){
            case "mark", "unmark":
                args[0] = request.split(" ")[1]; //1 argument
                break;
            case "todo":
                args[0] = request.substring(5);
                break;
            case "deadline":
                parts = request.split(" /by ");
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
        addTaskList(new Deadline(input[0], input[1]));
    }
    public static void addEvent(String[] input){
        addTaskList(new Event(input[0], input[1], input[2]));
    }
    public static void addTodo(String[] input){
        addTaskList(new Todo(input[0]));
    }

    public static void chooseAction(String request){
        String key = request.split(" ")[0];
        filler();
        switch(key){
            case "greet" -> doGreeting();
            case "bye" -> exit();
            case "list" -> showTaskList();
            case "mark" -> markTaskDone(Integer.parseInt(requestParser(request)[0]) - 1);
            case "unmark" -> markTaskUndone(Integer.parseInt(requestParser(request)[0]) - 1);
            case "todo" -> addTodo(requestParser(request));
            case "deadline" -> addDeadline(requestParser(request));
            case "event" -> addEvent(requestParser(request));
            default -> System.out.println("I didn't understand what you meant （；´д｀）ゞ I'm still learning so do try again next time!");
        }
        filler();
    }
    public static void main(String[] args) {
        chooseAction("greet");
        taskList = TaskManager.loadTasks();
        Scanner scanner = new Scanner(System.in);
        while(!isExit){
            String request = scanner.nextLine().trim();
            chooseAction(request);
        }
        TaskManager.saveTasks(taskList);

    }
}
