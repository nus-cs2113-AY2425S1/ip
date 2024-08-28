import java.util.Scanner;

public class Doot {
    private static final String DIVIDER = "____________________________________________________________\n\n";
    private static String[] taskList = new String[100];
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
        switch(command){
            case ("list"):
            printList();
                break;
            default:
                addToList(command);
                break;
        }
    }

    public static void addToList(String toAdd){
        taskList[taskIdx] = toAdd;
        taskIdx++;
        System.out.print(DIVIDER + "added: " + toAdd + "\n" + DIVIDER);
    }

    public static void printList(){
        System.out.print(DIVIDER);
        int curIdx = 1;
        while (curIdx != taskIdx){
            System.out.println(curIdx + ". " + taskList[curIdx-1]);
            curIdx++;
        }
        System.out.print(DIVIDER);

    }
}
