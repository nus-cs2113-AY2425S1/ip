import java.util.Scanner;

public class Appal {
    private boolean isExited = false;

    public void printSeparator() {
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public void printReply(String reply) {
        printSeparator();
        System.out.println(reply);
        printSeparator();
    }

    public void handleInput() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        if (line.equals("bye")) {
            isExited = true;
            printReply("See ya! An Appal a day, keeps the boredom away!");
        } else {
            printReply(line);
        }
    }

    public void runAppal() {
        String chatbot = "Appal";
        printSeparator();
        System.out.println("Heyo! I'm your pal, " + chatbot + "!");
        System.out.println("Let's get things rolling, what would you like to do today?");
        printSeparator();
        while (!isExited) {
            handleInput();
        }
   }
}
