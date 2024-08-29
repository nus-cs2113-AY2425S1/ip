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
        System.out.println(dashedLine + "Howdy partner! My name is Doug Dimmadome.\n"
                            + "Now what can I do for ya?\n" + dashedLine);

        Task[] commands = new Task[100];
        int counter = 0;

        boolean saidBye = false;

        while (!saidBye) {
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();

            if (command.equals("bye")) {
                saidBye = true;
            } else if (command.equals("list")){
                System.out.println(dashedLine + "Here, let me lay out your tasks for you.");
                for (int i = 0; i < counter; i++) {
                    System.out.println((i+1) + commands[i].listTask());
                }
                System.out.print(dashedLine);
            } else if (command.startsWith("mark")) {
                int listIndex = Integer.parseInt(command.substring(5));

                if (listIndex > counter || listIndex <= 0) {
                    System.out.println(dashedLine + "No can do bud, your input is invalid!\n" + dashedLine);
                    continue;
                }

                commands[listIndex - 1].markAsDone();
                System.out.println(dashedLine + "Sure thing partner, I'll mark it as done");
                System.out.print(commands[listIndex - 1].listTask() + "\n" + dashedLine);
            } else if (command.startsWith("unmark")) {
                int listIndex = Integer.parseInt(command.substring(7));

                if (listIndex > counter || listIndex <= 0) {
                    System.out.println(dashedLine + "No can do bud, your input is invalid!\n" + dashedLine);
                    continue;
                }

                commands[listIndex - 1].markAsNotDone();
                System.out.println(dashedLine + "Sure thing partner, I'll mark it as not done");
                System.out.print(commands[listIndex - 1].listTask() + "\n" + dashedLine);
            } else {
                if (counter >= 100) {
                    System.out.println(dashedLine + "Sorry partner, the list is full!" + dashedLine);
                    continue;
                }
                Task t = new Task(command);
                commands[counter] = t;
                counter++;
                System.out.println(dashedLine + "added: " + command + "\n" + dashedLine);
            }
        }

        System.out.println(dashedLine + "It's been a pleasure partner.\n" +
                "Hope to see you around these parts again soon!\n" + dashedLine);
    }
}
