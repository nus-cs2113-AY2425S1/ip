package lovespiritual;

import lovespiritual.exception.lovespiritualException;
import lovespiritual.task.Deadline;
import lovespiritual.task.Event;
import lovespiritual.task.Task;
import lovespiritual.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class lovespiritual {
    public static final String SEPARATOR = "_".repeat(30);
    public static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        int taskCount = 0; // count the number of tasks added in the array

        printWelcomeScreen();

        // loop that keeps recurring when the program is running
        while (true) {
            String input = in.nextLine().trim();

            try {
                if (input.equalsIgnoreCase("bye")) {
                    printExitScreen();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    printList(taskCount, isMarked, taskTypes, tasks);
                } else if (input.startsWith("mark")) {
                    markTask(input, taskCount, isMarked, tasks);
                } else if (input.startsWith("unmark")) {
                    unmarkTask(input, taskCount, isMarked, tasks);
                } else if (input.startsWith("todo")) {
                    taskCount = todo(input, tasks, taskCount);
                } else if (input.startsWith("deadline")) {
                    taskCount = deadline(input, taskCount, tasks);
                } else if (input.startsWith("event")) {
                    taskCount = event(input, tasks, taskCount);
                } else {
                    throw new lovespiritualException("(^_^) Let's get started with a command!");
                }
            } catch (lovespiritualException e) {
                System.out.println(SEPARATOR);
                System.out.println(e.getMessage());
                System.out.println(SEPARATOR);
            } catch (Exception e) {
                System.out.println(SEPARATOR);
                System.out.println("Oh no! (＞﹏＜) Something went a little wrong...");
                System.out.println(SEPARATOR);
            }
        }
    }

    private static int event(String input, ArrayList <Task> tasks) throws lovespiritualException {
        String fullTaskDescription = input.substring("event".length()).trim();
        if (fullTaskDescription.isEmpty()) {
            throw new lovespiritualException("Uh-oh! (・_・;) Your event description seems to be missing!");
        }
        if (!fullTaskDescription.contains("from")) {
            throw new lovespiritualException("Hmmm (・_・) Your event is missing the 'from' time! Please add it.");
        }
        if (!fullTaskDescription.contains("to")) {
            throw new lovespiritualException("Oops! (•‿•) The 'to' part is missing! Let's add it.");
        }
        String taskDescription;
        String from;
        String to;
        String[] taskDetails = fullTaskDescription.split("from ");
        if (taskDetails[0].trim().isEmpty()) {
            throw new lovespiritualException("Yikes! (⊙_⊙;) You forgot to tell me what the event is about!");
        }
        taskDescription = taskDetails[0].trim();
        String[] time = taskDetails[1].split("to ");
        if (time.length < 2 || time[0].trim().isEmpty()) {
            throw new lovespiritualException("Start date/time? (。_。) We can't go without it!");
        }
        if (time[1].trim().isEmpty()) {
            throw new lovespiritualException("The end date/time is missing (･o･;) When does this event wrap up?");
        }
        from = time[0].trim();
        to = time[1].trim();
        tasks.add(new Event(taskDescription, from, to));
        System.out.println(SEPARATOR);
        System.out.println("Yay! (•‿•) I've added your task!");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Woot! (^▽^) You now have " + tasks.size() + " tasks in your list!");
        System.out.println(SEPARATOR);
        return tasks.size();
    }

    private static int deadline(String input, ArrayList <Task> tasks) throws lovespiritualException {
        String fullTaskDescription = input.substring("deadline".length()).trim();
        if (fullTaskDescription.isEmpty()) {
            throw new lovespiritualException("Oops! (｡•́︿•̀｡) Your deadline needs a little description!");
        }
        if (!fullTaskDescription.contains("by")) {
            throw new lovespiritualException("The 'by' is missing! (・_・;) When's it due?");
        }
        String taskDescription;
        String by;
        String[] taskDetails = fullTaskDescription.split("by", 2);
        if (taskDetails.length < 2 || taskDetails[0].trim().isEmpty()) {
            throw new lovespiritualException("Hmm... (・_・;) Don’t forget to tell me what this deadline is about!");
        }
        if (taskDetails[1].trim().isEmpty()) {
            throw new lovespiritualException("Uh-oh! (・へ・) I need to know the deadline date or time.");
        }
        taskDescription = taskDetails[0].trim();
        by = taskDetails[1].trim();
        tasks.add(new Deadline(taskDescription, by));
        System.out.println(SEPARATOR);
        System.out.println("Yippee! (★^O^★) Task added successfully!");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Wow! (｡♥‿♥｡) You now have " + tasks.size() + " tasks! Keep going!");
        System.out.println(SEPARATOR);
        return tasks.size();
    }

    private static int todo(String input, ArrayList <Task> tasks) throws lovespiritualException {
        String taskDescription = input.substring("todo".length()).trim();
        if (taskDescription.isEmpty()) {
            throw new lovespiritualException("Hmm... (¬‿¬) What's the todo? Looks like the description's missing!");
        }
        tasks.add(new Todo(taskDescription));
        System.out.println(SEPARATOR);
        System.out.println("Woohoo! (＾▽＾) Your task is safely added!");
        System.out.println(" [T][ ] " + taskDescription);
        System.out.println("Amazing! (•̀ᴗ•́) You’ve got " + tasks.size() + " tasks lined up!");
        System.out.println(SEPARATOR);
        return tasks.size();
    }

    private static void unmarkTask(String input, int taskCount, ArrayList <Task> tasks) throws lovespiritualException {
        String taskNumber = input.substring("unmark".length()).trim();
        if (taskNumber.isEmpty()) {
            throw new lovespiritualException("Oopsie! (⊙_⊙) Please give me a valid number!");
        }
        int indexNumber;
        try {
            indexNumber = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new lovespiritualException("Hmm, that's not a number! (・_・;) Try again, please!");
        }
        if (indexNumber >= 0 && indexNumber < taskCount) {
            tasks.get(indexNumber).unmark();
            System.out.println(SEPARATOR);
            System.out.println("Got it! (◠‿◠) This task isn't done yet!");
            System.out.println(tasks.get(indexNumber));
            System.out.println(SEPARATOR);
        } else {
            throw new lovespiritualException("Yikes! (≧Д≦) That number doesn't look right. Can you double-check it?");
        }
    }

    private static void markTask(String input, int taskCount, ArrayList <Task> tasks) throws lovespiritualException {
        String taskNumber = input.substring("mark".length()).trim();
        if (taskNumber.isEmpty()) {
            throw new lovespiritualException("Hmm... (ʘ‿ʘ) A valid number, please?");
        }
        int indexNumber;
        try {
            indexNumber = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new lovespiritualException("Whoa there! (O.O) That’s not a number! Can you double-check?");
        }
        if (indexNumber >= 0 && indexNumber < taskCount) {
            tasks.get(indexNumber).mark();
            System.out.println(SEPARATOR);
            System.out.println("Yay! (^_^) This task is all done!");
            System.out.println(tasks.get(indexNumber));
            System.out.println(SEPARATOR);
        } else {
            throw new lovespiritualException("Hmm... (°ヘ°) That number seems a bit off. Try again?");
        }
    }

    private static void printList(int taskCount, ArrayList <Task> tasks) {
        System.out.println(SEPARATOR);
        System.out.println("Here's your list! (・∀・) Ready to tackle it?");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(SEPARATOR);
    }

    private static void printExitScreen() {
        System.out.println(SEPARATOR);
        System.out.println("Goodbye! (｡•‿•｡) Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    private static void printWelcomeScreen() {
        System.out.println(SEPARATOR);
        System.out.println("Hiya! (✿◠‿◠) I'm lovespiritual, ready to help!");
        System.out.println("How can I assist? (•‿•) I'm all ears!");
        System.out.println(SEPARATOR);
    }
}
