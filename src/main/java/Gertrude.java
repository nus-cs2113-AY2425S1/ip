import java.util.Scanner;

public class Gertrude {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final Integer MAXIMUM_TASKS = 100;
    public Integer task_counter = 0;
    Task[] tasks = new Task[MAXIMUM_TASKS];

    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printIntroduction() {
        System.out.println("Hello, I'm Gertrude.");
        System.out.println("What can I do for you?");
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void printList() {
        for (int i = 1; i <= taskCounter; i++) {
            System.out.print(i + ".");
            tasks[i-1].printTask();
        }
        System.out.println("You have " + taskCounter + " tasks in the list.");
    }

    public static void markTask(String lineInputArr[]) {
        int index = Integer.parseInt(lineInputArr[1]);
        if (index < 1 || index > taskCounter) {
            System.out.println("That is not a valid index.");
        } else if (lineInputArr[0].equals("mark")) {
            tasks[index - 1].markDone();
        } else {
            tasks[index-1].markNotDone();
        }
    }

    public static void addTask(Task task, String lineInput) {
        tasks[taskCounter] = task;
        taskCounter++;
        System.out.println("added: " + lineInput);
    }

    public static void addTodo(String lineInput) {
        Todo newTodo = new Todo(lineInput);
        addTask(newTodo, lineInput)
    }

    public static void addDeadline(String[] lineInputArr) {
        String description = "";
        String deadline = "";
        boolean isDeadline = false;
        for(int i = 1; i < lineInputArr.length; i++) {
            if (lineInputArr[i].equals("/by")) {
                isDeadline = true;
            } else if (!isDeadline) {
                description = description + lineInputArr[i];
            } else {
                deadline = deadline + lineInputArr[i];
            }
        }
        Deadline newDeadline = new Deadline(description, deadline);
        addTask(newDeadline, description);
    }

    public static void addEvent(String[] lineInputArr) {
        String description = "";
        String start = "";
        String end = "";
        String section = "description";
        for(int i = 1; i < lineInputArr.length; i++) {
            if (lineInputArr[i].equals("/from")) {
                section = "from";
            } else if (lineInputArr[i].equals("/to")) {
                section = "to";
            } else if (section.equals("description")) {
                description += lineInputArr[i];
            } else if (section.equals("from")) {
                start += lineInputArr[i];
            } else if (section.equals("to")) {
                end += lineInputArr[i];
            }
        }
        Event newEvent = new Event(description, start, end);
        addTask(newEvent, description);
    }


    public static void main(String[] args) {
        printIntroduction();
        boolean runLoop = true;
        while (runLoop) {
            String lineInput;
            Scanner in = new Scanner(System.in);
            lineInput = in.nextLine();
            String[] lineInputArr = lineInput.split(" ");
            printHorizontalLine();

            switch (lineInputArr[0]) {
            case "bye":
                printGoodbyeMessage();
                runLoop = false;
                break;
            case "list":
                printList();
                break;
            case "mark":
                markTask(lineInputArr);
                break;
            case "unmark":
                markTask(lineInputArr);
                break;
            case "todo":
                addTodo(lineInput);
                break;
            case "deadline":
                addDeadline(lineInputArr);
                break;
            case "event":
                addEvent(lineInputArr);
                break;
            default:
                System.out.println("That is not a valid input.");
                break;
            }
            printHorizontalLine();
            pass;
        }
    }
}
