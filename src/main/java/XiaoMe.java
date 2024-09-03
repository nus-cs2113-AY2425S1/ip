import java.util.Objects;
import java.util.Scanner;


public class XiaoMe {
    static Task[] tasks = new Task[100];
    static Integer taskCount = 0;

    public enum Type {
        MARK,
        LIST,
        TODO,
        EVENT,
        DEADLINE,
        TASK,
        BYE
    }

    public static Type checkType(String line) {
        // checks what kind of command was received by XiaoMe

        if (Objects.equals(line, "bye")) {
            return Type.BYE;
        } else if (Objects.equals(line, "list")) {
            return Type.LIST;
        }

        String[] words = line.trim().split(" ");
        String first = words[0];

        if (Objects.equals(first, "todo")) {
            return Type.TODO;
        } else if (Objects.equals(first, "deadline")) {
            return Type.DEADLINE;
        } else if (Objects.equals(first, "event")) {
            return Type.EVENT;
        } else if (Objects.equals(first, "mark") || Objects.equals(first, "unmark")) {
            return Type.MARK;
        }

        return Type.TASK;
    }

    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("""               
                \t____________________________________________________________
                \tHello! I'm XiaoMe
                \tWhat can I do for you?
                \t____________________________________________________________
                """);

        while (true) {
            line = in.nextLine();
            Type type = checkType(line);

            if (type == Type.BYE) {
                // user input is bye: end programme
                System.out.println("""
                        \t____________________________________________________________
                        \tBye. Hope to see you again soon!
                        \t____________________________________________________________
                        """);
                break; // stop programme
            } else if (type == Type.LIST) {
                // user input is list: display past tasks
                System.out.println("""
                        \t____________________________________________________________
                        \tHere are the tasks in your list:""");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("\t\t" + (i + 1) + "." + tasks[i].toString());
                }
                System.out.println("\t____________________________________________________________\n");

            } else if (type == Type.MARK) {
                // user input is mark/unmark x: mark corresponding task as done or undone
                String[] words = line.split(" ");
                int taskCount = Integer.parseInt(words[1]) - 1;

                if (Objects.equals(words[0], "mark")) {
                    tasks[taskCount].setDone(true);

                    System.out.println("\t____________________________________________________________\n"
                            + "\tNice! I've marked this task as done:\n"
                            + "\t\t" + tasks[taskCount].toString()
                            + "\n\t____________________________________________________________\n");
                } else {
                    tasks[taskCount].setDone(false);

                    System.out.println("\t____________________________________________________________\n"
                            + "\tOK, I've marked this task as not done yet:\n"
                            + "\t\t" + tasks[taskCount].toString()
                            + "\n\t____________________________________________________________\n");
                }

            } else if (type == Type.EVENT) {

                // user is creating a new event
                String[] words = line.split("/");

                tasks[taskCount] = new Event(words[0].replace("event", "").trim(), words[1].replace("from ", "").trim(), words[2].replace("to ", "").trim()); // add task to storage
                taskCount += 1;

                System.out.println("\t____________________________________________________________\n"
                        + "\tGot it. I've added this task:\n"
                        + "\t\t" + tasks[taskCount - 1].toString() + "\n"
                        + "\tNow you have " + taskCount + " tasks in the list.\n"
                        + "\t____________________________________________________________\n");

            } else if (type == Type.DEADLINE) {

                // user is creating a new deadline
                String[] words = line.split("/by");

                tasks[taskCount] = new Deadline(words[0].replace("deadline", "").trim(), words[1].trim()); // add task to storage
                taskCount += 1;

                System.out.println("\t____________________________________________________________\n"
                        + "\tGot it. I've added this task:\n"
                        + "\t\t" + tasks[taskCount - 1].toString() + "\n"
                        + "\tNow you have " + taskCount + " tasks in the list.\n"
                        + "\t____________________________________________________________\n");

            } else if (type == Type.TODO) {

                // user is creating a new Todo
                String string = line.replace("todo ", "");
                tasks[taskCount] = new Todo(string.trim()); // add task to storage
                taskCount += 1;

                System.out.println("\t____________________________________________________________\n"
                        + "\tGot it. I've added this task:\n"
                        + "\t\t" + tasks[taskCount - 1].toString() + "\n"
                        + "\tNow you have " + taskCount + " tasks in the list.\n"
                        + "\t____________________________________________________________\n");

            } else {
                // user is creating a new task
                System.out.println("\t____________________________________________________________\n"
                        + "\tGot it. I've added this task:\n"
                        + "\t\t" + line + "\n"
                        + "\t____________________________________________________________\n");

                tasks[taskCount] = new Task(line); // add task to storage
                taskCount += 1;
            }

        }
    }
}
