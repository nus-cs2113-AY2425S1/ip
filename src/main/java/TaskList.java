import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public static void createTodo(String line) {
        try{
            int beginIndex = 5;
            String taskName = getTaskName(line, beginIndex, -2);
            Task task = new Todo(taskName);
            addTask(tasks, task);
        } catch (EmptyTaskException e) {
            System.out.println("The description of a todo cannot be empty.\n");
        } catch (EmptyByOrFromException ignored) {
        }
    }

    public static void createDeadline(String line) {
        try {
            int beginIndex = 9;
            int byIndex = line.indexOf("/by");
            String taskName = getTaskName(line, beginIndex, byIndex);
            Task task = new Deadline(taskName, line.substring(byIndex + 4));
            addTask(tasks, task);
        } catch (EmptyTaskException e) {
            System.out.println("The description of a deadline cannot be empty.\n");
        } catch (EmptyByOrFromException e) {
            System.out.println("Please enter the deadline after \"/by\". \n");
        }
    }

    public static void createEvent(String line) {
        try {
            int beginIndex = 6;
            int fromIndex = line.indexOf("/from");
            int toIndex = line.indexOf("/to");
            String taskName = getTaskName(line, beginIndex, fromIndex);
            String from = getFrom(line, fromIndex, toIndex);
            Task task = new Event(taskName, from, line.substring(toIndex + 4));
            addTask(tasks, task);
        } catch (EmptyTaskException e) {
            System.out.println("The description of an event cannot be empty.\n");
        } catch (EmptyByOrFromException e) {
            System.out.println("Please enter the start time of event after \"/from\". \n");
        } catch (EmptyToException e) {
            System.out.println("Please enter the end time of event after \"/to\". \n");
        }
    }

    public static void mark(String line) {
        try {
            int beginIndex = 5;
            int index = getIndex(tasks, line, beginIndex, "mark");
            markTask(index);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number after mark\n");
        } catch (IndexOutOfRangeException e) {
            System.out.println("Please enter a task index from 1 to " + Storage.getCount() + "\n");
        } catch (TaskSameStateException e) {
            System.out.println("Task already completed\n");
        }
    }

    public static void unmark(String line) {
        try {
            int beginIndex = 7;
            int index = getIndex(tasks, line, beginIndex, "unmark");
            unmarkTask(index);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number after unmark\n");
        } catch (IndexOutOfRangeException e) {
            System.out.println("Please enter a task index from 1 to " + Storage.getCount() + "\n");
        } catch (TaskSameStateException e) {
            System.out.println("Task already not done\n");
        }
    }

    public static void unmarkTask(int index) {
        tasks.get(index - 1).setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1).toString() + "\n");
    }

    public static void markTask(int index) {
        tasks.get(index - 1).setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1).toString() + "\n");
    }

    public static void deleteTask(String line) {
        try {
            int index = getIndex(tasks, line, 7, "delete");
            System.out.println("Noted. I've removed this task: ");
            System.out.println(tasks.get(index - 1));
            tasks.remove(index - 1);
            Storage.decrementCount();
            System.out.println("Now you have " + Storage.getCount() + " tasks in the list.\n");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number after delete\n");
        } catch (IndexOutOfRangeException e) {
            System.out.println("Please enter a task index from 1 to " + Storage.getCount() + "\n");
        } catch (TaskSameStateException e) {
            System.out.println("ERROR\n"); //won't fall into this case
        }
    }

    public static void find(String line) {
        line = line.substring(5);
        for (int i = 0; i < Storage.getCount(); i++) {
            if (getTask(i).toString().contains(line)) {
                Ui.show(i);
            }
        }
        System.out.println("");
    }

    private static int getIndex(ArrayList<Task> tasks, String line, int beginIndex, String func) throws NumberFormatException, IndexOutOfRangeException, TaskSameStateException {
        try {
            int index = Integer.parseInt(line.substring(beginIndex));
            String state;
            if (index < 0 || index > Storage.getCount()) {
                throw new IndexOutOfRangeException();
            }
            if (func == "mark") {
                state = "X";
                if (tasks.get(index - 1).getStatusIcon() == state) {
                    throw new TaskSameStateException();
                }
            } else if (func == "unmark"){
                state = " ";
                if (tasks.get(index - 1).getStatusIcon() == state) {
                    throw new TaskSameStateException();
                }
            }
            return index;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    private static String getFrom(String line, int fromIndex, int toIndex) throws EmptyToException, EmptyByOrFromException {
        if (toIndex == -1 || toIndex + 4 > line.length() - 1) {
            throw new EmptyToException();
        } else if (fromIndex + 6 >= toIndex){
            throw new EmptyByOrFromException();
        } else {
            return line.substring(fromIndex + 6, toIndex).trim();
        }
    }

    private static void addTask(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
//        System.out.println(getTask(Storage.getCount()));
        Ui.showLine();
    }

    public static Task getTask(int index) {
        return tasks.get(index);
    }

    private static String getTaskName(String line, int beginIndex, int endIndex) throws EmptyByOrFromException, EmptyTaskException {
        if (endIndex == -1) {
            throw new EmptyByOrFromException();
        } else if (endIndex == -2 && beginIndex + 1 > line.length()) {
            throw new EmptyTaskException();
        } else if (endIndex == -2 && line.substring(beginIndex).trim() != "") {
            return line.substring(beginIndex).trim();
        } else {
            String trimName = line.substring(beginIndex, endIndex).trim();
            if (trimName != "") {
                return trimName;
            } else {
                throw new EmptyTaskException();
            }
        }
    }

}
