import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;
    private static int taskCount;


    public TaskList() {
        taskList = new ArrayList<>();
        taskCount = 0;
    }

    public void add(Task newTask) {
        taskList.add(newTask);
        taskCount++;
    }

    //add toDo task for level-4
    public static void addToDo(String[] inputComponent) throws DukeException {
        String description = "";

        for (int i = 1; i < inputComponent.length; i++) {
            description += inputComponent[i];
            description += " ";
        }

        try {
            checkTodoInput(description);
            taskList.add(new ToDo(description.trim()));
            System.out.println("Got it. I've added this task:");
            System.out.println(taskList.get(taskCount).toString());
            taskCount++;
            System.out.println("Now you have " + taskCount + " tasks in the list");
        } catch (DukeException e) {
            e.displayMessage();
        }

    }


    //add deadline task for level-4
    public static void addDeadline(String[] inputComponent) throws DukeException {
        String description = "";
        String by = "";
        int state = 0;//transition from "description" to "by" string


        for (int i = 1; i < inputComponent.length; i++) {
            if (inputComponent[i].equals("/by")) {
                state += 1;
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

        try {
            checkDeadlineInput(description, state);
            taskList.add(new Deadline(description.trim(), by.trim()));
            System.out.println("Got it. I've added this task:");
            System.out.println(taskList.get(taskCount).toString());
            taskCount++;
            System.out.println("Now you have " + taskCount + " tasks in the list");
        } catch (DukeException e) {
            e.displayMessage();
        }

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

        try {
            checkEventInput(description, state);
            taskList.add(new Event(description.trim(), from.trim(), to.trim()));
            System.out.println("Got it. I've added this task:");
            System.out.println(taskList.get(taskCount).toString());
            taskCount++;
            System.out.println("Now you have " + taskCount + " tasks in the list");
        } catch (DukeException e) {
            e.displayMessage();
        }
    }

    //exception handler for todo level-5
    public static void checkTodoInput(String input) throws DukeException {
        if (input == null || input.isEmpty()) {
            // Throw a custom exception for empty input
            throw new DukeException("Description for a todo cannot be empty");
        }
    }

    //exception handler for deadline level-5
    public static void checkDeadlineInput(String input, int state) throws DukeException {
        if (input == null || input.isEmpty()) {
            // Throw a custom exception for empty input
            throw new DukeException("Description for a deadline cannot be empty");
        } else if (state == 0) {
            throw new DukeException("There is no date for a deadline");
        } else if (state > 1) {
            throw new DukeException("Too many /by statement");
        }
    }


    //exception handler for event level-5
    public static void checkEventInput(String input, int state) throws DukeException {
        if (input == null || input.isEmpty()) {
            // Throw a custom exception for empty input
            throw new DukeException("Description for an event cannot be empty");
        } else if (state == 0) {
            throw new DukeException("There is no start and end for this event");
        } else if (state == 1) {
            throw new DukeException("There is no end for this event");
        }
    }


    //exception handler for mark and unmark level-5
    public static void checkMarkUnmarkInput(int index) throws DukeException {
        if (index < 0 || index > taskCount) {
            throw new DukeException("You have input an invalid index");
        }
    }

    //exception handler for general input level-5
    public static void checkGeneralInput() throws DukeException {
        throw new DukeException("Sorry I cannot understand that");
    }

    //delete method for level-6
    public static void deleteTask(int index) throws DukeException {
        if (index-1 < 0 || index-1 > taskCount) {
            throw new DukeException("You have input an invalid index");
        }

        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(index-1).toString());
        taskList.remove(index-1);
        taskCount--;
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }
}
