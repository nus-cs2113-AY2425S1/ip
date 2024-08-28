import java.util.Scanner;

public class Juan {
    private static String[] Strings = new String[100];
    private static int stringsCounter = 0;
    public static void main(String[] args) {
        lineMessage();
        helloMessage();
        lineMessage();
        chatFeature();
        byeMessage();
        lineMessage();
    }
    public static void chatFeature(){
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            String line = scanner.nextLine();
            lineMessage();
            if (line.equals("bye")) {
                exit = true;
                return;
            } else if (line.equals("list")) {
                Task.printTasksList();
            } else {
                // Check for mark and unmark
                String[] parts = line.split(" ");
                if (parts[0].equals("mark")){
                    // Mark
                    Task.mark(Integer.parseInt(parts[1]) - 1);
                } else if (parts[0].equals("unmark")){
                    // Unmark
                    Task.unmark(Integer.parseInt(parts[1]) - 1);
                } else {
                    // else add task
                    Task newTask = new Task(line);
                }

            }

            lineMessage();
        }
    }
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