import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;

public class Gertrude {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final Integer MAXIMUM_TASKS = 100;
    public static final String[] KEYWORDS = {"bye", "list", "mark", "unmark", "todo", "deadline", "event"};
    public static final String[] ONE_WORD_KEYWORDS = {"bye", "list"};
    public static final String FILE_PATH = "src/main/java/data/gertrude.txt";

    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printIntroduction() {
        System.out.println("Hello, I'm Gertrude.");
        System.out.println("What can I do for you?");
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printList(Task[] tasks) {
        for (int i = 1; i <= Task.taskIndex; i++) {
            System.out.print(i + ".");
            tasks[i-1].printTask();
        }
        System.out.println("You have " + Task.taskIndex + " tasks in the list.");
    }

    public static void markTask(String[] lineInputArr, Task[] tasks) {
        int index = Integer.parseInt(lineInputArr[1]);
        if (index < 1 || index > Task.taskIndex) {
            System.out.println("That is not a valid index.");
        } else if (lineInputArr[0].equals("mark")) {
            tasks[index - 1].markDone();
        } else {
            tasks[index-1].markNotDone();
        }
    }

    public static void addTask(Task task, String lineInput, Task[] tasks) {
        tasks[Task.taskIndex] = task;
        Task.taskIndex += 1;
        System.out.println("added: " + lineInput);
    }

    public static void addTodo(String[] lineInputArr, Task[] tasks) {
        String name = "";
        for(int i = 1; i < lineInputArr.length; i++) {
            name += lineInputArr[i] + " ";
        }
        Todo newTodo = new Todo(name);
        addTask(newTodo, name, tasks);
    }

    public static void addDeadline(String[] lineInputArr, Task[] tasks) throws GertrudeException, KeywordException {
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
        addTask(newDeadline, description, tasks);
    }

    public static void addEvent(String[] lineInputArr, Task[] tasks) throws GertrudeException, KeywordException {
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
        addTask(newEvent, description, tasks);
    }

    public static Task[] openFile() throws FileNotFoundException, CorruptedFileException, IndexOutOfBoundsException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        String line;
        Task[] tasks = new Task[MAXIMUM_TASKS];
        while (s.hasNext()) {
            line = s.nextLine();
            String[] lineArr = line.split(" \\| ");
            switch (lineArr[0]) {
            case "T":
                Todo newTodo = new Todo(lineArr[2]);
                newTodo.done = lineArr[1];
                tasks[Task.taskIndex] = newTodo;
                break;
            case "D":
                Deadline newDeadline = new Deadline(lineArr[2], lineArr[3]);
                newDeadline.done = lineArr[1];
                tasks[Task.taskIndex] = newDeadline;
                break;
            case "E":
                Event newEvent = new Event(lineArr[2], lineArr[3], lineArr[4]);
                newEvent.done = lineArr[1];
                tasks[Task.taskIndex] = newEvent;
                break;
            default:
                throw new CorruptedFileException();
            }
            Task.taskIndex++;
        }
        return tasks;
    }

    public static void saveFile(Task[] tasks) throws IOException, FileNotFoundException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for(int i = 0; i < Task.taskIndex; i++) {
            Task task = tasks[i];
            fw.write(task.symbol + " | " + task.done + " | " + task.name + task.dataForSave() + System.lineSeparator());
        }
        fw.close();
    }

    public static void main(String[] args) throws GertrudeException {
        printIntroduction();
        Task[] tasks = new Task[MAXIMUM_TASKS];
        try {
            tasks = openFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            throw new GertrudeException();
        } catch (CorruptedFileException | IndexOutOfBoundsException e) {
            System.out.println("Corrupted file.");
            throw new GertrudeException();
        }
        boolean runLoop = true;
        while (runLoop) {
            String lineInput;
            Scanner in = new Scanner(System.in);
            lineInput = in.nextLine();
            String[] lineInputArr = lineInput.split(" ");
            printHorizontalLine();

            try {
                if (lineInputArr.length <= 1 && !Arrays.asList(ONE_WORD_KEYWORDS).contains(lineInputArr[0])) {
                    System.out.println("The description of your task cannot be empty.");
                    throw new GertrudeException();
                }
            } catch (GertrudeException e) {
                System.out.println("Please try again.");
                printHorizontalLine();
                continue;
            }

            try {
                switch (lineInputArr[0]) {
                case "bye":
                    printGoodbyeMessage();
                    runLoop = false;
                    try {
                        saveFile(tasks);
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found. Couldn't save.");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "list":
                    printList(tasks);
                    break;
                case "mark", "unmark":
                    markTask(lineInputArr, tasks);
                    break;
                case "todo":
                    addTodo(lineInputArr, tasks);
                    break;
                case "deadline":
                    addDeadline(lineInputArr, tasks);
                    break;
                case "event":
                    addEvent(lineInputArr, tasks);
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
                printHorizontalLine();
            }
        }
    }
}
