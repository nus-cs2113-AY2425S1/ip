package TheThinker.Tasks;

import TheThinker.Parser.Date;

import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> listOfTasks = new ArrayList<>();
    public static int listLength = 0;

    public static void addTask(Task task){
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + task);
        listOfTasks.add(task);
        listLength++;
        System.out.printf("Now you have %d tasks in the list.\n" , listLength);
    }

    public static void addTaskWithoutResponse(Task task){
        listOfTasks.add(task);
        listLength++;
    }

    public static void deleteTask(int taskNumber){
        System.out.println("Noted. I've removed this task:");
        Task taskToRemove = listOfTasks.get(taskNumber-1);
        listLength--;
        listOfTasks.remove(taskNumber-1);
        System.out.println(taskToRemove);
        System.out.printf("Now you have %d tasks in the list.\n" , listLength);
    }

    public static void setAsDone(int listNumber){
        Task currentTask = listOfTasks.get(listNumber-1);
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("  [%c][X] " + currentTask.getTaskDescription() + "\n" , currentTask.getTaskType());
        currentTask.setMarkedAsDone(true);
    }

    public static void setAsNotDone(int listNumber){
        Task currentTask = listOfTasks.get(listNumber-1);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("  [%c][ ] " + currentTask.getTaskDescription() + "\n" , currentTask.getTaskType());
        currentTask.setMarkedAsDone(false);
    }

    public static void listTasks(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < listLength; i++){
            System.out.printf("%d." + listOfTasks.get(i) + "\n", i+1);
        }
    }

    public static void listTasksOfDate(String date){
        System.out.println("Here are the tasks in your list in " + date + " :");
        int counter = 0;
        for(int i = 0; i < listLength; i++){
            if(Date.isMatchingDateByType(listOfTasks.get(i) , date)) {
                System.out.printf("%d." + listOfTasks.get(i) + "\n", counter + 1);
                counter++;
            }
        }
    }

    public static void listTasksOfKeyword(String keyword){
        System.out.println("Here are the matching tasks in your list:");
        int counter = 0;
        for(int i = 0; i < listLength; i++){
            if(listOfTasks.get(i).taskDescription.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.printf("%d." + listOfTasks.get(i) + "\n", counter + 1);
                counter++;
            }
        }
    }
}
