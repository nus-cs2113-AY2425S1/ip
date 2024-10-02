package UserInteraction;

import TaskTypes.Task;
import java.util.ArrayList;
import java.util.Scanner;
import UserInteraction.ChangeTaskStatus;

import static UserInteraction.ChangeTaskStatus.changeTaskStatus;
import static UserInteraction.PrintShape.printHorizontalLine;

public class ChatBot {
    private final String MY_NAME;
    ArrayList<Task> tasks;

    public ChatBot(String MY_NAME) {
        this.MY_NAME = MY_NAME;
        tasks = new ArrayList<>();
    }

    public void interactWithUser() {
        Scanner sc = new Scanner(System.in);
        giveIntroduction();
        do {
            String enteredString = sc.nextLine();
            printHorizontalLine();
            if (enteredString.equals("bye")) {
                sayGoodbye();
                break;
            } else if (enteredString.equals("list")) {
                listTasks();
            } else if (enteredString.contains("mark")) {
                changeTaskStatus(tasks, enteredString);
            } else {
                Task newTask = CreateTask.addNewTask(enteredString);
                if (newTask == null) {
                    printHorizontalLine();
                    break;
                }
                tasks.add(newTask);
                System.out.println("Got it. I've added this task: ");
                System.out.println(newTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list");
                printHorizontalLine();
            }
        } while (true);
    }

    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list: ");
        int serialNumber = 0;
        for(Task task : tasks) {
            serialNumber++;
            System.out.println(serialNumber + "." + task.toString());
        }
        printHorizontalLine();
    }
    public void giveIntroduction() {
        System.out.println("Hello I am " + MY_NAME + ".");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }


}
