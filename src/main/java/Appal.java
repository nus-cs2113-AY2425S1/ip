import java.util.Scanner;

public class Appal {
    private boolean isExited = false;
    private Task[] taskList = new Task[100];

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
            System.out.println("Great job! One more step towards success :)");
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

    public void addToList(String task) {
        int totalToDos = Task.getTotalTasks();
        taskList[totalToDos] = new Task(task);
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
        String by = words[1].replace("by ", "");
        taskList[totalToDos] = new Deadline(task, by);
    }

    public void addEvent(String instruction) {
        int totalToDos = Task.getTotalTasks();
        String[] words = instruction.split("/");
        String task = words[0].replace("event ", "");
        String from = words[1].replace("from ", "");
        String to = words[2].replace("to ", "");
        taskList[totalToDos] = new Event(task, from, to);
    }

    public void handleInput() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        if (line.equals("bye")) {
            isExited = true;
            printBye();
        } else if (line.equals("list")) {
            printTaskList();
        } else if (line.contains("unmark")) {
            markTask(line, false);
        } else if (line.contains("mark")) {
            markTask(line, true);
        } else if (line.contains("todo")) {
            addToDo(line);
            printReply(line);
        } else if (line.contains("deadline")) {
            addDeadline(line);
            printReply(line);
        } else if (line.contains("event")) {
            addEvent(line);
            printReply(line);
        } else {
            addToList(line);
            printReply(line);
        }
    }

    public void runAppal() {
        String chatbot = "Appal";
        printSeparator();
        System.out.println("Heyo! I'm your pal, " + chatbot + "!");
        System.out.println("Let's get things rolling, what would you like to do today?");
        printSeparator();
        while (!isExited) {
            handleInput();
        }
   }
}
