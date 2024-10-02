package apsea.ui;

import apsea.task.Task;
import apsea.task.TaskList;

import java.util.Scanner;

/**
 * Displays messages to user.
 */
public class Ui {
    private final String SEPARATOR = "\t______________________________________________________________________________";
    private final String HELLO_MESSAGE = "\tHello! I'm Apsea!\n" + "\tWhat can I do for you?";
    public final String BYE_MESSAGE = "\tBye. Hope to see you again soon";

    public Ui(){
    }

    public void printLine() {
        System.out.println(SEPARATOR);
    }

    public void printHello() {
        printLine();
        System.out.println(HELLO_MESSAGE);
        printLine();
    }

    public void printBye() {
        System.out.println(BYE_MESSAGE);
    }

    public void printTasks(TaskList taskList) {
        for (int i = 0; i < taskList.getTotal(); i++) {
            System.out.println("\t" + (i+1) + ". " + taskList.getTask(i));
        }
    }

    public void printAddTask(TaskList taskList) {
        System.out.println("\tI've added this task to the list:");
        System.out.println("\t" + taskList.getTask(taskList.getTotal()-1));
    }

    public void printTotalTaskCount(TaskList taskList) {
        System.out.println("\tNow you have " + taskList.getTotal() + " task(s) in the list");
    }

    public void printMarkTask(TaskList taskList, int taskIndex) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + taskList.getTask(taskIndex));
    }

    public void printUnmarkTask(TaskList taskList, int taskIndex) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + taskList.getTask(taskIndex));
    }

    public void printDeleteTask(Task deletedTask) {
        System.out.println("\tOK, I've deleted this task:");
        System.out.println("\t" + deletedTask);
    }

    public void printFindMatch(Task task, int taskNumber) {
        System.out.println("\t" + (taskNumber) + ". " + task);
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
