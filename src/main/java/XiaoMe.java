import java.util.Objects;
import java.util.Scanner;


public class XiaoMe {
    static Task[] taskStorage= new Task[100];
    static int NumOfTasks = 0;

    public static boolean isMark(String line) {
        // checks if user input is a mark / unmark command

        String[] words = line.trim().split(" ");

        if (words.length != 2) {
            // input is not 2 words long
            return false;
        }
        // first word is not "mark" or "unmark"
        return Objects.equals(words[0], "mark") || Objects.equals(words[0], "unmark");

        //todo check if 2nd word is an integer
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("""
                ____________________________________________________________\s
                Hello! I'm XiaoMe
                What can I do for you?
                ____________________________________________________________
                """);
        while (true) {
            line = in.nextLine();

            if (Objects.equals(line, "bye")) {
                // user input is bye: end programme
                System.out.println("""
                        ____________________________________________________________
                        Bye. Hope to see you again soon!
                        ____________________________________________________________
                        
                        """);
                break;
            } else if (Objects.equals(line, "list")) {
                // user input is list: display past tasks
                System.out.println("""
                        ____________________________________________________________
                        Here are the tasks in your list:""");
                for (int i = 0; i < NumOfTasks; i++) {
                    System.out.println((i + 1) + ".[" + taskStorage[i].getStatusIcon() + "] " + taskStorage[i].getDescription());
                }
                System.out.println("____________________________________________________________\n");
            } else if (isMark(line)) {
                String[] words = line.split(" ");
                int taskCount = Integer.parseInt(words[1]) - 1;
                if (Objects.equals(words[0], "mark")) {
                    taskStorage[taskCount].setDone(true);

                    System.out.println("____________________________________________________________\n"
                            + "Nice! I've marked this task as done:\n"
                            + "\t[" + taskStorage[taskCount].getStatusIcon() + "] " + taskStorage[taskCount].getDescription()
                            + "\n____________________________________________________________\n");
                } else {
                    taskStorage[taskCount].setDone(false);

                    System.out.println("____________________________________________________________\n"
                            + "\tOK, I've marked this task as not done yet:\n"
                            + "[" + taskStorage[taskCount].getStatusIcon() + "] " + taskStorage[taskCount].getDescription()
                            + "\n____________________________________________________________\n");
                }

            } else {
                System.out.println("____________________________________________________________\n"
                        + "added: " + line + "\n"
                        + "____________________________________________________________\n");
                taskStorage[NumOfTasks] = new Task(line); // add task to storage
                NumOfTasks += 1;
            }

        }
    }
}
