import java.util.Scanner;
public class Functions {

    protected static final Scanner in = new Scanner(System.in);

    public Functions() {
        printFunctions();
    }

    public void printFunctions(){
        System.out.println("Currently, I am able to execute the following functions:");
        System.out.println("1. Add tasks: I can add tasks to your task list.");
        System.out.println("   - Type command: todo <task>");
        System.out.println("   - Type command: deadline <task> /by <date>");
        System.out.println("   - Type command: event <task> /from <day> <start time> /to <end time>");
        System.out.println("2. Mark tasks as done:");
        System.out.println("   - Type command: mark <task number>");
        System.out.println("3. Mark tasks as not done:");
        System.out.println("   - Type command: unmark <task number>");
        System.out.println("_________________________________________________________");
    }

    public void taskmaster(){
        Task[] taskList = new Task[100];
        int taskCounter = 0;
        while(true) {
            String input = in.nextLine();
            int taskNumIndex;

            if (input.startsWith("mark ")){
                try {
                    taskNumIndex = Integer.parseInt(input.substring(5)) - 1;
                    if (taskNumIndex >= 0 && taskNumIndex < taskCounter) {
                        taskList[taskNumIndex].setDone();
                        System.out.println("_________________________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("[X] " + taskList[taskNumIndex].description);
                        System.out.println("_________________________________________________________");
                    }
                    else {
                        System.out.println("________________________________________________________");
                        System.out.println("Invalid task input. Please try again.");
                        System.out.println("________________________________________________________");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("_________________________________________________________");
                    System.out.println("Invalid task input. Please try again.");
                    System.out.println("Correct format: mark <int>");
                    System.out.println("_________________________________________________________");
                }
            }
            else if (input.startsWith("unmark ")) {
                try {
                    taskNumIndex = Integer.parseInt(input.substring(7)) - 1;
                    if (taskNumIndex >= 0 && taskNumIndex < taskCounter && taskList[taskNumIndex].isDone) {
                        taskList[taskNumIndex].setNotDone();
                        System.out.println("__________________________________________________________");
                        System.out.println("OK! I've marked this task as not done yet:");
                        System.out.println("[ ] " + taskList[taskNumIndex].description);
                        System.out.println("__________________________________________________________");
                    }
                    else {
                        System.out.println("________________________________________________________");
                        System.out.println("Invalid task input. Please try again.");
                        System.out.println("________________________________________________________");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("_________________________________________________________");
                    System.out.println("Invalid task input. Please try again.");
                    System.out.println("Correct format: unmark <int>");
                    System.out.println("_________________________________________________________");
                }
            }
            else {
                switch(input) {
                    case "bye":
                        Chatbot.printByeMessage();
                        return;

                    case "list":
                        System.out.println("_________________________________________________________");
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskCounter; i++) {
                            String output = (i + 1) + ".[" + taskList[i].getStatusIcon() + "] "+ taskList[i].description;
                            System.out.println(output);
                        }
                        System.out.println("_________________________________________________________");
                        break;

                    default:
                        System.out.println("_________________________________________________________");
                        taskList[taskCounter] = new Task(input);
                        taskCounter++;
                        System.out.println("added: " + input);
                        System.out.println("_________________________________________________________");
                }
            }
        }
    }

    public void echo(String input){
        System.out.println("_________________________________________________________");
        System.out.println(input);
        System.out.println("_________________________________________________________");
    }

}
