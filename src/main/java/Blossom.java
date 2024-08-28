import java.util.Scanner;

public class Blossom {
    private static int numberOfItems = 0;
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
    private static String[] listOfItems = new String[100];

    public static void printItems(String[] listOfItems) {
        // Print items in order
        int index = 0;
        int orderInList = 1;
        // Print the list until the current item is empty - we've reached the end of the list
        System.out.println(horizontalLine);
        while(listOfItems[index] != null) {
            System.out.println(orderInList+ ". " + listOfItems[index]);
            index++;
            orderInList++;
        }
        System.out.println(horizontalLine);
    }

    public static void addItems(String[] listOfItems, String input) {
        listOfItems[numberOfItems] = input;
        numberOfItems++;
    }

    public static void printItem(String input) {
        System.out.println(horizontalLine);
        System.out.println("added: " + input);
        System.out.println(horizontalLine);
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
                    printItems(listOfItems);
                }
                else {
                    addItems(listOfItems, line);
                    printItem(line);
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
