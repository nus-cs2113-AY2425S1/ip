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

            switch(input) {
                case "bye":
                    printByeMessage();
                    return;

                case "list":
                    System.out.println("_________________________________________________________");
                    for (int i = 0; i < taskCounter; i++) {
                        String output = (i + 1) + ".[ " + taskList[i].getStatusIcon() + "] "+ taskList[i].description;
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
