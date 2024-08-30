package Utils;

import java.util.Objects;

public class messageHandler {
    public static void echo(String message) {

        if(Objects.equals(message, "bye")) {
            System.out.println("Bye. Hope to see you soon!");
            return;
        }

        System.out.println("-----------------------------------\n");
        System.out.println(message);
        System.out.println("-----------------------------------\n");
    }
}
