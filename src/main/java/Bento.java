import java.util.Scanner;

public class Bento {
    private Scanner in = new Scanner(System.in);
    private boolean isExit = false;
    private Task[] tasks = new Task[100];
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
        tasks[taskCount] = new Task(input);
        taskCount++;

        echoInput(String.format("Roger that! Successfully added task: %s", input));
    }

    public void listTasks() {
        printLine();
        System.out.println("\tHere is the list of your existing tasks!");
        for (int i = 0; i < taskCount; i++) {
            System.out.printf("\t%d. %s %s\n", i + 1, (tasks[i].isDone()) ? "[x]" : "[ ]", tasks[i].getTaskName());
        }
        printLine();
    }

    public void handleUserInput(String input) {
        String[] inputList = input.split(" ");
        switch (inputList[0]) {
        case "bye" :
            saySayonara();
            break;
        case "list":
            listTasks();
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