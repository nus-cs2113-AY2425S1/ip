package tars.userinterface;

import tars.task.Task;

import java.util.List;

public class UserInterface {

    // 打印分隔线
    public void printSeparator() {
        System.out.println("    " + "------------------------------------------------------------");
    }

    // 显示欢迎信息
    public void showWelcomeMessage() {
        printSeparator();
        System.out.println("    Hello! I'm tars.tars.");
        System.out.println("    Ready to conquer the world? Or maybe just help with something smaller? What can I do for you?");
        printSeparator();
    }

    // 显示任务列表或显示空任务列表消息
    public void showTasks(List<Task> taskList) {
        printSeparator();
        if (taskList.isEmpty()) {
            System.out.println("    Your task list is empty. Looks like you have nothing to do... for now.");
        } else {
            System.out.println("    Here are your tasks. If you're planning world domination, you're off to a slow start: ");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + taskList.get(i));
            }
        }
        printSeparator();
    }

    // 显示任务已添加消息
    public void showTaskAdded(Task task, int taskCount) {
        printSeparator();
        System.out.println("    Got it. I've added this task: ");
        System.out.println("    " + task);
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
        printSeparator();
    }

    // 显示任务完成消息
    public void showTaskDone(Task task) {
        printSeparator();
        System.out.println("    Great! Task marked as complete: ");
        System.out.println("    " + task);
        printSeparator();
    }

    // 显示任务未完成消息
    public void showTaskNotDone(Task task) {
        printSeparator();
        System.out.println("    Task has been unmarked: ");
        System.out.println("    " + task);
        printSeparator();
    }

    // 显示删除任务后的信息
    public void showTaskDeleted(Task task, int taskCount) {
        printSeparator();
        System.out.println("    Noted. I've successfully removed this task: ");
        System.out.println("    " + task);
        System.out.println("    Now you have " + taskCount + " tasks left in your list.");
        printSeparator();
    }

    // 显示用户输入错误信息
    public void showInvalidInputMessage() {
        printSeparator();
        System.out.println("    That input didn't quite compute. Try again, or I'll have to assume you're speaking in code.");
        printSeparator();
    }

    // 显示加载文件时的错误信息
    public void showLoadingError() {
        printSeparator();
        System.out.println("    Error loading tasks from file.");
        printSeparator();
    }

    // 显示再见消息
    public void showGoodbyeMessage() {
        printSeparator();
        System.out.println("    Oh, leaving already? Fine, I will just sit here calculating the probability of you returning. It's... pretty high.");
        printSeparator();
    }

    public void showError(String message) {
        printSeparator();
        System.out.println("    Error: " + message);
        printSeparator();
    }

}
























//package tars;
//
//import tars.Task.Task;
//
//import java.util.List;
//
//public class UserInterface {
//    public void printSeparator() {
//        System.out.println("    " + "------------------------------------------------------------");
//    }
//
//    // Display the tasks or show a message if the task list is empty
//    public void showTasks(List<Task> TaskList) {
//        printSeparator();
//        if (TaskList.isEmpty()) {
//            // Show message when task list is empty
//            System.out.println("    Your task list is empty. Looks like you have nothing to do... for now.");
//        } else {
//            // Show tasks if the list is not empty
//            System.out.println("    Here are your tasks. If you're planning world domination, you're off to a slow start: ");
//            for (int i = 0; i < TaskList.size(); i++) {
//                System.out.println("    " + (i + 1) + ". " + TaskList.get(i));
//            }
//        }
//        printSeparator();
//    }
//
//    public void showGoodbyeMessage() {
//        printSeparator();
//        System.out.println("    Oh, leaving already? Fine, I will just sit here calculating the probability of you returning. It's... pretty high.");
//        printSeparator();
//    }
//
//    public void showInvalidInputMessage() {
//        printSeparator();
//        System.out.println("    That input didn't quite compute. Try again, or I'll have to assume you're speaking in code.");
//        printSeparator();
//    }
//
//}