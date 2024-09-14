package TheThinker.Tasks;

import java.util.ArrayList;

public class Task {
    public String taskDescription;
    public boolean isMarkedAsDone;
    public char taskType;
    public static ArrayList<Task> listOfTasks = new ArrayList<>();
    public static int listLength = 0;

    public Task(String taskDescription, char taskType) {
        this.taskDescription = taskDescription;
        this.isMarkedAsDone = false;
        this.taskType = taskType;
    }

    public void setMarkedAsDone(boolean markedAsDone) {
        isMarkedAsDone = markedAsDone;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public char getTaskType() {
        return taskType;
    }

    public String toString() {
        char maskingChar = isMarkedAsDone ? 'X' : ' ';
        return " [" + taskType + "][" + maskingChar + "] " + taskDescription;
    }

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
        System.out.println("Here are the tasks in your list:"); /* to add the individual to_string*/
        for(int i = 0; i < listLength; i++){
            System.out.printf("%d." + listOfTasks.get(i) + "\n", i+1);
        }
    }

    public String convertToFileFormat(){
        return "NIL" + " | " + isMarkedAsDone + " | " + taskDescription;
    }
}
