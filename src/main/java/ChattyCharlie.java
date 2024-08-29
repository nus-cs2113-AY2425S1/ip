import java.util.Scanner;

public class ChattyCharlie {

    public static void Echo() { //Echo as a function
        String line;
        String you = "User:    ";
        //make the scanner
        Scanner in = new Scanner(System.in);
        //accept an insert
        while (true) {
            System.out.print(you);
            line = in.nextLine();

            //if the line contains bye, it signals the end
            if (line.contains("Bye") || line.contains("bye")) {
                break;
            }

            String response = "Input:   ";
            System.out.println(response + line);
        }
    }

    public static void main(String[] args) {
        String logo = "   _____      \n"
                + "  /     \\     \n"
                + " |  O O  |    \n"
                + " | \\___/ |    \n"
                + "  \\_____/     \n"
                + " /\\_____/\\    \n"
                + " |       |    \n"
                + " |       |    \n"
                + " |_______|    \n"
                + "              \n";
        String charlie = "Charlie: ";

        String greeting = "Hello! I'm ChattyCharlie, your friendly companion.\n"
                + "         What can I help you with today?\n" ;

        String farewell = "Bye, have a pleasant day today!";

        System.out.println(logo + charlie+ greeting);
        Echo();
        System.out.println(charlie + farewell);

    }
}
