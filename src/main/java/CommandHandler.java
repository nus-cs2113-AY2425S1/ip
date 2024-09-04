public class CommandHandler {
    private Task[] tasks;
    private int taskCount;

    public CommandHandler(Task[] tasks, int taskCount) {
        this.tasks = tasks;
        this.taskCount = taskCount;
    }

    public void handleCommand(Command command, String line) {
        switch (command) {
        case BYE:
            printByeMessage();
            break;
        case LIST:
            handleList();
            break;
        case MARK:
            handleMark(parseIndex(line));
            break;
        case UNMARK:
            handleUnmark(parseIndex(line));
            break;
        case TODO:
            handleTodo(line);
            break;
        case DEADLINE:
            handleDeadline(line);
            break;
        case EVENT:
            handleEvent(line);
            break;
        default:
            System.out.println("Unknown command.");
            break;
        }
    }

    private void printByeMessage() {
        System.out.println("Catch you on the flip cuh");
    }

    private int parseIndex(String line) {
        String[] parts = line.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    private void handleList() {
        System.out.println("You got " + taskCount + " task(s)");
        int listCount = 0;
        while (tasks[listCount] != null) {
            System.out.println(listCount + 1 + ". " + tasks[listCount++]);
        }
    }

    private void handleMark(int index) {
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsDone();
            System.out.println("Good shit kid! I've marked this task as done:");
            System.out.println("  " + tasks[index]);
        } else {
            System.out.println("Task not found.");
        }
    }

    private void handleUnmark(int index) {
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsNotDone();
            System.out.println("Get yo shit together son, this task aint done yet:");
            System.out.println("  " + tasks[index]);
        } else {
            System.out.println("Task not found.");
        }
    }

    private void handleTodo(String line) {
        String taskDescription = line.substring(line.indexOf(" ") + 1);
        ToDo todo = new ToDo(taskDescription);
        tasks[taskCount++] = todo;
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
        System.out.println("You got " + taskCount + " task(s)");
    }

    private void handleDeadline(String line) {
        if (!line.contains("/by")) {
            System.out.println("Please specify the deadline using '/by'.");
            return;
        }

        String[] deadlineParts = line.split("/by", 2);
        if (deadlineParts.length < 2) {
            System.out.println("Please provide a valid deadline.");
            return;
        }

        String taskDescription = deadlineParts[0].trim().substring(9);
        String byWhen = deadlineParts[1].trim();

        Deadline deadline = new Deadline(taskDescription, byWhen);
        tasks[taskCount++] = deadline;

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + deadline);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    private void handleEvent(String line) {
        if (!line.contains("/from") || !line.contains("/to")) {
            System.out.println("Please specify the event time using '/from' and '/to'.");
            return;
        }

        String[] eventParts = line.split("/from", 2);
        if (eventParts.length < 2) {
            System.out.println("Please provide a valid event time.");
            return;
        }

        String descriptionAndTo = eventParts[0].trim().substring(6);
        String[] timeParts = eventParts[1].split("/to", 2);
        if (timeParts.length < 2) {
            System.out.println("Please provide a valid event end time.");
            return;
        }

        String fromTime = timeParts[0].trim();
        String toTime = timeParts[1].trim();

        Event event = new Event(descriptionAndTo, fromTime, toTime);
        tasks[taskCount++] = event;

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + event);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
}
