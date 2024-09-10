import java.text.ParseException;
import java.util.Scanner;

public class Juan {
    private final static String porFavor = "Por Favor?\n";
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
        } else if (line.startsWith("mark ")){
            // Mark
            try {
                int taskIndex = Integer.parseInt(line.replace("mark ", "")) - 1;
                Task.mark(taskIndex);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println(porFavor + "MARK EXCEPTION: INVALID TASK INDEX");
            } catch (NullPointerException e) {
                System.out.println(porFavor + "MARK EXCEPTION: NULL TASK INDEX");
            }
        } else if (line.startsWith("unmark ")){
            // Unmark
            try {
                int taskIndex = Integer.parseInt(line.replace("unmark ", "")) - 1;
                Task.unmark(taskIndex);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println(porFavor + "UNMARK EXCEPTION: INVALID TASK INDEX");
            } catch (NullPointerException e) {
                System.out.println(porFavor + "UNMARK EXCEPTION: NULL TASK INDEX");
            }

        } else if (line.startsWith("todo ")) {
            try {
                new ToDo(line);
            } catch (ToDoConstructorException e) {
                System.out.println(porFavor + e.getMessage());
            }
        } else if (line.startsWith("deadline ")) {
            try {
                new Deadline(line);
            } catch (DeadlineConstructorException e) {
                System.out.println(porFavor + e.getMessage());
            }
        } else if (line.startsWith("event ")) {
            try {
                new Event(line);
            } catch (EventConstructorException e) {
                System.out.println(porFavor + e.getMessage());
            }
        } else {
            System.out.println(porFavor + "TRY A NEW REQUEST");
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