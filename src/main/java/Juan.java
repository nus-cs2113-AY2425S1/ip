import java.util.Scanner;

public class Juan {
    public static void main(String[] args) {
        lineMessage();
        helloMessage();
        lineMessage();

        boolean continueChatting = true;
        while (continueChatting) {
            continueChatting = chatFeature();
        }
        byeMessage();
        lineMessage();
    }
    public static boolean chatFeature(){
        // Less efficient to create a new scanner everytime but code is much neater
        // If return True means continue
        // Else End

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        lineMessage();

        if (line.equals("bye")) {
            return false;
        } else if (line.equals("list")) {
            Task.printTasksList();
            return true;
        }

        // Check for mark and unmark or just add task
        String[] parts = line.split(" ");
        if (parts[0].equals("mark")){
            // Mark
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            Task.mark(taskIndex);
        } else if (parts[0].equals("unmark")){
            // Unmark
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            Task.unmark(taskIndex);
        } else {
            // else add task
            Task newTask = new Task(line);
        }

        lineMessage();
        return true;
    }

    // Message Functions for cleaner main Function
    public static void lineMessage() {
        String line = "____________________________________________________________\n";
        System.out.print(line);
    }
    public static void helloMessage() {
        String greeting =
            "               ._-'-_ .\n" +
            "          . '  /_-_-_\\   ` .\n" +
            "       .'     |-_-_-_-|      `.\n" +
            "      (       `.-_-_-.'        )\n" +
            "      !`.                    .'!\n" +
            "        ! ` .            . ' !\n" +
            "          ! ! ! ! ! ! ! !  !\n" +
            "            / /       \\ \\\n" +
            "          _-| \\___ ___/ /-_\n" +
            "         (_ )__\\_)\\(_/__( _)\n" +
            "             ))))\\X\\ ((((\n" +
            "               \\/ \\/ \n" +
            "Hola Amigo, I am Juan Cervantes Salamanca from Michoacan \n" +
            "Welcome to la familia \n" +
            "How can we help you? \n";
        System.out.print(greeting);
    }
    public static void byeMessage() {
        String bye = "Adios amigo, la familia will miss you\n";
        System.out.print(bye);
    }

}