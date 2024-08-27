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

    public void printOneTask(int listNumber) {
        System.out.println("[" + taskList[listNumber].getStatusIcon() + "] "
                + taskList[listNumber].getTask());
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
        printOneTask(listNumber);
        printSeparator();
    }

    public void printTaskList() {
        printSeparator();
        int totalTasks = Task.getTotalTasks();
        System.out.println("You have " + totalTasks + " to-dos!");
        for (int i = 0; i < totalTasks; i += 1) {
            System.out.print(taskList[i].getId() + ".");
            printOneTask(i);
        }
        printSeparator();
    }

    public void addToList(String task) {
        int totalToDos = Task.getTotalTasks();
        taskList[totalToDos] = new Task(task);
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
