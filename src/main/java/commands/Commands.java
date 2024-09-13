package commands;

import tasks.*;

import java.util.ArrayList;

public class Commands {
    public static void markAsDone(ArrayList<Task> list, String[] str) {
        int index = Integer.parseInt(str[1]) + 1;
        if (list.get(index) == null){
            System.out.println("Sorry, list number does not exist");
        }else {
            list.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(index).taskType() + "[" + list.get(index).getStatusIcon() + "] " + list.get(index).description);
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

    public static void markAsNotDone(ArrayList<Task> list, String[] str) {
        int index = Integer.parseInt(str[1])- 1;
        if (list.get(index) == null){
            System.out.println("Sorry, list number does not exist");
        }else {
            list.get(index).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(list.get(index).taskType()+ "[" + list.get(index).getStatusIcon() + "] " + list.get(index).description);
        }
    }

    public static void delete(ArrayList<Task> taskList, int index ) {
        taskList.remove(index-1);
    }
}
