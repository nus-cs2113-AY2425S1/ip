import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Cassandra {

    private static void line(){
        System.out.println("____________________________________________________________");
    }

    private static void intro(){
        line();
        System.out.println(" Hello! I'm Cassandra");
        System.out.println(" What can I do for you?");
        line();
    }

    private static void exit(){
        System.out.println(" Bye. Hope to see you again soon!");
        line();
    }

    private static void saveTask(ArrayList<Task> taskList,Task input){
        taskList.add(input);
        System.out.println(" Added : "+ input.getTaskName());
        line();
    }

    private static void markTask(ArrayList<Task> taskList,int index){
        if(index<=0 || index>taskList.size()) {
            System.out.println("Sorry, no task found");
        } else if(taskList.get(index-1).getIsCompleted()){
            System.out.println("Task has already been marked complete");
        } else {
            taskList.get(index - 1).setCompleted(true);
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println(" " + taskList.get(index - 1).printTask());
        }
        line();
    }

    private static void unmarkTask(ArrayList<Task> taskList,int index){
        if(index<=0 || index>taskList.size()) {
            System.out.println("Sorry, no task found");
        } else if(!taskList.get(index-1).getIsCompleted()){
            System.out.println("Task has already been marked incomplete");
        } else {
            taskList.get(index - 1).setCompleted(false);
            System.out.println(" OK, I've marked this task as not done yet: ");
            System.out.println(" " + taskList.get(index - 1).printTask());
        }
        line();
    }

    private static void printtaskList(ArrayList<Task> taskList){
        if(taskList.isEmpty()){
            System.out.println("List is empty");
            return;
        }
        System.out.println("Here are the tasks in your list: :");
        for(int i=0;i<taskList.size();i++){
            System.out.println((i+1) + ". "+taskList.get(i).printTask());
        }
        line();
    }

    private  static void input(ArrayList<Task> taskList) {
        String input = new Scanner(System.in).nextLine().trim();
        String code = input.split(" ")[0];
        line();
        if(code.equals("mark")){
            markTask(taskList,Integer.parseInt(input.substring(input.indexOf(" ")+1)));
        } else if(code.equals("unmark")){
            unmarkTask(taskList,Integer.parseInt(input.substring(input.indexOf(" ")+1)));
        } else if(code.equals("list")){
            printtaskList(taskList);
        } else if(code.equals("bye")){
            return;
        }else {
            saveTask(taskList, new Task(input));
        }
        input(taskList);
    }

    public static void main(String[] args) {
        intro();
        ArrayList<Task> taskList = new ArrayList<Task>();
        input(taskList);
        exit();
    }
}
