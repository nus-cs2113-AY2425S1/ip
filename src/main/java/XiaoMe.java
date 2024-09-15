import task.Task;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import task.*;


public class XiaoMe {
    static ArrayList<Task> tasks = new ArrayList<>();

    public enum Type {
        MARK,
        LIST,
        TODO,
        EVENT,
        DEADLINE,
        BYE,
        DELETE
    }

    public static Type checkType(String line) throws XiaoMeException {
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
        } else if (Objects.equals(first, "delete")) {
            return Type.DELETE;
        }

        throw new XiaoMeException();
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
            Type type;
            try {
                type = checkType(line);
            } catch (XiaoMeException e) {
                // invalid command
                System.out.println("""
                            \t____________________________________________________________
                            Invalid command :(
                            \t____________________________________________________________
                            """);
                continue;
            }
            switch (type) {
                case BYE:
                    // user input is bye: end programme
                    System.out.println("""
                            \t____________________________________________________________
                            \tBye. Hope to see you again soon!
                            \t____________________________________________________________
                            """);
                    return; // stop programme

                case LIST:

                    // user input is list: display past tasks
                    System.out.println("""
                            \t____________________________________________________________
                            \tHere are the tasks in your list:""");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("\t\t" + (i + 1) + "." + tasks.get(i));
                    }
                    System.out.println("\t____________________________________________________________\n");
                    break;

                case MARK:
                    try {
                        // user input is mark/unmark x: mark corresponding task as done or undone
                        String[] markWords = line.split(" ");
                        int index = Integer.parseInt(markWords[1]) - 1;

                        if (Objects.equals(markWords[0], "mark")) {
                            tasks.get(index).setDone(true);

                            System.out.println("\t____________________________________________________________\n"
                                    + "\tNice! I've marked this task as done:\n"
                                    + "\t\t" + tasks.get(index)
                                    + "\n\t____________________________________________________________\n");
                        } else {
                            tasks.get(index).setDone(false);

                            System.out.println("\t____________________________________________________________\n"
                                    + "\tOK, I've marked this task as not done yet:\n"
                                    + "\t\t" + tasks.get(index)
                                    + "\n\t____________________________________________________________\n");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("""
                                \t____________________________________________________________
                                \tHEY mark/unmark should be followed by a valid integer
                                \t____________________________________________________________
                                """);
                    } catch (NullPointerException | IndexOutOfBoundsException e) {
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

                        tasks.add(new Event(eventWords[0].replace("event", "").trim(), eventWords[1].replace("from ", "").trim(), eventWords[2].replace("to ", "").trim())); // add task to storage

                        System.out.println("\t____________________________________________________________\n"
                                + "\tGot it. I've added this task:\n"
                                + "\t\t" + tasks.getLast() + "\n"
                                + "\tNow you have " + tasks.size() + " tasks in the list.\n"
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

                        tasks.add(new Deadline(lineWords[0].replace("deadline", "").trim(), lineWords[1].trim())); // add task to storage

                        System.out.println("\t____________________________________________________________\n"
                                + "\tGot it. I've added this task:\n"
                                + "\t\t" + tasks.getLast() + "\n"
                                + "\tNow you have " + tasks.size() + " tasks in the list.\n"
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
                        // user is creating a new  Task.Todo
                        String string = line.replace("todo", "").trim();

                        if (string.isEmpty()) {
                            throw new IllegalArgumentException();
                        }

                        tasks.add(new Todo(string)); // add task to storage

                        System.out.println("\t____________________________________________________________\n"
                                + "\tGot it. I've added this task:\n"
                                + "\t\t" + tasks.getLast() + "\n"
                                + "\tNow you have " + tasks.size() + " tasks in the list.\n"
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

                case DELETE:
                    try {
                        String[] markWords = line.split(" ");
                        int index = Integer.parseInt(markWords[1]) - 1;
                        Task temp = tasks.get(index);

                        tasks.remove(index);


                        System.out.println("\t____________________________________________________________\n"
                                + "\tNoted. I've removed this task:\n"
                                + "\t\t" + temp + "\n"
                                + "\tNow you have " + tasks.size() + " tasks in the list.\n"
                                + "\t____________________________________________________________\n");

                    } catch (NumberFormatException e) {
                        System.out.println("""
                                \t____________________________________________________________
                                \tHEY delete should be followed by a valid integer
                                \t____________________________________________________________
                                """);
                    } catch (NullPointerException | IndexOutOfBoundsException e) {
                        System.out.println("""
                                \t____________________________________________________________
                                \tInteger provided does not have a corresponding task
                                \t____________________________________________________________
                                """);
                    }
                    break;
            }
        }
    }
}
