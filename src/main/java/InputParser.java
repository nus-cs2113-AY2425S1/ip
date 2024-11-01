import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Parses input from the user and checks for common errors.
 */
public class InputParser {
    public static final String[] KEYWORDS = {"bye", "list", "mark", "unmark", "todo", "deadline", "event", "find"};
    public static final String[] ONE_WORD_KEYWORDS = {"bye", "list"};
    public static final int COMMAND_INDEX = 0;
    public static final int DESCRIPTION_INDEX = 1;

    /**
     * Given a line input, returns the index of the task to mark/unmark.
     * Checks for valid index
     * @param lineInputArr the line input to check
     * @param taskList the task list
     * @return the index if it's successfully found, -1 otherwise
     */
    public static int parseMark(String[] lineInputArr, TaskList taskList) {
        int index = Integer.parseInt(lineInputArr[DESCRIPTION_INDEX]);
        if (index < 0 || index > taskList.getTaskList().size()) {
            System.out.println("That is not a valid index.");
            return -1;
        } else {
            return index-1;
        }
    }

    /**
     * Parses general user input
     * Redirects to various parse functions based on what keyword the user inputted
     * Checks for common errors
     * @param taskList the task list
     * @return true if the program should continue to run, false otherwise
     */
    public static boolean parseInput(TaskList taskList) {
        String lineInput;
        Scanner in = new Scanner(System.in);
        lineInput = in.nextLine();
        String[] lineInputArr = lineInput.split(" ");
        Ui.printHorizontalLine();

        try {
            if (lineInputArr.length <= 1 && !Arrays.asList(ONE_WORD_KEYWORDS).contains(lineInputArr[COMMAND_INDEX])) {
                System.out.println("The description of your task cannot be empty.");
                throw new GertrudeException();
            }
        } catch (GertrudeException e) {
            System.out.println("Please try again.");
            Ui.printHorizontalLine();
            return true;
        }

        try {
            switch (lineInputArr[COMMAND_INDEX]) {
            case "bye":
                Ui.printGoodbyeMessage();
                return false;
            case "list":
                taskList.printList();
                break;
            case "mark", "unmark":
                taskList.markTask(lineInputArr);
                break;
            case "todo":
                addTodo(lineInputArr, taskList);
                break;
            case "deadline":
                addDeadline(lineInputArr, taskList);
                break;
            case "event":
                addEvent(lineInputArr, taskList);
                break;
            case "delete":
                taskList.deleteTask(lineInputArr);
                break;
            case "find":
                taskList.findKeyword(lineInputArr);
                break;
            default:
                System.out.println("That is not a valid input.");
                throw new GertrudeException();
            }
        } catch (GertrudeException e) {
            System.out.println("Please try again.");
        } catch (KeywordException e) {
            System.out.println("Please make sure you're entering the correct keywords.");
        } finally {
            Ui.printHorizontalLine();
        }
        return true;
    }

    /**
     * Parses user input to add a todo
     * @param lineInputArr the user's input
     * @param taskList the task list
     */
    public static void addTodo(String[] lineInputArr, TaskList taskList) {
        String name = "";
        for(int i = DESCRIPTION_INDEX; i < lineInputArr.length; i++) {
            name += lineInputArr[i] + " ";
        }
        Todo newTodo = new Todo(name);
        taskList.addTask(newTodo, name);
    }

    /**
     * Parses user input to add a deadline
     * First adds user input to a description variable, then to a deadline variable after /by
     * Concatenates all Strings at the end
     * @param lineInputArr the user's input
     * @param taskList the task list
     * @throws GertrudeException if the description is missing
     * @throws KeywordException if /by is missing
     */
    public static void addDeadline(String[] lineInputArr, TaskList taskList) throws GertrudeException, KeywordException {
        String description = "";
        String deadline = "";
        boolean hasBy = false;
        boolean isDeadline = false;

        for(int i = DESCRIPTION_INDEX; i < lineInputArr.length; i++) {
            if (lineInputArr[i].equals("/by")) {
                isDeadline = true;
                hasBy = true;
            } else if (!isDeadline) {
                description = description + lineInputArr[i] + " ";
            } else {
                deadline = deadline + lineInputArr[i] + " ";
            }
        }

        if(description.isEmpty()) {
            System.out.println("Description is missing");
            throw new GertrudeException();
        }
        if(!hasBy) {
            System.out.println("/by is missing.");
            throw new KeywordException();
        }
        if(deadline.isEmpty()) {
            System.out.println("Deadline is empty.");
            throw new GertrudeException();
        }

        Deadline newDeadline = new Deadline(description, deadline);
        taskList.addTask(newDeadline, description);
    }

    /**
     * Parses user input to add an event
     * First adds user input to a description variable, then to a from variable after /from
     * Then adds to a to variable after /to
     * Concatenates all Strings at the end
     * @param lineInputArr the user's input
     * @param taskList the task list
     * @throws GertrudeException if the description is missing
     * @throws KeywordException if /from or /to is missing
     */
    public static void addEvent(String[] lineInputArr, TaskList taskList) throws GertrudeException, KeywordException {
        String description = "";
        String start = "";
        String end = "";
        String section = "description";
        boolean hasFrom = false;
        boolean hasTo = false;
        for(int i = 1; i < lineInputArr.length; i++) {
            if (lineInputArr[i].equals("/from")) {
                section = "from";
                hasFrom = true;
            } else if (lineInputArr[i].equals("/to")) {
                section = "to";
                hasTo = true;
            } else if (section.equals("description")) {
                description += lineInputArr[i] + " ";
            } else if (section.equals("from")) {
                start += lineInputArr[i] + " ";
            } else if (section.equals("to")) {
                end += lineInputArr[i] + " ";
            }
        }

        if(description.isEmpty()) {
            System.out.println("Description is empty.");
            throw new GertrudeException();
        }
        if(!hasFrom) {
            System.out.println("/from is missing.");
            throw new KeywordException();
        }
        if(start.isEmpty()) {
            System.out.println("From is empty.");
            throw new GertrudeException();
        }
        if(!hasTo) {
            System.out.println("/to is missing.");
            throw new KeywordException();
        }
        if(end.isEmpty()) {
            System.out.println("To is empty.");
            throw new GertrudeException();
        }

        Event newEvent = new Event(description, start, end);
        taskList.addTask(newEvent, description);
    }
}
