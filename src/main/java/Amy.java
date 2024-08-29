import java.util.Scanner;

public class Amy {
    private static final int MAX_TASKS = 100;
    private static Task[] taskList = new Task[MAX_TASKS];
    private static int taskNum = 0;
    public static void doEcho(String line){
        System.out.println(line + "! I wonder what I can do with this information (●'◡'●)");
    }
    public static void getTaskList(){
        String line = "Let's try to get this done! You got this (๑•̀ㅂ•́)و✧";
        String noTask = "You don't have anything in your TDL! Take some rest for now (✿◡‿◡)";

        if(taskNum == 0) System.out.println(noTask);
        else    System.out.println(line);

        for(int i = 0; i<taskNum; i++){
            System.out.println(String.valueOf(i+1) + '.' + taskList[i]);
        }
    }
    public static void addTaskList(String input){
        taskList[taskNum++] = new Task(input);
        System.out.println("I added <" + input + "> to the todo-list! ☆*: .｡. o(≧▽≦)o .｡.:*☆");
    }
    public static void doGreeting(){
        String name = "Amy";
        String greet = "Hello! I'm " + name + " ✿" + "\nWhat can I do for you? (❁´◡`❁) \n";
        System.out.println(greet);
    }
    public static void exit(){
        String bye = "Bye. Hope to see you again soon! (*/ω＼*)\n";
        System.out.println(bye);
    }
    public static void filler(){
        String filler = "____________________________________________________________" + "\n";
        System.out.println(filler);
    }
    public static void markTaskDone(int taskNo){
        String line = "Good job ( •̀ ω •́ )y You have been working hard~";

        taskList[taskNo].markAsDone();
        System.out.println(line);
        System.out.println(taskList[taskNo]);

    }
    public static void markTaskUndone(int taskNo){
        String line = "Okay, let's do this again o(*￣▽￣*)ブ";

        taskList[taskNo].markAdUndone();
        System.out.println(line);
        System.out.println(taskList[taskNo]);
    }
    public static void main(String[] args) {
        filler();
        doGreeting();
        filler();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while(!line.equals("bye")){
            if(line.equals("list")){
                getTaskList();
            }
            else if(line.startsWith("mark") || line.startsWith("unmark")){

                int taskNo = Integer.parseInt(line.split(" ")[1]) - 1;
                if(taskNo < 0 || taskNo >= taskNum)  System.out.println("I didn't find that task on your list. Wanna try again? (┬┬﹏┬┬)");
                else{
//                try {
                    if (line.startsWith("mark"))    markTaskDone(taskNo);
                    else markTaskUndone(taskNo);

                }
//                catch(ArrayIndexOutOfBoundsException e){
//                    System.out.println("I didn't find that task on your list. Wanna try again? (┬┬﹏┬┬)");
//                }

            }
//            else if(line.startsWith("unmark")){
//                int taskNo = Integer.parseInt(line.split(" ")[1]) - 1;
//                if(taskNo > taskNum){
//                    System.out.println("I didn't find that task on your list. Wanna try again? (┬┬﹏┬┬)");
//                }
//                else{
//                    markTaskUndone(taskNo);
//                }
//            }
            else {
                addTaskList(line);
            }
            filler();
            line = in.nextLine().trim();
        }
        exit();
    }
}
