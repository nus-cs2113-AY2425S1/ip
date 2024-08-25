import java.util.Scanner;
import java.util.ArrayList;

public class Erika {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        int taskSize = 0;
        String logo =
                " _______   ________  ___  ___  __    ________     \n" +
                "|\\  ___ \\ |\\   __  \\|\\  \\|\\  \\|\\  \\ |\\   __  \\    \n" +
                "\\ \\   __/|\\ \\  \\|\\  \\ \\  \\ \\  \\/  /|\\ \\  \\|\\  \\   \n" +
                " \\ \\  \\_|/_\\ \\   _  _\\ \\  \\ \\   ___  \\ \\   __  \\  \n" +
                "  \\ \\  \\_|| \\ \\  \\\\  \\\\ \\  \\ \\  \\\\ \\  \\ \\  \\ \\  \\ \n" +
                "   \\ \\_______\\ \\__\\\\ _\\\\ \\__\\ \\__\\\\ \\__\\ \\__\\ \\__\\\n" +
                "    \\|_______|\\|__|\\|__|\\|__|\\|__| \\|__|\\|__|\\|__|";
        System.out.println("Hello from\n" + logo + "\n");
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Erika\n What can I do for you?");
        System.out.println("____________________________________________________________");
        line = in.nextLine();
        while (!line.equals("bye")) {
            line = line.trim();
            if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskSize; i++) {
                    System.out.println(Integer.toString(i+1) + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            } else {
                tasks.add(line);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + line);
                System.out.println("____________________________________________________________");
                taskSize++;
            }
            line = in.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Auf Wiedersehen! Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
