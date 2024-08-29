import java.util.Scanner;
import java.util.regex.*;

public class Doot {
    private static final String DIVIDER = "____________________________________________________________\n\n";
    private static Task[] taskList = new Task[100];
    private static int taskIdx = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(DIVIDER + "Hello! I'm  Doot\nWhat can I do for you?\n" + DIVIDER);
        String currentInput = scanner.nextLine();
        while (!currentInput.equals("bye")){
            findCommand(currentInput);
            currentInput = scanner.nextLine();
        }
        System.out.print(DIVIDER + "Bye. Hope to see you again soon!" + "\n" + DIVIDER);
        scanner.close();
    }

    public static void findCommand(String command){
        String wordDigit = "\\w+ \\d{1,3}$";
        int digit = -1;
        if (command.matches(wordDigit)){
            digit = Integer.parseInt(command.substring(command.indexOf(" ") + 1));
            command = command.substring(0, command.indexOf(" "));
        }
        switch(command){
            case ("list"):
                printList();
                break;
            case ("mark"):
                markTask(digit);
                break;
            case ("unmark"):
                unmarkTask(digit);
                break;
            default:
                addToList(command);
                break;
        }
    }

    public static void markTask(int idx){
        taskList[idx-1].markDone();
        System.out.println(DIVIDER + "Nice! I've marked this task as done: " + taskList[idx-1].getDescription() + "\n" + DIVIDER);
    }

    public static void unmarkTask(int idx){
        taskList[idx-1].markUnDone();
        System.out.println(DIVIDER + "OK, I've marked this task as not done yet: " + taskList[idx-1].getDescription() + "\n" + DIVIDER);
    }

    public static void addToList(String toAdd){
        taskList[taskIdx] = new Task(toAdd);
        taskIdx++;
        System.out.print(DIVIDER + "added: " + toAdd + "\n" + DIVIDER);
    }

    public static void printList(){
        System.out.print(DIVIDER);
        int curIdx = 1;
        while (curIdx != taskIdx + 1){
            Task curTask = taskList[curIdx-1];
            System.out.println(curIdx + ". " + curTask.getStatusIcon() + curTask.getDescription());
            curIdx++;
        }
        System.out.print(DIVIDER);

    }
}
