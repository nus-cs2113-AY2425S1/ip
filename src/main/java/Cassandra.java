import java.util.ArrayList;
import java.util.Scanner;

public class Cassandra {

    private final static ArrayList<Task> taskList = new ArrayList<>();
    private static boolean EXIT_FLAG = false;

    private static void drawLine(){
        System.out.println("____________________________________________________________");
    }

    private static void exit() {
        EXIT_FLAG = true;
        System.out.println(" Bye. Hope to see you again soon!");
    }

    private static void displayIntroduction(){
        drawLine();
        System.out.println(" Hello! I'm Cassandra");
        System.out.println(" What can I do for you?");
        drawLine();
    }

    private static void saveTask(Task input){
        taskList.add(input);
        System.out.println("Got it. I've added this task: \n "+ input.toString());
        System.out.println("Now you have "+taskList.size()+" tasks in the list.");
    }

    private static void markTask(int index){
        if(index<=0 || index>taskList.size()) {
            System.out.println("Sorry, no task found");
        } else if(taskList.get(index-1).getIsCompleted()){
            System.out.println("Task has already been marked complete");
        } else {
            taskList.get(index - 1).setCompleted(true);
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println(" " + taskList.get(index - 1).toString());
        }
    }

    private static void unmarkTask(int index){
        if(index<=0 || index>taskList.size()) {
            System.out.println("Sorry, no task found");
        } else if(!taskList.get(index-1).getIsCompleted()){
            System.out.println("Task has already been marked incomplete");
        } else {
            taskList.get(index - 1).setCompleted(false);
            System.out.println(" OK, I've marked this task as not done yet: ");
            System.out.println(" " + taskList.get(index - 1).toString());
        }
    }

    private static void printList(){
        if(taskList.isEmpty()){
            System.out.println("List is empty");
        } else {
            System.out.println("Here are the tasks in your list: :");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i).toString());
            }
        }
    }

    private static void executeCommand(String input, String[] commandArgs){
        if(commandArgs[0].equalsIgnoreCase("mark")){
            markTask(Integer.parseInt(commandArgs[1]));
        } else if(commandArgs[0].equalsIgnoreCase("unmark")){
            unmarkTask(Integer.parseInt(commandArgs[1]));
        } else if(commandArgs[0].equalsIgnoreCase("list")){
            printList();
        } else if(commandArgs[0].equalsIgnoreCase("bye")){
            exit();
        } else if(commandArgs[0].equalsIgnoreCase("todo")) {
            String taskName = input.substring(4).trim();
            saveTask(new Todo(taskName));
        } else if(commandArgs[0].equalsIgnoreCase("deadline")) {
            int byIndex = input.indexOf("/by");
            String by = input.substring(byIndex+4).trim();
            String taskName = input.substring(0,byIndex).trim();
            saveTask(new Deadline(taskName,by));
        } else if(commandArgs[0].equalsIgnoreCase("event")) {
            int fromIndex = input.indexOf("/from")+6;
            int toIndex = input.indexOf("/to");
            String from = input.substring(fromIndex,toIndex).trim();
            String to = from.substring(toIndex+4).trim();
            String taskName = input.substring(0,fromIndex).trim();
            saveTask(new Event(taskName,from,to));
        } else{
            System.out.println("Sorry, unknown command");
        }
    }

    private  static void readUserCommand() {
        String input = new Scanner(System.in).nextLine().trim();
        String[] commandArgs = input.split(" ");
        drawLine();
        executeCommand(input,commandArgs);
        drawLine();
    }

    public static void main(String[] args) {
        displayIntroduction();
        while(!EXIT_FLAG) {
            readUserCommand();
        }
    }
}
