import java.util.Scanner;
import java.util.ArrayList;

public class Edith {
    Scanner sc=new Scanner(System.in);
    private static final String myName="Edith";
    //private final static String horizontalLine="______________________________________________";
    ArrayList<Task> tasks= new ArrayList<>();

    public void printHorizontalLine() {
        System.out.println("______________________________________________");
    }

    public boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber >= 1 && taskNumber <= tasks.size());
    }

    private char getIsDoneCharacter(boolean isDone) {
        if (isDone) return 'X';
        else return ' ';
    }

    public String printTask(Task task) {
        return "[" + getIsDoneCharacter(task.getIsDone()) + "] " + task.getDescription();
    }

    public void giveIntroduction() {
        System.out.println("Hello I am " + myName + ".");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list: ");
        int serialNumber=0;
        for(Task task : tasks) {
            serialNumber++;
            System.out.println(serialNumber + "." + printTask(task));
        }
        printHorizontalLine();
    }


    public void changeTaskStatus(String enteredString) {
        int taskNumber= Integer.parseInt(enteredString.substring(enteredString.length()-1));
        if(!isValidTaskNumber(taskNumber)) {
            System.out.println("Invalid task number. Please try again.");
            printHorizontalLine();
            return;
        }
        Task currentTask = tasks.get(taskNumber-1);
        if(enteredString.contains("unmark")) {
            currentTask.setIsDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(printTask(currentTask));
        } else if(enteredString.contains("mark")) {
            currentTask.setIsDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(printTask(currentTask));
        }
        printHorizontalLine();
    }

    public void addNewTask(String enteredString) {
        Task newTask= new Task(enteredString);
        tasks.add(newTask);
        System.out.println("added : " + newTask.getDescription());
        printHorizontalLine();
    }
    public void talkToUser() {
        giveIntroduction();
        do {
            String enteredString= sc.nextLine();
            printHorizontalLine();
            if(enteredString.equals("bye")) {
                sayGoodbye();
                break;
            } else if(enteredString.equals("list")) {
                listTasks();
            } else if(enteredString.contains("mark")) {
                changeTaskStatus(enteredString);
            } else {
                addNewTask(enteredString);
            }
        } while (true);
    }

    public static void main(String[] args) {
        Edith obj = new Edith();
        obj.talkToUser();
    }
}
