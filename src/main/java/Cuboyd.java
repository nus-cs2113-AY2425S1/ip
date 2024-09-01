import java.util.ArrayList;
import java.util.Scanner;

public class Cuboyd {
    public static void main(String[] args) {
        String name = "Cuboyd";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        // Initialise List
        ArrayList<Task> tasks = new ArrayList<>();

        // For Marking/ Unmarking
        Task currentTask;
        int index;

        // Command Entry
        String line;
        String[] lineArgs;
        Scanner scanner = new Scanner(System.in);
        boolean isAskingInput = true;
        while (isAskingInput){
            System.out.print("> ");
            line = scanner.nextLine();
            lineArgs = line.split(" ");
            switch(lineArgs.length > 0 ? lineArgs[0] : ""){
                case "list":
                    for (int currentItemIndex = 0; currentItemIndex< tasks.size(); currentItemIndex++){
                        System.out.println(String.valueOf(currentItemIndex+1) + "." + tasks.get(currentItemIndex));
                    }
                    break;
                case "mark":
                    if (lineArgs.length < 2){
                        System.out.println("No index was given!");
                        break;
                    }
                    try {
                        index = Integer.parseInt(lineArgs[1]) - 1;
                    } catch (NumberFormatException e){
                        System.out.println("That is not a valid index!");
                        break;
                    }
                    if (index < 0 || index >= tasks.size()){
                        System.out.println("That is not a valid index!");
                        break;
                    }
                    currentTask = tasks.get(index);
                    currentTask.markAsDone();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + currentTask);
                    break;
                case "unmark":
                    if (lineArgs.length < 2){
                        System.out.println("No index was given!");
                        break;
                    }
                    try {
                        index = Integer.parseInt(lineArgs[1]) - 1;
                    } catch (NumberFormatException e){
                        System.out.println("That is not a valid index!");
                        break;
                    }
                    if (index < 0 || index >= tasks.size()){
                        System.out.println("That is not a valid index!");
                        break;
                    }
                    currentTask = tasks.get(index);
                    currentTask.markAsUndone();

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + currentTask);
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    isAskingInput = false;
                    break;
                default:
                    tasks.add(new Task(line));
                    System.out.println("added: " + line);
                    break;
            }
        }
    }
}