package ui;

import exception.IncompleteCommandException;
import tasklist.TaskList;

import java.util.Arrays;
import java.util.Scanner;
import static main.Sirius.*;

public class Ui {
    private final Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in); // stores the input of the user
    }

    // print greeting messages
    public void sayHello(){
        System.out.println(SEPARATOR);
        System.out.println(WELCOME_MESSAGE);
        System.out.println(SEPARATOR);
    }
    public void sayGoodbye(){
        System.out.println(SEPARATOR);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(SEPARATOR);
    }

    // print error messages
    public void showLoadingError(){
        System.out.println(SEPARATOR);
        System.out.println(LOADING_ERROR_MESSAGE);
        System.out.println(SEPARATOR);
    }
    public void showSavingError(){
        System.out.println(SEPARATOR);
        System.out.println(SAVING_ERROR_MESSAGE);
        System.out.println(SEPARATOR);
    }
    public void showIndexOutOfBoundError(){
        System.out.println(SEPARATOR);
        System.out.println(INDEX_BOUND_ERROR_MESSAGE);
        System.out.println(SEPARATOR);
    }
    public void showNumberFormatError(){
        System.out.println(SEPARATOR);
        System.out.println(INDEX_FORMAT_ERROR_MESSAGE);
        System.out.println(SEPARATOR);
    }
    public void showIncompleteCommandError(IncompleteCommandException e){
        System.out.println(SEPARATOR);
        System.out.println(e.getMessage());
        System.out.println(SEPARATOR);
    }

    // print successful messages
    public void showTaskAdded(TaskList tasks, int size){
        System.out.println(SEPARATOR);
        System.out.println(ADD_TASK_MESSAGE);
        System.out.println(tasks.getList().get(size-1).toString());
    }
    public void showTaskDeleted(TaskList tasks, int size, int taskNumber){
        System.out.println(SEPARATOR);
        System.out.println(DELETE_TASK_MESSAGE);
        System.out.println(tasks.getList().get(taskNumber - 1).toString());
    }
    public void showCurrentSizeOfList(TaskList tasks){
        System.out.println("Now you have " + tasks.getListSize() + " tasks in the list.");
        System.out.println(SEPARATOR);
    }
    public void showTaskMarked(TaskList tasks, int taskNumber){
        System.out.println(SEPARATOR);
        System.out.println(MARK_TASK_MESSAGE);
        System.out.println(tasks.getList().get(taskNumber - 1).toString());
        System.out.println(SEPARATOR);
    }
    public void showTaskUnmarked(TaskList tasks, int taskNumber){
        System.out.println(SEPARATOR);
        System.out.println(UNMARK_TASK_MESSAGE);
        System.out.println(tasks.getList().get(taskNumber - 1).toString());
        System.out.println(SEPARATOR);
    }
    public void showTaskListed(TaskList tasks){
        System.out.println(SEPARATOR);
        System.out.println(LIST_TASK_MESSAGE);
        for (int i = 0; i < tasks.getList().size(); i++) {
            System.out.println(i + 1 + ". " + tasks.getList().get(i).toString());
        }
        System.out.println(SEPARATOR);
    }
    public void showTaskFound(TaskList tasks, String keyWord) {
        boolean found = false;  // 用于标记是否找到了匹配项
        System.out.println(SEPARATOR);
        for (int i = 0; i < tasks.getList().size(); i++) {
            String taskName = tasks.getList().get(i).getTaskName();
            String[] components = taskName.split(SPACE);

            if (Arrays.asList(components).contains(keyWord)) {
                if (!found) {
                    System.out.println(FIND_TASK_MESSAGE);  // Only the 1st find will print message
                    found = true;
                }
                System.out.println(i + 1 + ". " + tasks.getList().get(i).toString());
            }
        }
        if (!found) {
            System.out.println(NOT_FIND_TASK_MESSAGE + keyWord);  // 如果没有找到匹配项，可以打印一个提示消息
        }
        System.out.println(SEPARATOR);
    }


    public String readCommand(){
        return scanner.nextLine();
    }
    public void showLine(){
        System.out.println(SEPARATOR);
    }
    public void print(String message){
        System.out.println(message);
    }
    public void printEmptyLine(){
        System.out.println();
    }
}
