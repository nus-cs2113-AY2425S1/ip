package king;

import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

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

        LocalDateTime deadlineTime;
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

            taskContent = String.join(" ", Arrays.copyOfRange(taskSpecifics, 1, separationIndex));

            taskEndTime = String.join(" "
                    , Arrays.copyOfRange(taskSpecifics, separationIndex + 1, taskSpecifics.length)).trim();

            try {
                if (taskEndTime.length() == 10) {
                    taskEndTime += " 2359";
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                deadlineTime = LocalDateTime.parse(taskEndTime, formatter);
            } catch (DateTimeParseException e) {
                throw new KingException("Invalid date/time format. Please use 'yyyy-MM-dd HHmm' or 'yyyy-MM-dd'.\n");
            }

        } catch (KingException e) {
            System.out.println(LINE + e.getMessage() + LINE);
            return;
        }

        Task t = new Deadline(taskContent, deadlineTime);
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
        LocalDateTime startTime;
        LocalDateTime endTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

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

            taskContent = String.join(" ", Arrays.copyOfRange(taskSpecifics
                    , 1, separationIndexFirst)).trim();

            taskStartTime = String.join(" ", Arrays.copyOfRange(taskSpecifics
                    , separationIndexFirst + 1, separationIndexSecond)).trim();
            if (taskStartTime.isEmpty()) {
                throw new KingException("You must provide a valid start time after '/from'. Please try again:)\n");
            }

            try {
                if (taskStartTime.length() == 10) {
                    taskStartTime += " 2359";
                }
                startTime = LocalDateTime.parse(taskStartTime, formatter);
            } catch (DateTimeParseException e) {
                throw new KingException("Invalid start time format. Please use 'yyyy-MM-dd HHmm'.\n");
            }


            taskEndTime = String.join(" ", Arrays.copyOfRange(taskSpecifics, separationIndexSecond + 1, taskSpecifics.length)).trim();
            if (taskEndTime.isEmpty()) {
                throw new KingException("You must provide a valid end time after '/to'. Please try again:)\n");
            }

            try {
                if (taskEndTime.length() == 10) {
                    taskEndTime += " 2359";
                }
                endTime = LocalDateTime.parse(taskEndTime, formatter);
            } catch (DateTimeParseException e) {
                throw new KingException("Invalid end time format. Please use 'yyyy-MM-dd HHmm'.\n");
            }

            if (endTime.isBefore(startTime)) {
                throw new KingException("The end time must be after the start time. Please check your inputs.\n");
            }

        } catch (KingException e) {
            System.out.println(LINE + e.getMessage() + LINE);
            return;
        }

        Task t = new Event(taskContent, startTime, endTime);
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
                               tasks.get(index) + "\n" + LINE);

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
                               + tasks.get(index) + "\n"
                               + LINE);

            Storage.updateFile();
        } catch (IndexOutOfBoundsException e) {
            throw new KingException("You cannot unmark a task that doesn't exist!\n");
        }
    }

    private void printAddedTaskDescription(Task t) {
        System.out.println(LINE +
                           "Got it. I've added this task:\n   " +
                           t +
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
