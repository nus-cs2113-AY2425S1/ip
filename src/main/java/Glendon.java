import java.awt.Graphics;
import java.util.Scanner;

public class Glendon {
    public static void main(String[] args) {
        String logo = " _______   _                     _\n"
                + "| ______| | |                   | |\n"
                + "| |  ___  | |  ___   _____   ___| |  _____   _____\n"
                + "| | |__ | | | / _ \\ |  _  | |  _  | |  _  | |  _  |\n"
                + "| |___| | | | | __/ | | | | | |_| | | |_| | | | | |\n"
                + "|_______| |_| \\___| |_| |_| |_____| |_____| |_| |_|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Glendon.\n" + "What can I do for you?\n");

        Scanner in = new Scanner(System.in);
        String response;
        response = in.nextLine();
        while (response != null) {
            if (response.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(response);
            }
            response = in.nextLine();
        }
    }
}
