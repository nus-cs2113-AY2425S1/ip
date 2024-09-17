import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Duke {

    //taskList variable for level-2 onwards
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    //task count
    private static int taskCount = 0;

    private static String folderPath = "./data";
    private static String filePath = folderPath+ "/duke.txt";


    //echo function for level-1
    public static void echo(String message) {
        System.out.println(message);
    }


    //add function for level-2
    public static void add(String task) {
        Task newTask = new Task(task);
        taskList.add(newTask);
        taskCount++;
        System.out.println("added: " + task);
    }


    //list function for level-2
    public static void list() {
        if(taskCount == 0) {
            System.out.println("No tasks found");
        }
        else {
            for (int i = 0; i < taskCount; i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(taskList.get(i).toString());
            }
        }
    }


    //mark function for level-3
    public void mark(int index) throws DukeException {
        try {
            checkMarkUnmarkInput(index);
            System.out.println("Nice! I've marked this task as done:");
            taskList.get(index-1).markAsDone();
        } catch (DukeException e) {
            e.displayMessage();
        }

    }


    //unmark function for level-3
    public void unmark(int index) throws DukeException {
        try {
            checkMarkUnmarkInput(index);
            System.out.println("Ok, I've marked this task as not done yet:");
            taskList.get(index-1).markAsNotDone();
        } catch (DukeException e) {
            e.displayMessage();
        }
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


    //load data from file to arraylist level-7
    public static void loadFileData() throws DukeException, IOException {

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                loadLineData(line);
            }
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            boolean succeed = file.createNewFile();
        }
    }


    //load data from string to arraylist level-7
    public static void loadLineData(String line) throws DukeException {
        String[] inputComponent = line.split(" \\| ");
        switch (inputComponent[0]) {
            case "T" -> {
                taskList.add(new ToDo(inputComponent[1]));
                taskCount++;
                if (inputComponent[2].equals("1")) {
                    taskList.get(taskCount - 1).markAsDone();
                }
            }
            case "D" -> {
                taskList.add(new Deadline(inputComponent[1], inputComponent[2]));
                taskCount++;
                if (inputComponent[3].equals("1")) {
                    taskList.get(taskCount - 1).markAsDone();
                }
            }
            case "E" -> {
                taskList.add(new Event(inputComponent[1], inputComponent[2], inputComponent[3]));
                taskCount++;
                if (inputComponent[4].equals("1")) {
                    taskList.get(taskCount - 1).markAsDone();
                }
            }
        }
    }

    //helper function to create string to push to file level-7
    public static String addLineData(Task task) throws DukeException, IOException {
        String line = "";
        if(task instanceof ToDo) {
            line += "T | " + task.getDescription() + " | ";
            if(task.isDone) {
                line += "1";
            }
            else {
                line += "0";
            }
        }
        else if(task instanceof Deadline) {
            line += "D | " + task.getDescription() + " | " + ((Deadline) task).getBy() + " | ";
            if(task.isDone) {
                line += "1";
            } else {
                line += "0";
            }
        }
        else if(task instanceof Event) {
            line += "E | " + task.getDescription() + " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo() + " | ";
            if(task.isDone) {
                line += "1";
            } else {
                line += "0";
            }
        }
        return line;
    }

    //update file data after every command for level-7
    public static void updateFileData() throws DukeException, IOException {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for(int i = 0; i < taskCount; i++) {
                writer.write(addLineData(taskList.get(i)) + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new DukeException("cannot write to file");
        }
    }


    //main function to execute the chatbot
    public void execute() throws DukeException,IOException {
        System.out.println("Hello I'm Lambo");
        System.out.println("What can I do for you?");
        Scanner inputReader = new Scanner(System.in);//scanner for receiving input

        loadFileData();


        //Super loop used for receiving inputs continuously and response back to user
        SuperLoop:
        while (true) {
            String input = inputReader.nextLine();
            String[] inputComponent = input.split(" ");

            //switch case based on the first word of input line
            switch (inputComponent[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break SuperLoop;
                case "list":
                    list();
                    break;
                case "mark":
                    try {
                        mark(Integer.parseInt(inputComponent[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Warning: You haven't input any number");
                    }
                    break;
                case "unmark":
                    try {
                        unmark(Integer.parseInt(inputComponent[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Warning: You haven't input any number");
                    }
                    break;
                case "todo":
                    addToDo(inputComponent);
                    break;
                case "deadline":
                    addDeadline(inputComponent);
                    break;
                case "event":
                    addEvent(inputComponent);
                    break;
                case "delete":
                    deleteTask(Integer.parseInt(inputComponent[1]));
                    break;
                default:
                    try {
                        checkGeneralInput(); //check for invalid command
                    } catch (DukeException e) {
                        e.displayMessage();
                    }
            }
            updateFileData();
        }
    }
}
