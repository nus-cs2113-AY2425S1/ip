import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class InputParser {
    public static final String[] KEYWORDS = {"bye", "list", "mark", "unmark", "todo", "deadline", "event"};
    public static final String[] ONE_WORD_KEYWORDS = {"bye", "list"};

    public static int parseMark(String[] lineInputArr, TaskList taskList) {
        int index = Integer.parseInt(lineInputArr[1]);
        if (index < 0 || index > taskList.getTaskList().size()) {
            System.out.println("That is not a valid index.");
            return -1;
        } else {
            return index-1;
        }
    }

    public static boolean parseInput(TaskList taskList) {
        String lineInput;
        Scanner in = new Scanner(System.in);
        lineInput = in.nextLine();
        String[] lineInputArr = lineInput.split(" ");
        Ui.printHorizontalLine();

        try {
            if (lineInputArr.length <= 1 && !Arrays.asList(ONE_WORD_KEYWORDS).contains(lineInputArr[0])) {
                System.out.println("The description of your task cannot be empty.");
                throw new GertrudeException();
            }
        } catch (GertrudeException e) {
            System.out.println("Please try again.");
            Ui.printHorizontalLine();
            return true;
        }

        try {
            switch (lineInputArr[0]) {
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


    public static void addTodo(String[] lineInputArr, TaskList taskList) {
        String name = "";
        for(int i = 1; i < lineInputArr.length; i++) {
            name += lineInputArr[i] + " ";
        }
        Todo newTodo = new Todo(name);
        taskList.addTask(newTodo, name);
    }

    public static void addDeadline(String[] lineInputArr, TaskList taskList) throws GertrudeException, KeywordException {
        String description = "";
        String deadline = "";
        boolean hasBy = false;
        boolean isDeadline = false;

        for(int i = 1; i < lineInputArr.length; i++) {
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
