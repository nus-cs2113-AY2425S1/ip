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
        String line;
        String[] storedItems = new String[100];
        int dataIndex = 0;
        boolean exit = false;
        while (!exit) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if(line.equalsIgnoreCase("bye"))
            {
                exit = true;
            }
            else if(line.equalsIgnoreCase("list"))
            {
                for(int i = 0; i < dataIndex; i++)
                {
                    System.out.println(dataIndex + ". " + storedItem);
                }
            }
            storedItems[dataIndex] = line;
            dataIndex++;
            System.out.println("Added: " + line);
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
