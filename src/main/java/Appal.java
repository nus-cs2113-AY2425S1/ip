import java.util.Scanner;

public class Appal {
    // Constants for commands
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final int COMMAND_INDEX = 0;

    // Attributes
    private boolean isExited = false;
    private Task[] taskList = new Task[100];

    public void welcomeUser() {
        String chatbot = "Appal";
        printSeparator();
        System.out.println("Heyo! I'm your pal, " + chatbot + "!");
        System.out.println("Let's get things rolling, what would you like to do today?");
        printSeparator();
    }

    public void printSeparator() {
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public void printReply(String reply) {
        printSeparator();
        System.out.println(reply + " - added to your to-do list, you can do it!");
        printSeparator();
    }

    public void printBye() {
        printSeparator();
        System.out.println("See ya! An Appal a day, keeps the boredom away!");
        printSeparator();
    }

    public void printNotice() {
        printSeparator();
        System.out.println("Oops! I don't recognise this command :(");
        printSeparator();
    }

    public void printOneTask(Task task) {
        System.out.println(task);
    }

    public void markTask(String instruction, boolean isMark) {
        String[] words = instruction.split(" ");
        int taskId = Integer.parseInt(words[1]);
        int listNumber = taskId - 1;
        taskList[listNumber].setDone(isMark);
        printSeparator();
        if (isMark) {
            System.out.println("Task done! One more step towards success :)");
        } else {
            System.out.println("What's next on the agenda? :D");
        }
        printOneTask(taskList[listNumber]);
        printSeparator();
    }

    public void printTaskList() {
        printSeparator();
        int totalTasks = Task.getTotalTasks();
        System.out.println("You have " + totalTasks + " to-dos!");
        for (int i = 0; i < totalTasks; i += 1) {
            System.out.print(taskList[i].getId() + ".");
            printOneTask(taskList[i]);
        }
        printSeparator();
    }

    public void addToDo(String instruction) {
        int totalToDos = Task.getTotalTasks();
        String task = instruction.replace("todo ", "");
        taskList[totalToDos] = new ToDo(task);
    }

    public void addDeadline(String instruction) {
        int totalToDos = Task.getTotalTasks();
        String[] words = instruction.split("/");
        String task = words[0].replace("deadline ", "");
        String by = words[1].replace("by", "").trim();
        taskList[totalToDos] = new Deadline(task, by);
    }

    public void addEvent(String instruction) {
        int totalToDos = Task.getTotalTasks();
        String[] words = instruction.split("/");
        String task = words[0].replace("event ", "");
        String from = words[1].replace("from", "");
        String to = words[2].replace("to", "");
        taskList[totalToDos] = new Event(task, from, to);
    }

    public void handleInput() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] words = line.split(" ");
        String command = words[COMMAND_INDEX];
        switch (command) {
        case COMMAND_BYE:
            isExited = true;
            printBye();
            break;
        case COMMAND_LIST:
            printTaskList();
            break;
        case COMMAND_TODO:
            addToDo(line);
            printReply(line);
            break;
        case COMMAND_DEADLINE:
            addDeadline(line);
            printReply(line);
            break;
        case COMMAND_EVENT:
            addEvent(line);
            printReply(line);
            break;
        case COMMAND_MARK:
            markTask(line, true);
            break;
        case COMMAND_UNMARK:
            markTask(line, false);
            break;
        default:
            printNotice();
            break;
        }
    }

    public void runAppal() {
        welcomeUser();
        while (!isExited) {
            handleInput();
        }
    }
}
