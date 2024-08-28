import java.util.Scanner;
public class Cubone {
    public static void main(String[] args) {
        String logo =   
            "   ______      __                       \n"+
            "  / ____/_  __/ /_  ____  ____  ___     \n"+
            " / /   / / / / __ \\/ __ \\/ __ \\/ _ \\\n"+
            "/ /___/ /_/ / /_/ / /_/ / / / /  __/    \n"+
            "\\____/\\__,_/_.___/\\____/_/ /_/\\___/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Cubone\nWhat can I do for you?");

        // loop for user input
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String chat_prefix = "(Cubone) ";
            if (input.equals("bye")) {
                System.out.println(chat_prefix + "Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(chat_prefix + input);
            }
        }
    }
}
