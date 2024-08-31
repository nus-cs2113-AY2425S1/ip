import java.util.Scanner;
public class Functions {

    protected static final Scanner in = new Scanner(System.in);
    private static final String SEPARATOR = "_________________________________________________________";
    private Task[] taskList = new Task[100];
    private int taskCounter = 0;

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
        System.out.println("4. List all tasks.");
        System.out.println("   - Type command: list");
        System.out.println("5. Be a parrot!");
        System.out.println("   - Type command: echo");
        System.out.println("6. Shut myself down. Use this if you no longer need me :(");
        System.out.println("   - Type command: bye");
        System.out.println(SEPARATOR);
    }

    public void taskmaster() {
        while (true) {
            String input = in.nextLine();
            processCommand(input);
        }
    }

    private void processCommand(String input) {
        if (input.startsWith("mark ") || input.startsWith("unmark ")) {
            handleMarking(input);
        }
        else {
            switch (input) {
                case "bye":
                    Chatbot.printByeMessage();
                    System.exit(0);

                case "list":
                    listTasks();
                    break;

                case "echo":
                    echo();
                    break;

                default:
                    addTask(input);
            }
        }
    }

    private void handleMarking(String input) {
        try {
            int taskNumIndex = Integer.parseInt(input.substring(input.indexOf(' ') + 1)) - 1;
            if (taskNumIndex >= 0 && taskNumIndex < taskCounter) {
                if (input.startsWith("mark ")) {
                    taskList[taskNumIndex].setDone();
                    System.out.println(SEPARATOR);
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    taskList[taskNumIndex].setNotDone();
                    System.out.println(SEPARATOR);
                    System.out.println("OK! I've marked this task as not done yet:");
                }
                System.out.println(taskStatus(taskNumIndex));
                System.out.println(SEPARATOR);
            } else {
                System.out.println(SEPARATOR);
                System.out.println("Invalid task input. Please try again.");
                System.out.println(SEPARATOR);
            }
        } catch (NumberFormatException e) {
            System.out.println(SEPARATOR);
            System.out.println("Invalid task input. Please try again.");
            System.out.println("Correct format: mark <int>");
            System.out.println(SEPARATOR);
        }
    }

    public String taskStatus(int index){
        // N tag below in [N] is for no-tag (default setting)
        return taskList[index].getTaskStatus();
    }

    private void listTasks() {
        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; i++) {
            System.out.println((i + 1) + ". " + taskStatus(i));
        }
        System.out.println(SEPARATOR);
    }

    private void addTask(String input) {
        System.out.println(SEPARATOR);
        if (input.startsWith("todo ")){
            taskList[taskCounter++] = new ToDos(input);
        }
        else if (input.startsWith("deadline ")){
            taskList[taskCounter++] = new Deadlines(input);
        }
        else if (input.startsWith("event ")){
            taskList[taskCounter++] = new Events(input);
        }
        else{
            taskList[taskCounter++] = new Task(input);
        }

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskStatus(taskCounter - 1));
        System.out.println("Now you have %d task(s) in the list".formatted(taskCounter));
        System.out.println(SEPARATOR);
    }

    private void echo(){
        while(true){

            System.out.println("Say anything! If you are no longer bored, type exit !");
            String input = in.nextLine();
            if(input.equals("exit")){
                System.out.println(SEPARATOR);
                printFunctions();
                break;
            }
            System.out.println(SEPARATOR);
            System.out.println(input);
            System.out.println(SEPARATOR);
        }
    }

}
