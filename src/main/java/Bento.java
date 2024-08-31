import java.util.ArrayList;
import java.util.Scanner;

public class Bento {
    public static final String LOGO = "\t  ____             _        \n"
            + "\t | __ )  ___ _ __ | |_ ___  \n"
            + "\t |  _ \\ / _ \\ '_ \\| __/ _ \\ \n"
            + "\t | |_) |  __/ | | | || (_) |\n"
            + "\t |____/ \\___|_| |_|\\__\\___/ \n"
            + "\t                            \n";
    public static final String GREETING_MESSAGE = "\tKonichiwa! I am Bento, your personal assistant!\n\tHow can I help you with your tasks today?";
    public static final String LINE_MESSAGE = "\t____________________________________________________________";
    public static final String SAYONARA_MESSAGE = "\tThank you for working with me today! See you next time! Sayonara~";
    public static final String ADD_TASK_SUCCESS_MESSAGE = "\tRoger that! Successfully added task:";
    private final Scanner in = new Scanner(System.in);
    private boolean isExit = false;
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;

    public void printLogo() {
        System.out.print(LOGO);
    }

    public void sayKonichiwa() {
        printLine();
        printLogo();
        System.out.println(GREETING_MESSAGE);
        printLine();
    }

    public void printLine() {
        System.out.println(LINE_MESSAGE);
    }

    public void saySayonara() {
        isExit = true;
        printLine();
        System.out.println(SAYONARA_MESSAGE);
        printLine();
    }

    public String getUserInput() {
        return in.nextLine();
    }

//    public void echoMessage(String message) {
//        printLine();
//        System.out.println("\t" + message);
//        printLine();
//    }

    public void printAddTaskSuccessMessage(String task) {
        printLine();
        System.out.printf("%s\n\t\t%s\n%s", ADD_TASK_SUCCESS_MESSAGE, task, getTaskCountMessage());
        printLine();
    }

    public String getTaskCountMessage() {
        return String.format("\tYou currently have %d tasks! Way to go, you busy bee!\n", taskCount);
    }

    public void addTask(String input) {
        Task toAdd = new Task(input);
        tasks.add(toAdd);
        taskCount++;

        printAddTaskSuccessMessage(toAdd.toString());
    }

    public void addToDo(String input) {
        ToDo toAdd = new ToDo(input);
        tasks.add(toAdd);
        taskCount++;

        printAddTaskSuccessMessage(toAdd.toString());
    }

    private void listTasks() {
        printLine();
        System.out.println("\tHere is the list of your existing tasks!");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, tasks.get(i));
        }
        printLine();
    }

    private void markTaskAsDone(boolean isDone, String taskIndex) {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            updateTask(isDone, index);
            printLine();
            if (isDone) {
                printMarked(index);
            } else {
                printUnmarked(index);
            }
            printLine();
        } catch (NumberFormatException e) {
            printLine();
            printInvalidIndexMessage();
            printLine();
        } catch (IndexOutOfBoundsException e) {
            printLine();
            printNoTaskFoundMessage();
            printLine();
        }
    }

    private static void printNoTaskFoundMessage() {
        System.out.println("\tHmm... I don't think that task exists... Check again with list!");
    }

    private static void printInvalidIndexMessage() {
        System.out.println("\tHey! The index provided was not a number!");
    }

    private void printUnmarked(int index) {
        System.out.println("\tMaybe you're not quite ready for the task just yet. No worries, I'll be here to make sure you clear it.");
        System.out.printf("\t  [ ] %s\n", tasks.get(index).getTaskName());
    }

    private void printMarked(int index) {
        System.out.println("\tYou've crushed this task! I've gone ahead and marked it as done for you.");
        System.out.printf("\t\t%s\n", tasks.get(index));
    }

    private void updateTask(boolean isDone, int index) {
        tasks.get(index).setDone(isDone);
    }

    private static String[] getInputList(String input) {
        return input.split(" ");
    }

    private static String getTodo(String input) {
        return input.split("todo")[1];
    }

    public void handleUserInput(String input) {
        String[] inputList = getInputList(input);
        switch (inputList[0]) {
        case "bye":
            saySayonara();
            break;
        case "list":
            listTasks();
            break;
        case "mark":
            markTaskAsDone(true, inputList[1]);
            break;
        case "unmark":
            markTaskAsDone(false, inputList[1]);
            break;
        case "todo":
            addToDo(getTodo(input));
            break;
        default:
            addTask(input);
        }
    }

    public void run() {
        sayKonichiwa();
        while (!isExit) {
            String input = getUserInput();
            handleUserInput(input);
        }
    }
}