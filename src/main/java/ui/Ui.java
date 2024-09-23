package ui;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;

import java.time.LocalDate;
import java.util.Scanner;

import static constants.Message.ADD_TASK_SUCCESS_MESSAGE;
import static constants.Message.DELETE_TASK_SUCCESS_MESSAGE;
import static constants.Message.EXISTING_TASKS_MESSAGE;
import static constants.Message.GREETING_MESSAGE;
import static constants.Message.LINE_MESSAGE;
import static constants.Message.LOGO;
import static constants.Message.MARKED_MESSAGE;
import static constants.Message.NO_TASK_OF_INTEREST_MESSAGE;
import static constants.Message.SAVE_TASK_LIST_SUCCESS_MESSAGE;
import static constants.Message.SAYONARA_MESSAGE;
import static constants.Message.TASKS_OF_INTEREST_MESSAGE;
import static constants.Message.UNMARKED_MESSAGE;

public class Ui {
    private Scanner inputScanner;
    public Ui() {
        inputScanner = new Scanner(System.in);
    }
    public void printLogo() {
        System.out.print(LOGO);
    }
    public void printLine() {
        System.out.println(LINE_MESSAGE);
    }

    public void sayKonichiwa() {
        printLine();
        printLogo();
        System.out.println(GREETING_MESSAGE);
        printLine();
    }

    public void saySayonara() {
        printLine();
        System.out.println(SAYONARA_MESSAGE);
        printLine();
    }

    public String getUserInput() {
        return inputScanner.nextLine();
    }

    public void printAddTaskSuccessMessage(String task, TaskList tasks) {
        printLine();
        System.out.printf("%s\n\t\t%s\n%s", ADD_TASK_SUCCESS_MESSAGE, task, getTaskCountMessage(tasks));
        printLine();
    }

    public void printSaveTaskListSuccessMessage() {
        printLine();
        System.out.println(SAVE_TASK_LIST_SUCCESS_MESSAGE);
        printLine();
    }

    public void listTasks(TaskList tasks) {
        printLine();
        System.out.println(EXISTING_TASKS_MESSAGE);
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, tasks.retrieveTask(i));
        }
        printLine();
    }

    public void listTasksOfInterest(LocalDate dateOfInterest, TaskList tasks) {
        printLine();
        int currentTask = 0;
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task task = tasks.retrieveTask(i);
            if (isDeadlineOfInterest(dateOfInterest, task) || isEventOfInterest(dateOfInterest, task)) {
                if (currentTask == 0) {
                    System.out.println(TASKS_OF_INTEREST_MESSAGE);
                }
                System.out.printf("\t%d. %s\n", currentTask + 1, task);
                currentTask++;
            }
        }
        if (currentTask == 0) {
            System.out.println(NO_TASK_OF_INTEREST_MESSAGE);
        }
        printLine();
    }

    public void listTasksOfInterest(TaskList tasks) {
        printLine();
        System.out.println(TASKS_OF_INTEREST_MESSAGE);
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, tasks.retrieveTask(i));
        }
        printLine();
    }

    public boolean isEventOfInterest(LocalDate dateOfInterest, Task task) {
        return task instanceof Event && (((Event) task).getFrom().equals(dateOfInterest) || ((Event) task).getTo().equals(dateOfInterest));
    }

    public boolean isDeadlineOfInterest(LocalDate dateOfInterest, Task task) {
        return task instanceof Deadline && ((Deadline) task).getBy().equals(dateOfInterest);
    }

    public void printUnmarked(TaskList tasks, int index) {
        System.out.println(UNMARKED_MESSAGE);
        System.out.printf("\t\t%s\n", tasks.retrieveTask(index));
    }

    public void printMarked(TaskList tasks, int index) {
        System.out.println(MARKED_MESSAGE);
        System.out.printf("\t\t%s\n", tasks.retrieveTask(index));
    }

    public void printMarkUpdate(TaskList tasks, boolean isDone, int index) {
        printLine();
        if (isDone) {
            printMarked(tasks, index);
        } else {
            printUnmarked(tasks, index);
        }
        printLine();
    }

    public void printDeleteTaskSuccessMessage(Task task, TaskList tasks) {
        printLine();
        System.out.printf("%s\n\t\t%s\n%s", DELETE_TASK_SUCCESS_MESSAGE, task, getTaskCountMessage(tasks));
        printLine();
    }

    public String getTaskCountMessage(TaskList tasks) {
        return String.format("\tYou currently have %d tasks! Way to go, you busy bee!\n", tasks.getTaskCount());
    }

    public void displayErrorMessage(String errorMessage) {
        System.out.print(errorMessage);
    }
}
