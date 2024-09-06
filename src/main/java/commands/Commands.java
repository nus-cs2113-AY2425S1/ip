package commands;

import tasks.*;

public class Commands {
    public static void markAsDone(Task[] list, String[] str) {
        int index = Integer.parseInt(str[1]) - 1;
        if (list[index]== null){
            System.out.println("Sorry, list number does not exist");
        }else {
            list[index].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list[index].taskType() + "[" + list[index].getStatusIcon() + "] " + list[index].description);
        }
    }

    public static void showList(Task[] list){
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.println((i+1) + ". " + list[i].toString());
            }
        }
    }

    public static void markAsNotDone(Task[] list, String[] str) {
        int index = Integer.parseInt(str[1])- 1;
        if (list[index]== null){
            System.out.println("Sorry, list number does not exist");
        }else {
            list[index].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(list[index].taskType()+ "[" + list[index].getStatusIcon() + "] " + list[index].description);
        }
    }
}
