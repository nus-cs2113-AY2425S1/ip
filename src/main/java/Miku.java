import java.util.Scanner;
import java.util.ArrayList;

public class Miku {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TaskList taskList = new TaskList();

        printGreeting();

        String line = input.nextLine();
        printDivider();

        while(!line.equals("bye")){

            if (line.equals("list")){
                taskList.printTaskList();
            }
            else if(line.startsWith("mark")){
                taskList.attemptToMarkTask(line);
            }
            else if(line.startsWith("unmark")){
                taskList.attemptToUnmarkTask(line);
            }
            else{
                taskList.addTask(line);
            }

            printDivider();
            line = input.nextLine();
            printDivider();
        }

        System.out.println("Bye, see you later!");
        printDivider();

        input.close();
    }

    public static void printGreeting(){
        String logo =
            """
             __  __   _   _           \s
            |  \\/  | (_) | |          \s
            | \\  / |  _  | | __  _   _\s
            | |\\/| | | | | |/ / | | | |
            | |  | | | | |   <  | |_| |
            |_|  |_| |_| |_|\\_\\  \\__,_|
            """;
        //Text to Ascii generated through https://patorjk.com/software/taag/
        System.out.println(logo);

        printDivider();
        System.out.println("Hello! I'm Miku\nWhat can I do for you?");
        printDivider();
    }

    public static void printDivider(){
        System.out.println("____________________________________________________________");
    }

    public static class TaskList{
        private Task[] list;
        private int size;

        public TaskList(){
            list = new Task[100]; // Constraint given that maximum number of tasks is 100
            size=0;
        }

        public void addTask(String line){
            System.out.println("I've added this to your list: "+line);
            Task newTask = new Task(line);
            list[size]=newTask;
            size++;
        }

        public void printTaskList(){
            System.out.println("Here is your list of tasks:");
            for (int i=1;i<=size;i++){
                System.out.printf("%d. ",i);
                list[i-1].printTask();
            }
        }

        public void attemptToMarkTask(String line){
            try{
                if (line.charAt(4) == ' '){
                    int index = Integer.parseInt(line.substring(5));
                    list[index-1].markAsDone();
                }
            }catch (Exception e){
                //Treat invalid command as a task
                addTask(line);
            }
        }

        public void attemptToUnmarkTask(String line){
            try{
                if (line.charAt(6) == ' '){
                    int index = Integer.parseInt(line.substring(7));
                    list[index-1].markAsNotDone();
                }
            }catch (Exception e){
                //Treat invalid command as a task
                addTask(line);
            }
        }
    }

    public static class Task{
        private boolean done;
        private String taskInfo;

        public Task(String taskInfo){
            this.taskInfo = taskInfo;
            done = false;
        }

        public boolean getStatus(){
            return done;
        }

        public String getInfo(){
            return taskInfo;
        }

        public void markAsDone(){
            done=true;
            System.out.println("Nice! I've marked this task as done:");
            printTask();
        }

        public void markAsNotDone(){
            done=false;
            System.out.println("OK, I've marked this task as not done yet:");
            printTask();
        }

        public void printTask(){
            System.out.printf("[%s] %s%n",done?"X":" ",taskInfo);
        }
    }
}
