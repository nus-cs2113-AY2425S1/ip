package blossom;

import blossom.task.Deadline;
import blossom.task.Event;
import blossom.task.Task;
import blossom.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Blossom {
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
    private static final ArrayList<Task> LIST_OF_TASKS = new ArrayList<Task>();
    private static final int LENGTH_OF_TODO = 5;
    private static final int LENGTH_OF_DEADLINE = 9;
    private static final int LENGTH_OF_EVENT = 6;

    public static void printItems() {
        // Print items in order
        System.out.println("Try hard to get these tasks done~~ ");
        int orderInList = 1;
        System.out.println(HORIZONTAL_LINE);
        for(Task item : LIST_OF_TASKS) {
            System.out.println(orderInList+ ". " + item.toString());
            orderInList++;
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markAndUnmarkItem(int itemIndex, String action) {
        itemIndex--;
        // If this item is marked - change boolean
        Task item = LIST_OF_TASKS.get(itemIndex);
        if (item != null) {
            System.out.println(HORIZONTAL_LINE);
            if(action.equalsIgnoreCase("mark")) {
                item.markAsDone();
                System.out.println("Yayy~~ Good job in getting this done!");
                System.out.println(item.toString());
            } else {
                item.markAsUndone();
                System.out.println("Hope you get this done soon! :D");
                System.out.println(item.toString());
            }
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public static void addTask(String input) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Ok!! I've added this task :D");
        Task item = new Task(input); // Default item if unspecified task type
        if(input.contains("todo")) {
            item = new Todo(input.substring(LENGTH_OF_TODO));
        } else if (input.contains("deadline")) {
            String[] parts = input.substring(LENGTH_OF_DEADLINE).split(" /by ");
            item = new Deadline(parts[0], parts[1]);
        } else if (input.contains("event")) {
            String[] parts = input.substring(LENGTH_OF_EVENT).split(" /from | /to ");
            item = new Event(parts[0], parts[1], parts[2]);
        }
        System.out.println(item.toString());
        LIST_OF_TASKS.add(item);
        System.out.println("Now you have " + LIST_OF_TASKS.size() +" tasks in the list!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printIntro() {
        System.out.println(LOGO + "\n" +"Hello, I'm Blossom! ⸜(｡˃ ᵕ ˂ )⸝♡");
        System.out.println("Your wish is my command (シ_ _ )シ");
        System.out.println(HORIZONTAL_LINE);
    }


    public static void main(String[] args) {
        printIntro();
        Scanner input = new Scanner(System.in);
        // Repeatedly takes in input until it's a key word
        while(input.hasNext()) {
            String line = input.nextLine();
            if(!line.equalsIgnoreCase("bye")) {
                if(line.equalsIgnoreCase("list")) {
                    printItems();
                }
                else if(line.contains("mark") || line.contains("unmark")) {
                    // Call the unmark and mark function
                    String[] parsedLine = line.split(" ");
                    markAndUnmarkItem(Integer.parseInt(parsedLine[1]), parsedLine[0]);
                }
                else {
                    addTask(line);
                }
            } else {
                System.out.println("Bye~~~ Come visit me soon! (๑>◡<๑)");
                System.out.println(HORIZONTAL_LINE);
                input.close();
                System.exit(0);
            }
        }
    }
}
