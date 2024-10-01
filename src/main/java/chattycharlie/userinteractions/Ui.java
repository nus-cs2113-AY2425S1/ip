package chattycharlie.userinteractions;

import chattycharlie.TaskList;
import chattycharlie.task.Task;

import java.util.Scanner;

public class Ui {

    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print(StringDesign.YOU);
        String userInput = scanner.nextLine();
        System.out.println(StringDesign.LINE);
        return userInput;
    }

    public void displayGreetings(){
        System.out.println(StringDesign.LOGO + StringDesign.CHARLIE + StringDesign.GREETING);
    }

    public void displayTaskAdded(Task task) {
        System.out.println(StringDesign.SPACE + "Added task: " + task + System.lineSeparator() + StringDesign.LINE);
    }

    public void displayTask(Task task) {
        System.out.println(StringDesign.SPACE + task + System.lineSeparator() + StringDesign.LINE);
    }

    public void displayList(TaskList list) {
        list.printList();
    }

    public void displayTaskInList(Task task, int number) {
        System.out.println(StringDesign.SPACE + number +". " + task);
    }

    public void displayError(String error) {
        System.out.println(error + System.lineSeparator() + StringDesign.LINE);
    }

    public void displayMarkingText(String line, int remainingTask) {
        System.out.println(StringDesign.SPACE + line  + remainingTask + " to go!");
    }

    public void displayDeletedTask(String line, int remainingTask) {
        System.out.println(StringDesign.SPACE + line + remainingTask + " to go!");
    }

    public void displayListHeader(int remainingTask) {
        System.out.println("Task List:");
        System.out.println("pending Task: " + remainingTask);
    }

    public void displayLine() {
        System.out.println(StringDesign.LINE);
    }

    public void displaySearchList() {
        System.out.println("Tasks found:");
    }
}
