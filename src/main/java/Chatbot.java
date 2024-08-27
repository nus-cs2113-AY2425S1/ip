import java.util.Scanner;

public class Chatbot {
    private Scanner in;

    // Constructor for chatbot
    public Chatbot() {
        this.in = new Scanner(System.in);
    }

    // Starting Chatbot
    public void start() {
        printWelcomeMessage();
        taskmaster();
    }

    // Welcome Screen
    private void printWelcomeMessage() {
        System.out.println("_________________________________________________________");
        System.out.println("Hello! I'm Uranus, the only Chatbot you'll ever need.");
        System.out.println("How can I be of service?");
        System.out.println("_________________________________________________________");
    }

    // Goodbye Screen
    private void printByeMessage() {
        System.out.println("_________________________________________________________");
        System.out.println("Bye. Hope to see you again!");
        System.out.println("_________________________________________________________");
    }

    // List
    private void taskmaster(){
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
                        printByeMessage();
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

    // Echo
    private void echo(){
        while(true){
            String input = in.nextLine();
            if(input.equals("bye")){
                printByeMessage();
                break;
            }
            System.out.println("_________________________________________________________");
            System.out.println(input);
            System.out.println("_________________________________________________________");
        }
    }

}
