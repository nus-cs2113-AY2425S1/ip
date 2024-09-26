package commands;

import tasks.Task;
import java.util.ArrayList;

public class Commands {
    public static void markAsDone(ArrayList<Task> list, String[] str) {
        int index = Integer.parseInt(str[1]) - 1;
        if (index < 0 || index >= list.size()){
            System.out.println("Sorry, list number does not exist");
        }else {
            list.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(index).taskType() + "[" + list.get(index).getStatusIcon() + "] " + list.get(index).description);
        }
    }

    public static void markAsNotDone(ArrayList<Task> list, String[] str) {
        int index = Integer.parseInt(str[1]) - 1;
        if (index < 0 || index >= list.size()){
            System.out.println("Sorry, list number does not exist");
        }else {
            list.get(index).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(list.get(index).taskType()+ "[" + list.get(index).getStatusIcon() + "] " + list.get(index).description);
        }
    }

    public static void showList(ArrayList<Task> list){
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                System.out.println((i+1) + ". " + list.get(i).toString());
            }
        }
    }

    public static void delete(ArrayList<Task> taskList, int index ) {
        try {
            if (index < 0 || index > taskList.size()) {
                System.out.println("Task number out of range.");
                return;
            }
            taskList.remove(index -  1);
            System.out.println("Task removed. You now have " + taskList.size() + " tasks.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number.");
        }
    }

    public static void findTask(ArrayList<Task> list, String keyword) {
        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.description.contains(keyword)) {
                counter++;
                System.out.println((i+1) + ". " + task.toString());
            }
        }
        if (counter==0){
            System.out.println("No task found.");
        }
    }
}
