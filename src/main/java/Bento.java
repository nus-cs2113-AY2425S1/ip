import java.util.ArrayList;
import java.util.Scanner;

public class Bento {
    private Scanner in = new Scanner(System.in);
    private boolean isExit = false;
    private ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;

    public void printLogo() {
        String logo = "\t  ____             _        \n"
                + "\t | __ )  ___ _ __ | |_ ___  \n"
                + "\t |  _ \\ / _ \\ '_ \\| __/ _ \\ \n"
                + "\t | |_) |  __/ | | | || (_) |\n"
                + "\t |____/ \\___|_| |_|\\__\\___/ \n"
                + "\t                            \n";
        System.out.print(logo);
    }


    public void sayKonichiwa() {
        printLine();
        printLogo();
        System.out.println("\tKonichiwa! I am Bento, your personal assistant!\n\tHow can I help you with your tasks today?");
        printLine();
    }

    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void saySayonara() {
        isExit = true;
        printLine();
        System.out.println("\tThank you for working with me today! See you next time! Sayonara~");
        printLine();
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void echoInput(String input) {
        printLine();
        System.out.println("\t" + input);
        printLine();
    }

    public void addTask(String input) {
        tasks.add(new Task(input));
        taskCount++;

        echoInput(String.format("Roger that! Successfully added task: %s", input));
    }

    public void listTasks() {
        printLine();
        System.out.println("\tHere is the list of your existing tasks!");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s %s\n", i + 1, (tasks.get(i).isDone()) ? "[x]" : "[ ]", tasks.get(i).getTaskName());
        }
        printLine();
    }

    public void markTaskAsDone(boolean isDone, String taskIndex) {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            tasks.get(index).setDone(isDone);
            printLine();
            if (isDone) {
                System.out.println("\tYou've crushed this task! I've gone ahead and marked it as done for you.");
                System.out.printf("\t  [x] %s\n", tasks.get(index).getTaskName());
            } else {
                System.out.println("\tMaybe you're not quite ready for the task just yet. No worries, I'll be here to make sure you clear it.");
                System.out.printf("\t  [ ] %s\n", tasks.get(index).getTaskName());
            }
            printLine();
        } catch (NumberFormatException e) {
            printLine();
            System.out.println("\tHey! The index provided was not a number!");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            printLine();
            System.out.println("\tHmm... I don't think that task exists... Check again with list!");
            printLine();
        }
    }

    public void handleUserInput(String input) {
        String[] inputList = input.split(" ");
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