import Entity.Message;
import Entity.messageList;
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
        String logo = "__   __         _        \n"
                + "\\ \\ / /_     _ / /___ __ ______ ______      \n"
                + " \\   / //  / // ___/ / // ___ ///--/ /     \n"
                + "  / // /__/ //   \\  / // / / ///__/ /  \n"
                + " /_/ /____/ /_/ \\_\\/_//_/ /_//_____/ \n";
        System.out.println(logo);
        System.out.println("Hello, I am Yukino!\n");
        System.out.println("What can I do for you?\n");
        System.out.println("-----------------------------------\n");

        Scanner scanner = new Scanner(System.in);
        String input = null;
        messageList list = new messageList();
        while(true) {
            input = scanner.nextLine();

            if(input.equals("bye")) {
                System.out.println("-----------------------------------\n");
                System.out.println("Bye! Hope to see you soon!\n");
                System.out.println("-----------------------------------\n");
                break;
            }
            else if(input.equals("list")) {
                messageHandler.listShow(list);
            }
            else if(input.contains("mark") || input.contains("unmark")) {
                messageHandler.mark(list, input);
            }
            else {
                Message message = new Message(input);
                messageHandler.addList(list, message);
            }
        }
    }
}
