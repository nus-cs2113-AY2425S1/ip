import java.util.Scanner;

public class Gertrude {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final Integer MAXIMUM_TASKS = 100;

    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printIntroduction() {
        System.out.println("Hello, I'm Gertrude.");
        System.out.println("What can I do for you?");
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printList(Task[] tasks) {
        for (int i = 1; i <= Task.taskIndex; i++) {
            System.out.print(i + ".");
            tasks[i-1].printTask();
        }
        System.out.println("You have " + Task.taskIndex + " tasks in the list.");
    }

    public static void markTask(String[] lineInputArr, Task[] tasks) {
        int index = Integer.parseInt(lineInputArr[1]);
        if (index < 1 || index > Task.taskIndex) {
            System.out.println("That is not a valid index.");
        } else if (lineInputArr[0].equals("mark")) {
            tasks[index - 1].markDone();
        } else {
            tasks[index-1].markNotDone();
        }
    }

    public static void addTask(Task task, String lineInput, Task[] tasks) {
        tasks[Task.taskIndex] = task;
        Task.taskIndex += 1;
        System.out.println("added: " + lineInput);
    }

    public static void addTodo(String[] lineInputArr, Task[] tasks) {
        String name = "";
        for(int i = 1; i < lineInputArr.length; i++) {
            name += lineInputArr[i] + " ";
        }
        Todo newTodo = new Todo(name);
        addTask(newTodo, name, tasks);
    }

    public static void addDeadline(String[] lineInputArr, Task[] tasks) {
        String description = "";
        String deadline = "";
        boolean isDeadline = false;
        for(int i = 1; i < lineInputArr.length; i++) {
            if (lineInputArr[i].equals("/by")) {
                isDeadline = true;
            } else if (!isDeadline) {
                description = description + lineInputArr[i] + " ";
            } else {
                deadline = deadline + lineInputArr[i] + " ";
            }
        }
        Deadline newDeadline = new Deadline(description, deadline);
        addTask(newDeadline, description, tasks);
    }

    public static void addEvent(String[] lineInputArr, Task[] tasks) {
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
                description += lineInputArr[i] + " ";
            } else if (section.equals("from")) {
                start += lineInputArr[i] + " ";
            } else if (section.equals("to")) {
                end += lineInputArr[i] + " ";
            }
        }
        Event newEvent = new Event(description, start, end);
        addTask(newEvent, description, tasks);
    }


    public static void main(String[] args) {
        printIntroduction();
        Task[] tasks = new Task[MAXIMUM_TASKS];
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
                printList(tasks);
                break;
            case "mark", "unmark":
                markTask(lineInputArr, tasks);
                break;
            case "todo":
                addTodo(lineInputArr, tasks);
                break;
            case "deadline":
                addDeadline(lineInputArr, tasks);
                break;
            case "event":
                addEvent(lineInputArr, tasks);
                break;
            default:
                System.out.println("That is not a valid input.");
                break;
            }
            printHorizontalLine();
        }
    }
}
