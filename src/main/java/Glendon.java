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
        String[] list = new String[100];
        int taskNumber;
        int taskCounter = 0;

        while (response != null) {
            if (response.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (response.equals("list")){
                taskNumber = 1;
                for (int i = 0; i < list.length; i++) {
                    if (list[i] != null) {
                        System.out.println(taskNumber + ". " + list[i]);
                        taskNumber++;
                    }
                }
            } else {
                list[taskCounter++] = response;
                System.out.println("added: " + response);
            }
            response = in.nextLine();
        }
    }
}
