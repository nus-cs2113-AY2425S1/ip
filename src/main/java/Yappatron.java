import java.util.Scanner;

public class Yappatron {
    public static final int MAX_TASKS = 100;
    public static int taskNumber = 0;
    public static Task[] taskArray = new Task[MAX_TASKS];
    public static void main(String[] args) {
        System.out.println("Hello! I'm Yappatron");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String input;
        int exitStatus = 0;
        Taskmanager taskManager = new Taskmanager();
        do {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")){
                exitStatus = 1;
            }
            else if (input.startsWith("mark")){
                taskManager.mark(input);
            }
            else if (input.startsWith("unmark")){
                taskManager.unmark(input);
            }
            else if (input.equalsIgnoreCase("list")) {
                taskManager.list();
            }
            else{
                System.out.println("added: " + input);
                if (input.startsWith("todo")){
                    taskManager.addTodo(input);
                }
                else if (input.startsWith("deadline")){
                    taskManager.addDeadline(input);
                }
                else{
                    taskManager.addEvent(input);
                }
            }
        }while (exitStatus==0);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
