package blossom;

import blossom.task.Task;
import java.util.ArrayList;

public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String LOGO =
            """
                     _______     .---.       ,-----.       .-'''-.    .-'''-.     ,-----.    ,---.    ,---.\s
                    \\  ____  \\   | ,_|     .'  .-,  '.    / _     \\  / _     \\  .'  .-,  '.  |    \\  /    |\s
                    | |    \\ | ,-./  )    / ,-.|  \\ _ \\  (`' )/`--' (`' )/`--' / ,-.|  \\ _ \\ |  ,  \\/  ,  |\s
                    | |____/ / \\  '_ '`) ;  \\  '_ /  | :(_ o _).   (_ o _).   ;  \\  '_ /  | :|  |\\_   /|  |\s
                    |   _ _ '.  > (_)  ) |  _`,/ \\ _/  | (_,_). '.  (_,_). '. |  _`,/ \\ _/  ||  _( )_/ |  |\s
                    |  ( ' )  \\(  .  .-' : (  '\\_/ \\   ;.---.  \\  :.---.  \\  :: (  '\\_/ \\   ;| (_ o _) |  |\s
                    | (_{;}_) | `-'`-'|___\\ `"/  \\  ) / \\    `-'  |\\    `-'  | \\ `"/  \\  ) / |  (_,_)  |  |\s
                    |  (_,_)  /  |        \\'. \\_/``".'   \\       /  \\       /   '. \\_/``".'  |  |      |  |\s
                    /_______.'   `--------`  '-----'      `-...-'    `-...-'      '-----'    '--'      '--'""";


    public void printItems(ArrayList<Task> listOfTasks) {
        System.out.println(HORIZONTAL_LINE);
        if(listOfTasks.isEmpty()) {
            System.out.println("No tasks added! ( • ᴖ • )");
        }
        else {
            // Print items in order
            System.out.println("Try hard to get these tasks done~~ ");
            int orderInList = 1;
            for(Task item : listOfTasks) {
                System.out.println(orderInList+ ". " + item.toString());
                orderInList++;
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printIntro() {
        System.out.println(LOGO + "\n" +"Hello, I'm Blossom! ⸜(｡˃ ᵕ ˂ )⸝♡");
        System.out.println("Your wish is my command (シ_ _ )シ");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printNumberOfTasks(ArrayList<Task> listOfTasks) {
        System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
    }


}
