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

            switch (type) {
                case BYE:
                    // user input is bye: end programme
                    System.out.println("""
                            \t____________________________________________________________
                            \tBye. Hope to see you again soon!
                            \t____________________________________________________________
                            """);
                    break; // stop programme

                case LIST:

                    // user input is list: display past tasks
                    System.out.println("""
                            \t____________________________________________________________
                            \tHere are the tasks in your list:""");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("\t\t" + (i + 1) + "." + tasks[i].toString());
                    }
                    System.out.println("\t____________________________________________________________\n");
                    break;

                case MARK:
                    try {
                        // user input is mark/unmark x: mark corresponding task as done or undone
                        String[] markWords = line.split(" ");
                        int index = Integer.parseInt(markWords[1]) - 1;

                        if (Objects.equals(markWords[0], "mark")) {
                            tasks[index].setDone(true);

                            System.out.println("\t____________________________________________________________\n"
                                    + "\tNice! I've marked this task as done:\n"
                                    + "\t\t" + tasks[index].toString()
                                    + "\n\t____________________________________________________________\n");
                        } else {
                            tasks[index].setDone(false);

                            System.out.println("\t____________________________________________________________\n"
                                    + "\tOK, I've marked this task as not done yet:\n"
                                    + "\t\t" + tasks[index].toString()
                                    + "\n\t____________________________________________________________\n");
                        }
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("""
                                \t____________________________________________________________
                                \tHEY mark/unmark should be followed by a valid integer
                                \t____________________________________________________________
                                """);
                    } catch (NullPointerException e) {
                        System.out.println("""
                                \t____________________________________________________________
                                \tInteger provided does not have a corresponding task
                                \t____________________________________________________________
                                """);
                    }
                    break;

                case EVENT:

                    try {
                        // user is creating a new event
                        String[] eventWords = line.split("/");

                        if (eventWords.length != 3 ||
                                !eventWords[1].startsWith("from ") ||
                                !eventWords[2].startsWith("to ")) {
                            throw new IllegalArgumentException();
                        }

                        tasks[taskCount] = new Event(eventWords[0].replace("event", "").trim(), eventWords[1].replace("from ", "").trim(), eventWords[2].replace("to ", "").trim()); // add task to storage
                        taskCount += 1;

                        System.out.println("\t____________________________________________________________\n"
                                + "\tGot it. I've added this task:\n"
                                + "\t\t" + tasks[taskCount - 1].toString() + "\n"
                                + "\tNow you have " + taskCount + " tasks in the list.\n"
                                + "\t____________________________________________________________\n");
                    } catch (Exception e) {
                        System.out.println("""
                                \t____________________________________________________________
                                \tInvalid format to create an event:
                                \tUse 'event <description> /from <start_time> /to <end_time>'
                                \t____________________________________________________________
                                """);
                    }
                    break;

                case DEADLINE:
                    try {
                        // user is creating a new deadline
                        String[] lineWords = line.split("/by");
                        if (lineWords.length != 2) {
                            throw new IllegalArgumentException();
                        }

                        tasks[taskCount] = new Deadline(lineWords[0].replace("deadline", "").trim(), lineWords[1].trim()); // add task to storage
                        taskCount += 1;

                        System.out.println("\t____________________________________________________________\n"
                                + "\tGot it. I've added this task:\n"
                                + "\t\t" + tasks[taskCount - 1].toString() + "\n"
                                + "\tNow you have " + taskCount + " tasks in the list.\n"
                                + "\t____________________________________________________________\n");
                    } catch (Exception e) {
                        System.out.println("""
                                \t____________________________________________________________
                                \tInvalid format to create a deadline:
                                \tUse 'deadline <description> /by <due_date>'
                                \t____________________________________________________________
                                """);
                    }
                    break;

                case TODO:
                    try {
                        // user is creating a new  Todo
                        String string = line.replace("todo", "").trim();

                        if (string.isEmpty()) {
                            throw new IllegalArgumentException();
                        }

                        tasks[taskCount] = new Todo(string.trim()); // add task to storage
                        taskCount += 1;

                        System.out.println("\t____________________________________________________________\n"
                                + "\tGot it. I've added this task:\n"
                                + "\t\t" + tasks[taskCount - 1].toString() + "\n"
                                + "\tNow you have " + taskCount + " tasks in the list.\n"
                                + "\t____________________________________________________________\n");
                    } catch (Exception e) {
                        System.out.println("""
                                \t____________________________________________________________
                                \tInvalid format to create a todo:
                                \tUse 'todo <task>'
                                \t____________________________________________________________
                                """);
                    }
                    break;

                default:

                    // invalid command
                    System.out.println("""
                            \t____________________________________________________________
                            Invalid command :(
                            \t____________________________________________________________
                            """);
            }
        }
    }
}
