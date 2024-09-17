package king;

import java.util.ArrayList;
import java.util.Scanner;

public class King {
    private static final String NAME = "King";
    protected static int tasksCount = 0;
    protected static ArrayList<Task> tasks = new ArrayList<>();
    private final static String LINE = "____________________________________________________________\n";

    private static void toGreet() throws KingException {
        String logo =
                "| |/ /|_ _|| \\| | / _` |\n" +
                "| ' <  | | | .` || (_| |\n" +
                "|_|\\_\\|___||_|\\_| \\__,_|\n";

        System.out.println("\nHello from\n" + logo);

        System.out.println(" Hello! I'm " + NAME + "\n" +
                           " What can I do for you?\n" + LINE);

        toChat();
    }

    private static void toChat() throws KingException {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine().trim();
            try {
                handleUserInputExceptions(userInput);
            } catch (KingException e) {
                System.out.println(LINE + e.getMessage() + LINE);
                continue;
            }

            if (userInput.equals("bye")) {
                toExit();
                return;
            } else if (userInput.equalsIgnoreCase("list")) {
                printList();
            } else if (userInput.toLowerCase().startsWith("mark")) {
                try {
                    markTaskDone(parseTaskIndex(userInput));
                } catch (KingException e) {
                    System.out.println(LINE + e.getMessage() + LINE);
                }
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                try {
                    markTaskUndone(parseTaskIndex(userInput));
                } catch (KingException e) {
                    System.out.println(LINE + e.getMessage() + LINE);
                }
            } else if (userInput.toLowerCase().startsWith("todo")) {
                addToDoTask(userInput);
            } else if (userInput.toLowerCase().startsWith("deadline")) {
                addDeadlineTask(userInput);
            } else if (userInput.toLowerCase().startsWith("event")) {
                addEventTask(userInput);
            } else if (userInput.toLowerCase().startsWith("delete")) {
                try {
                    deleteTask(parseTaskIndex(userInput));
                } catch (KingException e) {
                    System.out.println(LINE + e.getMessage() + LINE);
                }
            }
        }
        toExit();
    }

    private static void toExit() {
        System.out.println(LINE + " Bye. Hope to see you again soon!\n" + LINE);
    }


    private static int parseTaskIndex(String text) throws KingException {
        int taskIndex = 0;
        try {
            taskIndex = Integer.parseInt(text.split(" ")[1]) - 1;

        } catch (NumberFormatException e) {
            throw new KingException("Your input can only be numbers!\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KingException("Have u forgotten to input the task number?\n");
        }

        return taskIndex;
    }

    private static void markTaskDone(int index) throws KingException {
        try {
            tasks.get(index).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new KingException("You cannot mark any task that's not on ur list!\n");
        }
    }

    private static void markTaskUndone(int index) throws KingException {
        try {
            tasks.get(index).markAsUndone();
        } catch (IndexOutOfBoundsException e) {
            throw new KingException("You cannot unmark any task that's not on ur list!\n");
        }
    }


    private static void printList() {
        if (tasksCount == 0) {
            System.out.println(LINE + "Your list is still empty!\n" + LINE);
            return;
        }

        System.out.println(LINE + "Here are the tasks in your list:");

        for (int i = 0; i < tasksCount; i++) {
            int indexNum = i + 1;
            System.out.println(indexNum + ". " + tasks.get(i));
        }

        System.out.println(LINE);
    }


    private static void addToDoTask(String text) {
        String[] taskSpecifics = text.split(" ");

        try {
            String taskContent = taskSpecifics[1];
            Task t = new Todo(taskContent);
            tasks.add(t);
            tasksCount += 1;
            printAddedTaskDescription(t);

        } catch (IndexOutOfBoundsException e) {
            System.out.println(LINE + "Have u forgotten the task content?\n" + LINE);
        }
    }

    private static void addDeadlineTask(String text) throws KingException {
        String[] taskSpecifics = text.split(" ");
        int seperationIndex = 0;
        String taskContent = "";
        String taskEndTime = "";
        boolean existBy = false;

        try {
            for (int i = 0; i < taskSpecifics.length; i++) {
                if (taskSpecifics[i].equals("/by")) {
                    seperationIndex = i;
                    existBy = true;
                }
            }


            if (!existBy) {
                throw new KingException("Deadline not detected. Please try again:)\n");
            }

            for (int i = 1; i < seperationIndex; i++) {
                taskContent += taskSpecifics[i];
                if (i < seperationIndex - 1) {
                    taskContent += " ";
                }
            }
            for (int i = seperationIndex + 1; i < taskSpecifics.length; i++) {
                taskEndTime += taskSpecifics[i];
                if (i < taskSpecifics.length - 1) {
                    taskEndTime += " ";
                }
            }
        } catch (KingException e) {
            System.out.println(LINE + e.getMessage() + LINE);
            return;
        }

        Task t = new Deadline(taskContent, taskEndTime);
        tasks.add(t);
        tasksCount += 1;
        printAddedTaskDescription(t);
    }

    private static void addEventTask(String text) throws KingException {
        String[] taskSpecifics = text.split(" ");
        int seperationIndexFirst = 0;
        int seperationIndexSecond = 0;
        String taskContent = "";
        String taskStartTime = "";
        String taskEndTime = "";
        boolean existFrom = false;
        boolean existTo = false;

        try {
            for (int i = 0; i < taskSpecifics.length; i++) {
                if (taskSpecifics[i].equals("/from")) {
                    seperationIndexFirst = i;
                    existFrom = true;

                } else if (taskSpecifics[i].equals("/to")) {
                    seperationIndexSecond = i;
                    existTo = true;
                }
            }

            if (!existTo && !existFrom) {
                throw new KingException("Both starting & ending time not detected. Please try again:)\n");
            }
            if (!existFrom) {
                throw new KingException("Starting time not detected. Please try again:)\n");
            } else if (!existTo) {
                throw new KingException("Ending time not detected. Please try again:)\n");
            }


            for (int i = 1; i < seperationIndexFirst; i++) {
                taskContent += taskSpecifics[i];
                if (i < seperationIndexFirst - 1) {
                    taskContent += " ";
                }
            }
            for (int i = seperationIndexFirst + 1; i < seperationIndexSecond; i++) {
                taskStartTime += taskSpecifics[i];
                if (i < seperationIndexSecond - 1) {
                    taskStartTime += " ";
                }
            }
            for (int i = seperationIndexSecond + 1; i < taskSpecifics.length; i++) {
                taskEndTime += taskSpecifics[i];
                if (i < taskSpecifics.length - 1) {
                    taskEndTime += " ";
                }
            }
        } catch (KingException e) {
            System.out.println(LINE + e.getMessage() + LINE);
            return;
        }

        Task t = new Event(taskContent, taskStartTime, taskEndTime);
        tasks.add(t);
        tasksCount += 1;
        printAddedTaskDescription(t);
    }

    private static void printAddedTaskDescription(Task t) {
        System.out.println(LINE + "Got it. I've added this task:\n   "
                           + t.getTaskDescription() +
                           "\nNow you have " + tasksCount + " tasks in the list.\n" + LINE);
    }

    private static void handleUserInputExceptions(String userInput) throws KingException {
        userInput = userInput.toLowerCase();

        if (userInput.isEmpty()) {
            throw new KingException("You have empty input! How can I help you?\n");
        } else if (!((userInput.startsWith("todo")) || userInput.startsWith("deadline")
                     || userInput.startsWith("event") || userInput.startsWith("list")
                     || userInput.startsWith("mark") || userInput.startsWith("unmark")
                     || userInput.startsWith("bye") || userInput.startsWith("delete"))) {
            throw new KingException("Please first tell me what type of task you are doing:)\n");
        }
    }

    private static void deleteTask(int index) throws KingException {
        try {
            String text = LINE + "Sure thing. I've removed this task:\n   "
                          + tasks.get(index).getTaskDescription() +
                          "\nNow you have " + (tasksCount - 1) + " tasks in the list.\n" + LINE;
            tasks.remove(index);
            tasksCount -= 1;
            System.out.println(text);
        } catch (IndexOutOfBoundsException e) {
            throw new KingException("The task you want to delete does not exist!\n");
        }
    }


    public static void main(String[] args) throws KingException {
        toGreet();
    }
}
