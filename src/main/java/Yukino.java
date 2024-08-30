import Utils.messageHandler;

import java.util.Scanner;

public class Yukino {


    public static void handleMessage(String message) {
        if(message == null || message.equals(""))
            return;
        if (message.equals("bye")) {
            System.out.println("Bye! Hope to see you soon!\n");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I am Yukino!\n");
        System.out.println("What can I do for you?\n");
        System.out.println("-----------------------------------\n");

        Scanner scanner = new Scanner(System.in);
        String input = null;
        while(true) {
            input = scanner.nextLine();
            if(input.equals("bye")) {
                System.out.println("Bye! Hope to see you soon!\n");
                break;
            }
            else {
                messageHandler.echo(input);
            }
        }
    }
}
