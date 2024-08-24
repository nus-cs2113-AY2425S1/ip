import java.util.Scanner;

public class Doug {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        String dashedLine = "______________________________________________\n";
        System.out.println(dashedLine + "Howdy partner! My name is Doug Dimmadome\n"
                            + "Now what can I do for ya?\n" + dashedLine);

        String[] commands = new String[100];
        int counter = 0;

        boolean saidBye = false;

        while (!saidBye) {
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();
            if (command.equals("bye")) {
                saidBye = true;
            } else if (command.equals("list")){
                System.out.print(dashedLine);
                for (int i = 0; i < counter; i++) {
                    System.out.println((i+1) + ". " + commands[i]);
                }
                System.out.print(dashedLine);
            } else {
                if (counter == 100) {
                    System.out.println("Can't add no more commands partner, the list is full!");
                    continue;
                }
                commands[counter] = command;
                counter++;
                System.out.println(dashedLine + "added: " + command + "\n" + dashedLine);
            }
        }

        System.out.println(dashedLine + "It's been a pleasure partner.\n" +
                "Hope to see you around these parts again soon!\n" + dashedLine);
    }
}
