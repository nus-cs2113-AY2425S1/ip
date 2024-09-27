package king;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    protected static ArrayList<Task> tasks;
    private int tasksCount;
    private final static String LINE = "____________________________________________________________\n";

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.tasksCount = 0;
    }

    public void loadTasks() throws KingException, IOException {
        if (Storage.checkFile()) {
            tasks = Storage.readFile();
            tasksCount = tasks.size();
        }
    }

    public void findTask(String text) {
        String[] inputWords = text.trim().split("\\s+", 2);

        if (inputWords.length < 2 || inputWords[1].isBlank()) {
            System.out.println(LINE + "You must provide a valid search term...\n" + LINE);
            return;
        }

        String searchTerm = inputWords[1].trim();

        ArrayList<Task> targetTasks = new ArrayList<>();
        boolean isMatching = false;

        for (Task t : tasks) {
            String description = t.description;
            if (description.contains(searchTerm)) {
                targetTasks.add(t);
                isMatching = true;
            }
        }

        if (isMatching) {
            System.out.println(LINE + "Here are the matching tasks in your list:");

            for (int i = 0; i < targetTasks.size(); i++) {
                int indexNum = i + 1;
                System.out.println(indexNum + ". " + targetTasks.get(i));
            }
            System.out.println(LINE);

        } else {
            System.out.println(LINE + "There is no matching task in your list :(\n" + LINE);
        }
    }

    public void addToDoTask(String text) {
        String[] taskSpecifics = text.trim().split("\\s+");

        try {
            if (taskSpecifics.length <= 1) {
                throw new IndexOutOfBoundsException();
            }

            StringBuilder taskContent = new StringBuilder(taskSpecifics[1]);
            for (int i = 2; i < taskSpecifics.length; i++) {
                taskContent.append(" ").append(taskSpecifics[i]);
            }

            String taskContentStr = taskContent.toString().trim();

            if (taskContentStr.isEmpty()) {
                throw new IndexOutOfBoundsException();
            }

            Task t = new Todo(taskContentStr);
            tasks.add(t);
            tasksCount += 1;
            printAddedTaskDescription(t);

        } catch (IndexOutOfBoundsException e) {
            System.out.println(LINE
                               + "Have you forgotten the task content? Please enter a valid task.\n"
                               + LINE);
        }

        Storage.updateFile();
    }


    public void addDeadlineTask(String text) throws KingException {
        String[] taskSpecifics = text.split(" ");
        int separationIndex = 0;
        String taskContent = "";
        String taskEndTime = "";
        boolean existBy = false;

        try {
            for (int i = 0; i < taskSpecifics.length; i++) {
                if (taskSpecifics[i].equals("/by")) {
                    separationIndex = i;
                    existBy = true;
                    break;
                }
            }

            if (!existBy) {
                throw new KingException("You must have a proper format for deadline task. Please try again:)\n");
            }

            if (separationIndex == 1) {
                throw new KingException("You must provide a task description before '/by'. Please try again:)\n");
            }

            for (int i = 1; i < separationIndex; i++) {
                taskContent += taskSpecifics[i];
                if (i < separationIndex - 1) {
                    taskContent += " ";
                }
            }

            taskContent = taskContent.trim();
            if (taskContent.isEmpty()) {
                throw new KingException("You must provide a valid task description before '/by'. Please try again:)\n");
            }

            if (separationIndex == taskSpecifics.length - 1) {
                throw new KingException("You must provide a deadline after '/by'. Please try again:)\n");
            }

            for (int i = separationIndex + 1; i < taskSpecifics.length; i++) {
                taskEndTime += taskSpecifics[i];
                if (i < taskSpecifics.length - 1) {
                    taskEndTime += " ";
                }
            }

            taskEndTime = taskEndTime.trim();
            if (taskEndTime.isEmpty()) {
                throw new KingException("You must provide a valid deadline after '/by'. Please try again:)\n");
            }

        } catch (KingException e) {
            System.out.println(LINE + e.getMessage() + LINE);
            return;
        }

        Task t = new Deadline(taskContent, taskEndTime);
        tasks.add(t);
        tasksCount += 1;
        printAddedTaskDescription(t);

        Storage.updateFile();
    }

    public void addEventTask(String text) throws KingException {
        String[] taskSpecifics = text.split(" ");
        int separationIndexFirst = 0;
        int separationIndexSecond = 0;
        String taskContent = "";
        String taskStartTime = "";
        String taskEndTime = "";
        boolean existFrom = false;
        boolean existTo = false;

        try {
            for (int i = 0; i < taskSpecifics.length; i++) {
                if (taskSpecifics[i].equals("/from")) {
                    separationIndexFirst = i;
                    existFrom = true;
                } else if (taskSpecifics[i].equals("/to")) {
                    separationIndexSecond = i;
                    existTo = true;
                }
            }

            if (!existFrom || !existTo) {
                throw new KingException("You must have a proper format for event task. Please try again:)\n");
            }

            if (separationIndexFirst == 1) {
                throw new KingException("You must provide a task description before '/from'. Please try again:)\n");
            }

            for (int i = 1; i < separationIndexFirst; i++) {
                taskContent += taskSpecifics[i];
                if (i < separationIndexFirst - 1) {
                    taskContent += " ";
                }
            }

            for (int i = separationIndexFirst + 1; i < separationIndexSecond; i++) {
                taskStartTime += taskSpecifics[i];
                if (i < separationIndexSecond - 1) {
                    taskStartTime += " ";
                }
            }
            taskStartTime = taskStartTime.trim();
            if (taskStartTime.isEmpty()) {
                throw new KingException("You must provide a valid start time after '/from'. Please try again:)\n");
            }

            for (int i = separationIndexSecond + 1; i < taskSpecifics.length; i++) {
                taskEndTime += taskSpecifics[i];
                if (i < taskSpecifics.length - 1) {
                    taskEndTime += " ";
                }
            }
            taskEndTime = taskEndTime.trim();
            if (taskEndTime.isEmpty()) {
                throw new KingException("You must provide a valid end time after '/to'. Please try again:)\n");
            }

        } catch (KingException e) {
            System.out.println(LINE + e.getMessage() + LINE);
            return;
        }

        Task t = new Event(taskContent, taskStartTime, taskEndTime);
        tasks.add(t);
        tasksCount += 1;
        printAddedTaskDescription(t);

        Storage.updateFile();
    }

    public void deleteTask(int index) throws KingException {
        try {
            Task removedTask = tasks.remove(index);
            tasksCount -= 1;
            System.out.println(LINE + "Noted. I've removed this task:\n   "
                               + removedTask.getTaskDescription()
                               + "\nNow you have " + tasksCount + " tasks in the list.\n" + LINE);

            Storage.updateFile();
        } catch (IndexOutOfBoundsException e) {
            throw new KingException("The task you want to delete does not exist!\n");
        }
    }

    public void markTaskDone(int index) throws KingException {
        try {
            tasks.get(index).markAsDone();
            System.out.println(LINE +
                               "Nice! I've marked this task as done:\n   " +
                               tasks.get(index).getTaskDescription() + "\n" + LINE);

            Storage.updateFile();
        } catch (IndexOutOfBoundsException e) {
            throw new KingException("You cannot mark a task that doesn't exist!\n");
        }
    }

    public void markTaskUndone(int index) throws KingException {
        try {
            tasks.get(index).markAsUndone();
            System.out.println(LINE
                               + "OK, I've marked this task as not done yet:\n   "
                               + tasks.get(index).getTaskDescription() + "\n"
                               + LINE);

            Storage.updateFile();
        } catch (IndexOutOfBoundsException e) {
            throw new KingException("You cannot unmark a task that doesn't exist!\n");
        }
    }

    private void printAddedTaskDescription(Task t) {
        System.out.println(LINE +
                           "Got it. I've added this task:\n   " +
                           t.getTaskDescription() +
                           "\nNow you have " + tasksCount + " tasks in the list.\n" + LINE);
    }

    public void printList() {
        if (tasksCount == 0) {
            System.out.println(LINE +
                               "Your list is still empty!\n" + LINE);
            return;
        }

        System.out.println(LINE +
                           "Here are the tasks in your list:");

        for (int i = 0; i < tasksCount; i++) {
            int indexNum = i + 1;
            System.out.println(indexNum + ". " + tasks.get(i));
        }

        System.out.println(LINE);
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }
}
