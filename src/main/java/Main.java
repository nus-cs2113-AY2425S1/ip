import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    Scanner sc=new Scanner(System.in);
    private final String myName;
    ArrayList<Task> tasks;

    public Main(String myName) {
        this.myName = myName;
        tasks=new ArrayList<>();
    }

    public void printHorizontalLine() {
        System.out.println("______________________________________________");
    }

    public boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber >= 1 && taskNumber <= tasks.size());
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
            System.out.println(serialNumber + "." + task.toString());
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
            unmarkTask(currentTask);
        } else if(enteredString.contains("mark")) {
            markTask(currentTask);
        }
        printHorizontalLine();
    }

    public void unmarkTask(Task task) {
        task.setIsDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }
    public void markTask(Task task) {
        task.setIsDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public Deadlines createDeadlineTask(String enteredString) {
        String taskDescription=enteredString.substring("deadline".length(), enteredString.indexOf('/')-1);
        String deadlineTime=enteredString.substring(enteredString.indexOf("/by")+"/by".length()+1);
        return new Deadlines(taskDescription, deadlineTime);
    }

    public Events createEventTask(String enteredString) {
        String taskDescription=enteredString.substring("event".length(), enteredString.indexOf('/')-1);
        String eventFromTime=enteredString.substring(enteredString.indexOf("/from")+"/from".length()+1, enteredString.indexOf("/to"));
        String eventToTime=enteredString.substring(enteredString.indexOf("/to")+"/to".length()+1);
        return new Events(taskDescription, eventFromTime, eventToTime);
    }

    public ToDos createTodoTask(String enteredString) {
        String taskDescription=enteredString.substring("todo".length());
        return new ToDos(taskDescription);
    }
    public void addNewTask(String enteredString) {
        Task newTask;

        if(enteredString.startsWith("deadline")) {
            newTask=createDeadlineTask(enteredString);
        } else if(enteredString.startsWith("event")) {
            newTask=createEventTask(enteredString);
        } else if(enteredString.startsWith("todo")) {
            newTask=createTodoTask(enteredString);
        } else {
            System.out.println("Invalid task type. Please try again.");
            printHorizontalLine();
            return;
        }

        tasks.add(newTask);
        System.out.println("Got it. I've added this task: ");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
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
        Main Edith = new Main("Edith");
        Edith.talkToUser();
    }
}
