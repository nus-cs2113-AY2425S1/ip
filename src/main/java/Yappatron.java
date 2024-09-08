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
                if (input.startsWith("todo")){
                    try {
                        taskManager.addTodo(input);
                        System.out.println("added: " + input);
                    }catch (EmptyTaskEntry e){
                        System.out.println(e.getMessage());
                    }
                }
                else if (input.startsWith("deadline")){
                    try {
                        taskManager.addDeadline(input);
                        System.out.println("added: " + input);
                    }catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please key in valid deadline, include 'by' in string.");
                    }catch (EmptyTaskEntry e){
                        System.out.println(e.getMessage());
                    }
                }
                else if (input.startsWith("event")){
                    try {
                        taskManager.addEvent(input);
                        System.out.println("added: " + input);
                    }catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please key in valid event. Include from and to in string.");
                    }catch (EmptyTaskEntry e){
                        System.out.println(e.getMessage());
                    }
                }
                else{
                    System.out.println("I do not understand what that means!");
                }
            }
        }while (exitStatus==0);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
