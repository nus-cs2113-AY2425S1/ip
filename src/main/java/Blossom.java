import java.util.Scanner;
import java.util.ArrayList;

public class Blossom {
    private static String horizontalLine = "____________________________________________________________";
    private static String logo =
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
    private static ArrayList<Task> listOfItems = new ArrayList<Task>();

    public static void printItems() {
        // Print items in order
        int orderInList = 1;
        System.out.println(horizontalLine);
        for(Task item : listOfItems) {
            System.out.println(orderInList+ ". " + item.toString());
            orderInList++;
        }
        System.out.println(horizontalLine);
    }

    public static void markAndUnmarkItem(int itemIndex, String action) {
        itemIndex--;
        // If this item is marked - change boolean
        Task item = listOfItems.get(itemIndex);
        if (item != null) {
            System.out.println(horizontalLine);
            if(action.equalsIgnoreCase("mark")) {
                item.markAsDone();
                System.out.println("Yayy~~ Good job in getting this done!");
                System.out.println(item.toString());
            } else {
                item.markAsUndone();
                System.out.println("Hope you get this done soon! :D");
                System.out.println(item.toString());
            }
            System.out.println(horizontalLine);
        }
    }

    public static void addTask(String input) {
        Task item = new Task(input);
        listOfItems.add(item);
    }

    public static void printIntro() {
        System.out.println(logo + "\n" +"Hello, I'm Blossom! ⸜(｡˃ ᵕ ˂ )⸝♡");
        System.out.println("Your wish is my command (シ_ _ )シ");
        System.out.println(horizontalLine);
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
                    System.out.println(horizontalLine);
                    System.out.println("added: " + line);
                    System.out.println(horizontalLine);
                }
            } else {
                System.out.println("Bye~~~ Come visit me soon! (๑>◡<๑)");
                System.out.println(horizontalLine);
                input.close();
                System.exit(0);
            }
        }
    }
}
