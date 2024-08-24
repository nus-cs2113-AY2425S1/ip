import java.util.Scanner;

public class Dobby {
    private static final String DASH_LINE = "____________________________________________________________";
    private static final int MAX_LIST_SIZE = 100;
    private static final Task[] list = new Task[MAX_LIST_SIZE];
    private static int listSize = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean saidBye = false;

        printWelcomeMessage();

        while (!saidBye){
            String line = in.nextLine().trim();
            if (line.equalsIgnoreCase("bye")){
                saidBye = true;
            } else if (line.equals("list")) {
                printList();
            } else if (line.startsWith("mark ")) {
                markTaskAsDone(line);
            } else{
                addItem(line);
            }
        }

        printGoodbyeMessage();
    }

    private static void printWelcomeMessage(){
        System.out.println("  " + DASH_LINE);
        System.out.println("    " + "Hello! Dobby is Dobby!");
        System.out.println("    " + "What can Dobby do for master?");
        System.out.println("  " + DASH_LINE);
    }

    private static void printList(){
        System.out.println("  " + DASH_LINE);
        System.out.println("    Here are the tasks in master's list:");
        for (int i = 1; i <= listSize; i++) {
            Task t = list[i-1];
            System.out.println("    " + i + ".[" + t.getStatusIcon() + "] " + t.getDescription());
        }
        System.out.println("  " + DASH_LINE);
    }

    private static void addItem(String line){
        list[listSize] = new Task(line);
        listSize++;
        System.out.println("  " + DASH_LINE);
        System.out.println("    added: " + line);
        System.out.println("  " + DASH_LINE);
    }

    private static void printGoodbyeMessage(){
        System.out.println("  " + DASH_LINE);
        System.out.println("    " + "Thank you master, Dobby is free!!!");
        System.out.println("  " + DASH_LINE);
    }

    private static void markTaskAsDone(String line){
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (taskNumber > 0 && taskNumber <= listSize){
            list[taskNumber-1].markAsDone();
            System.out.println("  " + DASH_LINE);
            System.out.println("    Dobby has magically marked this task as done: ");
            System.out.println("      " + "[" + list[taskNumber-1].getStatusIcon() + "] " + list[taskNumber-1].getDescription());
            System.out.println("  " + DASH_LINE);
        }
    }
}
