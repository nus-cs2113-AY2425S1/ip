package freedom.tasks;

import java.util.ArrayList;

public class TaskList {
    public static String LOGO = "\t________________________________________\n";

    private ArrayList<Task> tasks;
    private int lastIndex;

    public TaskList() {
        tasks = new ArrayList<>();
        setLastIndex(0);
    }

    public void addTask(String type, String input) {
        String description;
        try {
            switch (type) {
            case "todo":
                description = input.replaceFirst("todo", "");
                tasks.add(new ToDo(description));
                break;
            case "deadline":
                description = input.replaceFirst("deadline", "");
                tasks.add(new Deadline(description));
                break;
            case "event":
                description = input.replaceFirst("event", "");
                tasks.add(new Event(description));
                break;
            default:
                throw new Exception();
            }

            System.out.println(LOGO + "\tGot it. I've added this task: ");
            System.out.println("\t  " + tasks.get(getLastIndex()).printLine());
            System.out.println("\tNow you have " + (getLastIndex() + 1) + " tasks in the list.\n" + LOGO);

            setLastIndex(getLastIndex() + 1);

        } catch (Exception e) {
            System.out.print("");
        }
    }

    public void editTask(String[] words) {
        final int COMMAND_INDEX = 0;
        final int TASK_INDEX = 1;
        int listNumber = Integer.parseInt(words[TASK_INDEX]);
        int taskIndexInStorage = listNumber - 1;
        String message;
        Task taskToBeMarked;

        try {
            if (taskIndexInStorage >= lastIndex) {
                throw new ArrayIndexOutOfBoundsException();
            }
            taskToBeMarked = tasks.get(taskIndexInStorage);
            switch (words[COMMAND_INDEX]) {
            case "mark":
                taskToBeMarked.markDone();
                tasks.set(taskIndexInStorage, taskToBeMarked);
                message = "\tNice! I've marked this task as done:";
                break;
            case "unmark":
                taskToBeMarked.markUndone();
                tasks.set(taskIndexInStorage, taskToBeMarked);
                message = "\tOk, I've marked this task as not done yet:";
                break;
            case "delete":
                tasks.remove(taskIndexInStorage);
                message = "\tOk! This task has been deleted:";
                setLastIndex(getLastIndex() - 1);
                break;
            default:
                throw new Exception();
            }
            System.out.println(LOGO + message);
            System.out.println("\t  " + taskToBeMarked.printLine());
            System.out.println(LOGO);

            //saveData();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print(LOGO);
            System.out.print("""
                    \tWe don't have that many tasks??
                    \tYou can use list to check
                    """);
            System.out.println(LOGO);
        } catch (Exception e) {
            System.out.print("");
        }
    }

    public void printList() {
        int counter = 1;
        System.out.print(LOGO + "\tHere are the tasks in your list:\n");
        for (int i = 0; i < getLastIndex(); i++) {
            System.out.println("\t" + counter + "." + tasks.get(i).printLine());
            counter++;
        }
        System.out.println(LOGO);
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
