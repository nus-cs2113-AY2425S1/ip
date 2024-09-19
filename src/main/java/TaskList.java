public class TaskList {
    private static final int MAXTASKS = 100;

    public static Task[] tasks = new Task[MAXTASKS];  // Array to store tasks
    public static int taskCounter = 0;  // Counter to track current number of tasks

    public static void listTasks() {
        System.out.println("Here's what you've got in your list:");
        for (int i = 1; i <= taskCounter; i++) {

            // Print generic task description
            System.out.print(i+". " + tasks[i-1].getTypeIcon()
                    + tasks[i-1].getStatusIcon() + tasks[i-1].getTaskName());

            // Print specific task-type information
            switch (tasks[i-1].getTypeIcon()) {
                case "[D]":
                    System.out.println(" (By: " + tasks[i-1].getBy() + ")");
                    continue;

                case "[T]":
                    System.out.println();
                    continue;

                case "[E]":
                    System.out.println(" (From: " + tasks[i-1].getEventStart()
                            + " To: " + tasks[i-1].getEventEnd() + ")");
                    continue;

                default:
                    System.out.println(" (ERROR: OTHER TYPE)");
            }
        }
        Terri.printDivider();
    }

    public static void addToDo(String newToDo) {
        if (checkTasklistCapacity()) {
            return;
        }
        tasks[taskCounter++] = new ToDo(newToDo);
        System.out.println("Just added: " + newToDo + " to your list as a ToDo!");
        printNumberOfTasks();
        Terri.printDivider();
    }

    // Parse deadline information from user input and log Deadline
    public static void handleDeadline(String[] keyWord) {
        String newBy = null;
        String newDeadline = null;
        StringBuilder tempDeadlineInfo = new StringBuilder();

        // Iterate through user input to concatenate
        // deadline description/date information
        for (int i = 1; i <= keyWord.length; i++) {
            if (keyWord[i].equals("/by")) {
                newBy = Terri.extractSubArray(keyWord, i+1, keyWord.length);
                newDeadline = tempDeadlineInfo.toString().trim();
                break;
            }
            tempDeadlineInfo.append(keyWord[i]).append(" ");
        }

        TaskList.addDeadline(newDeadline, newBy);
    }

    public static void addDeadline(String newDeadline, String newBy) {
        if (checkTasklistCapacity()) {
            return;
        }
        tasks[taskCounter++] = new Deadline(newDeadline, newBy);
        System.out.println("Just added: '" + newDeadline + "' to your list as a Deadline!");
        printNumberOfTasks();
        Terri.printDivider();
    }

    // Parse event information from user input and log event
    public static void handleEvent(String[] keyWord) {
        int startIdx = 0;
        int endIdx = 0;

        // Locate time information in user-input
        for (int i = 0; i < keyWord.length; i++) {
            if (keyWord[i].equals("/from")) startIdx = i;
            if (keyWord[i].equals("/to")) {
                endIdx = i;
                break;
            }
        }

        String newDescription = Terri.extractSubArray(keyWord, 1, startIdx);
        String newStart = Terri.extractSubArray(keyWord, startIdx + 1, endIdx);
        String newEnd = Terri.extractSubArray(keyWord, endIdx + 1, keyWord.length);

        TaskList.addEvent(newDescription, newStart, newEnd);
    }


    public static void addEvent(String newEvent, String From, String To) {
        if (checkTasklistCapacity()) {
            return;
        }
        tasks[taskCounter++] = new Event(newEvent, From, To);
        System.out.println("Just added: '" + newEvent + "' to your list as an Event!");
        printNumberOfTasks();
        Terri.printDivider();
    }

    // Updates task isDone field to be completed
    public static void markDone(int taskIndex) {
        tasks[taskIndex].setDone(true);
        System.out.println("Just marked that task completed!");
        System.out.println((taskIndex+1)+". "
                + tasks[taskIndex].getTypeIcon()
                + tasks[taskIndex].getStatusIcon()
                + tasks[taskIndex].getTaskName());
        Terri.printDivider();
    }

    // Updates task isDone field to be not completed
    public static void markNotDone(int taskIndex) {
        tasks[taskIndex].setDone(false);
        System.out.println("Just marked that task as not completed!");
        System.out.println((taskIndex+1)+". "
                + tasks[taskIndex].getTypeIcon()
                + tasks[taskIndex].getStatusIcon()
                + tasks[taskIndex].getTaskName());
        Terri.printDivider();
    }

    private static void printNumberOfTasks() {
        if (taskCounter == 1) {
            System.out.println("There is now (1) logged task/event.");
        } else {
            System.out.println("There are now (" + taskCounter + ") logged tasks/events.");
        }
    }

    // Returns true if at Tasklist capacity has been reached
    private static boolean checkTasklistCapacity() {
        if (taskCounter >= MAXTASKS) {
            System.out.println("Maximum number of items (" + MAXTASKS + ") has been reached");
            System.out.println("Please delete a task in order to add an item.");
            return true;
        }
        return false;
    }


}
