import java.util.Scanner;
public class Functions {

    protected static final Scanner in = new Scanner(System.in);
    private static final String SEPARATOR = "_________________________________________________________";
    private Task[] taskList = new Task[100];
    private int taskCounter = 0;

    public Functions() {}

    public void printFunctions(){
        print(
                "Currently, I am able to execute the following functions:",
                "1. Add tasks: I can add tasks to your task list.",
                "   - Type command: <task>",
                "   - Type command: todo <task>",
                "   - Type command: deadline <task> /by <date>",
                "   - Type command: event <task> /from <day> <start time> /to <end time>",
                "2. Mark tasks as done:",
                "   - Type command: mark <task number>",
                "3. Mark tasks as not done:",
                "   - Type command: unmark <task number>",
                "4. List all tasks.",
                "   - Type command: list",
                "5. Be a parrot!",
                "   - Type command: echo",
                "6. Shut myself down. Use this if you no longer need me :(",
                "   - Type command: bye"
        );
    }

    public void print(String... messages){
        System.out.println(SEPARATOR);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(SEPARATOR);
    }

    public void taskmaster() {
        while (true) {
            String input = in.nextLine();
            processCommand(input);
        }
    }

    private void processCommand(String input) {
        if (input.startsWith("mark") || input.startsWith("unmark")) {
            handleMarking(input);
        }
        else {
            switch (input) {
            case "bye":
                Chatbot.printByeMessage();
                System.exit(0);
                // Fallthrough

            case "list":
                listTasks();
                break;

            case "echo":
                echo();
                break;

            default:
                addTask(input);
                break;
            }
        }
    }

    private void handleMarking(String input) {
        try {
            int taskNumIndex = Integer.parseInt(input.substring(input.indexOf(' ') + 1)) - 1;
            if (taskNumIndex >= 0 && taskNumIndex < taskCounter) {
                if (input.startsWith("mark")) {
                    taskList[taskNumIndex].setDone();
                    print("Nice! I've marked this task as done:", taskStatus(taskNumIndex));
                } else {
                    taskList[taskNumIndex].setNotDone();
                    print("OK! I've marked this task as not done yet:", taskStatus(taskNumIndex));
                }
            } else {
                print("Invalid task input. Please try again.");
            }
        } catch (NumberFormatException e) {
            print("Invalid task input. Please try again.", "Correct format: mark <int> / unmark <int>");
        } catch (IllegalArgumentException e) {
            print("Task number cannot be empty!");
        }
    }

    public String taskStatus(int index){
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

        try {
            if (input == null || input.trim().isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty!");
            }
            else if (input.trim().equals("todo") || input.trim().equals("deadline") || input.trim().equals("event")){
                throw new IllegalArgumentException("Description cannot be empty!");
            } else if (input.startsWith("todo ")){
                taskList[taskCounter++] = new ToDos(input);
            } else if (input.startsWith("deadline ")){
                taskList[taskCounter++] = new Deadlines(input);
            } else if (input.startsWith("event ")){
                taskList[taskCounter++] = new Events(input);
            } else if (input.startsWith("task ")){
                taskList[taskCounter++] = new Task(input);
            } else{
                throw new IllegalArgumentException("I have no idea what this command is");
            }

            print("Got it. I've added this task:",
                    "  " + taskStatus(taskCounter - 1),
                    "Now you have %d task(s) in the list".formatted(taskCounter));
        } catch (IllegalArgumentException e) {
            print(e.getMessage());
        }
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
            print(input);
        }
    }

}
