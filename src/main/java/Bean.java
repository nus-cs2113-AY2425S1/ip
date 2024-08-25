import java.util.Scanner;

public class Bean {
    // Constants
    final static String SEPARATOR_LINE = "____________________________________________________________________\n";
    final static String INDENT = "  ";
    final static int MAX_LIST_COUNT = 100;
    final static String LOGO = "  ┏━┓\n" +
            "  ┃ ┃\n" +
            "  ┃ ┗━━┳━━━┳━━━━┳━━━┓\n" +
            "  ┃  ┏┓┃ ┃━┫ ┏┓ ┃ ┏┓ ┓ ┏━━━━━━┓\n" +
            "  ┃  ┗┛┃ ┃━┫ ┏┓ ┃ ┃┃ ┃ ┃• ᴗ • ┫\n" +
            "  ┗━━ ━┻━━━┻━┛┗━┻━┛┗━┛ ┗━━━━━━┛\n";;

    public static void greet() {
        System.out.println(SEPARATOR_LINE +
                LOGO +
                INDENT + "Howdy, mate! :) I am Bean, your personal assistant.\n" +
                INDENT + "Let me know how I can help you out!\n" +
                SEPARATOR_LINE
        );
    }

    public static void exit() {
        System.out.println(SEPARATOR_LINE +
                LOGO +
                INDENT + "Bye bye, glad I could help! See you soon? :'(\n" +
                SEPARATOR_LINE);
    }

    public static void echo(String string) {
        System.out.println(INDENT + string + SEPARATOR_LINE);
    }

    public static void printList(Task[] toDoList) {
        if (toDoList[0] == null) {
            System.out.println(SEPARATOR_LINE +
                    INDENT + "Nothing in your to do list!\n" + SEPARATOR_LINE);
            return;
        }

        System.out.println(SEPARATOR_LINE +
                INDENT + "TO DO LIST:\n");

        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] == null) {
                break;
            }
            System.out.println(INDENT + (i + 1) + ".[" + toDoList[i].getStatusIcon() + "]  " + toDoList[i].description);
        }
        System.out.println(SEPARATOR_LINE);
    }

    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);

        Task[] toDoList = new Task[MAX_LIST_COUNT];
        int count = 0;

        greet();

        while (count < MAX_LIST_COUNT) {
            userInput = in.nextLine();

            if (userInput.equals("list")) {
                printList(toDoList);
            } else if (userInput.equals("bye")) {
                // To exit
                break;
            } else if (userInput.contains("mark")) {
                // Either mark or unmark

                // Obtain task number
                String[] words = userInput.split(" ");
                // Take second word of input and trim any spaces then parse as int
                int taskNum = Integer.parseInt(words[1].trim());
                int taskIndex = taskNum - 1;

                if (userInput.contains("unmark")) {
                    // Unmark task as done
                    toDoList[taskIndex].markAsUndone();
                    System.out.println(SEPARATOR_LINE +
                            INDENT + "Task " + taskNum + " has been marked as UNDONE\n" +
                            SEPARATOR_LINE);
                } else {
                    // Mark task as done
                    toDoList[taskIndex].markAsDone();
                    System.out.println(SEPARATOR_LINE +
                            INDENT + "Task " + taskNum + " has been marked as DONE\n" +
                            SEPARATOR_LINE);
                }
            } else {
                // Add task
                toDoList[count] = new Task(userInput);

                System.out.println(SEPARATOR_LINE +
                        INDENT + "Task '" + userInput + "' has been added!\n" +
                        SEPARATOR_LINE);
                count++;
            }
        }
        exit();
    }
}