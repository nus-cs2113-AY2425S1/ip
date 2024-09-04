import java.util.Scanner;

public class Duke {

    //taskList variable for level-2 onwards
    private static Task[] taskList = new Task[100];
    //task count
    private static int taskCount = 0;


    //echo function for level-1
    public static void echo(String message) {
        System.out.println(message);
    }


    //add function for level-2
    public static void add(String task) {
        Task newTask = new Task(task);
        taskList[taskCount] = newTask;
        taskCount++;
        System.out.println("added: " + task);
    }


    //list function for level-2
    public static void list() {
        for (int i = 0; i < taskCount; i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(taskList[i].toString());
        }
    }


    //mark function for level-3
    public void mark(int index) {
        if (index > taskCount || index < 0) {
            System.out.println("Index out of bounds");
            return;
        }
        System.out.println("Nice! I've marked this task as done:");
        taskList[index - 1].markAsDone();
    }


    //unmark function for level-3
    public void unmark(int index) {
        if (index > taskCount || index < 0) {
            System.out.println("Index out of bounds");
            return;
        }
        System.out.println("Ok, I've marked this task as not done yet:");
        taskList[index - 1].markAsNotDone();

    }


    //add toDo task for level-4
    public static void addToDo(String[] inputComponent) {
        String description = "";

        for (int i = 1; i < inputComponent.length; i++) {
            description += inputComponent[i];
            description += " ";
        }

        taskList[taskCount] = new ToDo(description.trim());
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList[taskCount].toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }


    //add deadline task for level-4
    public static void addDeadline(String[] inputComponent) {
        String description = "";
        String by = "";
        int state = 0;//transition from "description" to "by" string


        for (int i = 1; i < inputComponent.length; i++) {
            if (inputComponent[i].equals("/by")) {
                state = 1;
            } else {
                if (state == 1) {
                    by += inputComponent[i];
                    by += " ";
                } else {
                    description += inputComponent[i];
                    description += " ";
                }
            }
        }


        taskList[taskCount] = new Deadline(description.trim(), by.trim());
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList[taskCount].toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }


    //add event task for level-4
    public static void addEvent(String[] inputComponent) {
        String description = "";
        String from = "";
        String to = "";
        int state = 0;//transition from "description" to "from" to "to" string


        for (int i = 1; i < inputComponent.length; i++) {
            if (inputComponent[i].equals("/from")) {
                state = 1;
            } else if (inputComponent[i].equals("/to")) {
                state = 2;
            } else {
                if (state == 1) {
                    from += inputComponent[i];
                    from += " ";
                } else if (state == 2) {
                    to += inputComponent[i];
                    to += " ";
                } else {
                    description += inputComponent[i];
                    description += " ";
                }
            }
        }


        taskList[taskCount] = new Event(description.trim(), from.trim(), to.trim());
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList[taskCount].toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }


    //main function to execute the chatbot
    public void execute() {
        System.out.println("Hello I'm Lambo");
        System.out.println("What can I do for you?");
        Scanner inputReader = new Scanner(System.in);//scanner for receiving input


        //Super loop used for receiving inputs continuously and response back to user
        SuperLoop:
        while (true) {
            String input = inputReader.nextLine();
            String[] inputComponent = input.split(" ");

            //switch case based on the first word of input line
            switch (inputComponent[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break SuperLoop;
                case "list":
                    list();
                    break;
                case "mark":
                    mark(Integer.parseInt(inputComponent[1]));
                    break;
                case "unmark":
                    unmark(Integer.parseInt(inputComponent[1]));
                    break;
                case "todo":
                    addToDo(inputComponent);
                    break;
                case "deadline":
                    addDeadline(inputComponent);
                    break;
                case "event":
                    addEvent(inputComponent);
                    break;
                default:
                    if (input.isEmpty()) {
                        continue;//handle exception when the input is empty
                    }
                    add(input);//default case is add task
                    break;
            }
        }
    }
}
