import java.util.Hashtable;
import java.util.Scanner;

public class Wildpeace {
    public static void initialise()
    {
        System.out.println("What can I do for you, enter '1' for echo, '2' for storing your plan, '0' to exit");
        Scanner initialInputScanner = new Scanner(System.in);
        String initialInput = initialInputScanner.nextLine();
        switch (initialInput) {
        case "1":
            echo();
            break;
        case "2":
            storeData();

        }
    }
    public static void storeData()
    {
        System.out.println("List your plans one by one");
        String line;
        // String[] storedItems = new String[100];
        Hashtable <String, Boolean> storedItems = new Hashtable<>();
        int dataIndex = 0;
        boolean exit = false;
        while (!exit) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if(line.equalsIgnoreCase("bye"))
            {
                exit = true;
            }
            else if(line.equalsIgnoreCase("list")) {
                for (String key : storedItems.keySet()) {
                    if (storedItems.get(key)) {
                        System.out.println("[X] " + key);
                    } else {
                        System.out.println("[ ] " + key);
                    }

                }
            }
            else if(line.contains("unmark"))
            {
                storedItems.put(line.substring(7), false);
                System.out.println("I have unmarked the task.");
                System.out.println("[ ] " + line);
            }
            else if (line.contains("mark")) {

                System.out.println("I have marked the [X] ");
                System.out.println("[X] " + line);
                storedItems.put(line.substring(5), true);


            }
            //storedItems[dataIndex] = line;
            else {
                storedItems.put(line, false);
                dataIndex++;
                System.out.println("Added: " + line);
            }
        }
    }
    public static void echo()
    {
        String line;
        boolean exit = false;
        while (!exit) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                exit = true;
            }
            System.out.println(line);
        }
        initialise();
    }
    public static void main(String[] args) {

        String logo = "__        ___ _     _                           \n" +
                      "\\ \\      / (_) | __| |_ __   ___  __ _  ___ ___ \n" +
                      " \\ \\ /\\ / /| | |/ _` | '_ \\ / _ \\/ _` |/ __/ _ \\\n" +
                      "  \\ V  V / | | | (_| | |_) |  __/ (_| | (_|  __/\n" +
                      "   \\_/\\_/  |_|_|\\__,_| .__/ \\___|\\__,_|\\___\\___|\n" +
                      "                     |_|                        ";
        System.out.println("Hello from\n" + logo);



        initialise();


        //System.out.println("See you next time, bye!");
    }
}
