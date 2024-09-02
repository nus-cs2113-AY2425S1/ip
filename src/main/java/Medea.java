import java.util.Scanner;

public class Medea {

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String input = scanner.nextLine();
        while(!input.equals("bye")){
            handleInput(taskList, input);
            input = scanner.nextLine();
        }
        goodbye();
    }

    private static void greet(){
        System.out.println("Hello! I'm Medea.");
        System.out.println("What can I do for you?");
    }

    private static void goodbye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void handleInput(TaskList taskList, String input){
        String[] inputArguments = input.split(" ");
        String inputCommand = inputArguments[0];
        switch(inputCommand){
            case "list":
                taskList.listTasks();
                break;
            case "mark":
                int markIndex = Integer.parseInt(inputArguments[1]) - 1;
                taskList.markTask(markIndex);
                break;
            case "unmark":
                int unmarkIndex = Integer.parseInt(inputArguments[1]) - 1;
                taskList.unmarkTask(unmarkIndex);
                break;
            default:
                taskList.addTask(input);
                break;
        }
    }
}