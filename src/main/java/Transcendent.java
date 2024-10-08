import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Transcendent {

    public static void main(String[] args) throws IOException {
        Printer.printEntry();
        List.init();
        File.init();
        File.loadFile();
        InputHandler.takeInput();
        File.clear();
        File.save();
        Printer.printExit();
    }

    private static class Printer {

        private static void printEntry() {
            Printer.printSeparator();
            System.out.println("Greetings.");
            System.out.println("I am Transcendent.");
            System.out.println("What do you need assistance with?");
            Printer.printSeparator();
        }

        private static void printSeparator() {
            System.out.println("____________________________________________________________");
        }

        private static void printExit() {
            Printer.printSeparator();
            System.out.println("The current list has been saved.");
            Printer.printSeparator();
            System.out.println("Let me know should you need assistance again.");
            System.out.println("Farewell.");
            Printer.printSeparator();
        }

        private static void printAddConfirm(List.Task task) throws IOException {
            Printer.printSeparator();
            System.out.println("Added: " + task.toString());
            Printer.printSeparator();
            File.clear();
            File.save();
        }

        private static void printMarkConfirm(List.Task task) throws IOException {
            Printer.printSeparator();
            System.out.println("I have marked the following task.");
            System.out.println(task.toString());
            Printer.printSeparator();
            File.clear();
            File.save();
        }

        private static void printUnmarkConfirm(List.Task task) throws IOException {
            Printer.printSeparator();
            System.out.println("I have unmarked the following task.");
            System.out.println(task.toString());
            Printer.printSeparator();
            File.clear();
            File.save();
        }

        private static void printDeleteConfirm(List.Task task) throws IOException {
            Printer.printSeparator();
            System.out.println("I have deleted the following task.");
            System.out.println(task.toString());
            Printer.printSeparator();
            File.clear();
            File.save();
        }

        private static void printInvalidCommand() {
            Printer.printSeparator();
            System.out.println("This is an invalid instruction.");
            System.out.println("For list of all valid instructions: help");
            Printer.printSeparator();
        }

        private static void printInvalidDeadline() {
            Printer.printSeparator();
            System.out.println("Invalid deadline.");
            System.out.println("Deadline creation syntax: ");
            System.out.println("deadline *task* /by *by*");
            System.out.println("For list of all valid instructions: help");
            Printer.printSeparator();
        }

        private static void printInvalidTodo() {
            Printer.printSeparator();
            System.out.println("Invalid todo.");
            System.out.println("Todo creation syntax: ");
            System.out.println("todo *task*");
            System.out.println("For list of all valid instructions: help");
            Printer.printSeparator();
        }

        private static void printInvalidEvent() {
            Printer.printSeparator();
            System.out.println("Invalid event.");
            System.out.println("Event creation syntax: ");
            System.out.println("event *task* /from *from* /to *to*");
            System.out.println("For list of all valid instructions: help");
            Printer.printSeparator();
        }

        private static void printInvalidMark() {
            Printer.printSeparator();
            System.out.println("Invalid mark statement.");
            System.out.println("Mark syntax: ");
            System.out.println("mark *task_number*");
            System.out.println("For list of all valid instructions: help");
            Printer.printSeparator();
        }

        private static void printInvalidUnmark() {
            Printer.printSeparator();
            System.out.println("Invalid unmark statement.");
            System.out.println("Unmark syntax: ");
            System.out.println("unmark *task_number*");
            System.out.println("For list of all valid instructions: help");
            Printer.printSeparator();
        }

        private static void printInvalidDelete() {
            Printer.printSeparator();
            System.out.println("Invalid delete statement.");
            System.out.println("Delete syntax: ");
            System.out.println("delete *task_number*");
            System.out.println("For list of all valid instructions: help");
            Printer.printSeparator();
        }

        private static void printNonExistentTask() {
            Printer.printSeparator();
            System.out.println("The specified task does not exist.");
            Printer.printSeparator();
        }

        private static void printAlreadyMarked() {
            Printer.printSeparator();
            System.out.println("This task is already marked.");
            Printer.printSeparator();
        }

        private static void printAlreadyUnmarked() {
            Printer.printSeparator();
            System.out.println("This task is already unmarked.");
            Printer.printSeparator();
        }

        private static void printEmptyList() {
            Printer.printSeparator();
            System.out.println("The list is empty.");
            Printer.printSeparator();
        }

        private static void printSaveFileCreated() {
            Printer.printSeparator();
            System.out.println("Save file created.");
            Printer.printSeparator();
        }

        private static void printLoadConfirm() {
            System.out.println("Save file loaded:");
            for (int i = 0; i < List.listCount; i += 1) {
                System.out.println(List.tasks[i].toString());
            }
            Printer.printSeparator();
        }

        private static void printHelp() {
            Printer.printSeparator();
            System.out.println("All actions:");
            System.out.println("To add a deadline: deadline *task* /by *by*");
            System.out.println("To add a todo: todo *task*");
            System.out.println("To add a event: event *task* /from *from* /to *to*");
            System.out.println("To mark a task: mark *task_number*");
            System.out.println("To unmark a task: unmark *task_number*");
            System.out.println("To delete a task: delete *task_number*");
            System.out.println("To list all tasks: list");
            System.out.println("To exit: bye");
            Printer.printSeparator();
        }
    }

    private static class InputHandler {

        private static void takeInput() {
            String command;
            while (true) {
                Scanner input = new Scanner(System.in);
                command = input.nextLine();
                if (command.equals("bye")) {
                    break;
                } if (command.equals("list")) {
                    List.list();
                } else if (command.equals("help")) {
                    Printer.printHelp();
                } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                    markOrUnmark(command);
                } else if (command.startsWith("deadline")) {
                    List.addDeadline(command);
                } else if (command.startsWith("todo")) {
                    List.addTodo(command);
                } else if (command.startsWith("event")) {
                    List.addEvent(command);
                } else if (command.startsWith("delete")) {
                    List.deleteTask(command);
                } else {
                    Printer.printInvalidCommand();
                }
            }
        }

        private static void markOrUnmark(String command) {
            try {
                String numberString = command.split(" ")[1];
                int index;
                try {
                    index = Integer.parseInt(numberString) - 1;
                    if (List.listCount == 0) {
                        Printer.printEmptyList();
                    } else if (index >= List.listCount) {
                        Printer.printNonExistentTask();
                    } else if (command.startsWith("mark ")) {
                        List.mark(index);
                    } else {
                        List.unmark(index);
                    }
                } catch (NumberFormatException e) {
                    if (command.startsWith("mark")) {
                        Printer.printInvalidMark();
                    } else {
                        Printer.printInvalidUnmark();
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if (command.startsWith("mark")) {
                    Printer.printInvalidMark();
                } else {
                    Printer.printInvalidUnmark();
                }
            } catch (NumberFormatException e) {
                Printer.printInvalidCommand();                                                                          
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static class List {

        public static final int MAX_TASKS = 100;

        private static class Task {
            protected String description;
            protected boolean isDone;
            protected int taskNum;
            public Task(String description, int taskNum) {
                this.description = description;
                this.isDone = false;
                this.taskNum = taskNum;
            }
            public String getStatusIcon() {
                return (isDone ? "[X]" : "[ ]");
            }
            @Override
            public String toString() {
                return tasks[taskNum].getStatusIcon() + " " + tasks[taskNum].description;
            }
        }

        private static class Deadline extends Task {
            protected String by;
            private Deadline(String description, int taskNum, String by) {
                super(description, taskNum);
                this.by = by;
            }
            @Override
            public String toString() {
                return (this.taskNum + 1) + ".[D]" + super.toString() + " BY: " + by;
            }
        }

        private static class ToDo extends Task {
            private ToDo(String description, int taskNum) {
                super(description, taskNum);
            }
            @Override
            public String toString() {
                return (this.taskNum + 1) + ".[T]" + super.toString();
            }
        }

        private static class Event extends Task {
            protected String from;
            protected String to;
            private Event(String description, int taskNum, String from, String to) {
                super(description, taskNum);
                this.from = from;
                this.to = to;
            }
            @Override
            public String toString() {
                return (this.taskNum + 1) + ".[E]" + super.toString() + " FROM: " + from + " TO: " + to;
            }
        }

        private static int listCount = 0;

        private static Task[] tasks;

        private static void init() {
            tasks = new Task[MAX_TASKS];
        }

        private static void addDeadline(String taskDesc) {
            try {
                String[] words = taskDesc.split(" /by ");
                Task newTask = new Deadline(words[0].substring(9), listCount, words[1]);
                tasks[listCount] = newTask;
                listCount += 1;
                Printer.printAddConfirm(newTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                Printer.printInvalidDeadline();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private static void addTodo(String taskDesc) {
            try {
                Task newTask = new ToDo(taskDesc.substring(5), listCount);
                tasks[listCount] = newTask;
                listCount += 1;
                Printer.printAddConfirm(newTask);
            }
            catch (StringIndexOutOfBoundsException e) {
                Printer.printInvalidTodo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private static void addEvent(String taskDesc) {
            try {
                String[] words = taskDesc.split(" /from | /to ");
                try {
                    Task newTask = new Event(words[0].substring(6), listCount, words[1], words[2]);
                    tasks[listCount] = newTask;
                    listCount += 1;
                    Printer.printAddConfirm(newTask);
                } catch (StringIndexOutOfBoundsException e) {
                    Printer.printInvalidEvent();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                Printer.printInvalidEvent();
            }
        }

        private static void deleteTask (String command) {
            try {
                String numberString = command.split(" ")[1];
                int toBeDeleted;
                try {
                    toBeDeleted = Integer.parseInt(numberString) - 1;
                    if (listCount == 0) {
                        Printer.printEmptyList();
                    } else if (toBeDeleted >= listCount) {
                        Printer.printNonExistentTask();
                    } else {
                        Printer.printDeleteConfirm(tasks[toBeDeleted]);
                        while (toBeDeleted < listCount - 1) {
                            tasks[toBeDeleted] = tasks[toBeDeleted + 1];
                            tasks[toBeDeleted].taskNum -= 1;
                            toBeDeleted += 1;
                        }
                        listCount -= 1;
                    }
                } catch (NumberFormatException e) {
                    Printer.printInvalidDelete();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                Printer.printInvalidDelete();
            }
        }

        private static void list() {
            if (listCount == 0) {
                Printer.printEmptyList();
            } else {
                Printer.printSeparator();
                String toBePrinted = "The list has " + listCount + " task";
                if (listCount == 1) {
                    toBePrinted += ".";
                } else {
                    toBePrinted += "s.";
                }
                System.out.println(toBePrinted);
                for (int i = 0; i < listCount; i += 1) {
                    System.out.println(tasks[i].toString());
                }
                Printer.printSeparator();
            }
        }

        private static void mark(int toBeMarked) throws IOException {
            if (tasks[toBeMarked].isDone) {
                Printer.printAlreadyMarked();
            } else {
                tasks[toBeMarked].isDone = true;
                Printer.printMarkConfirm(tasks[toBeMarked]);
            }
        }

        private static void unmark(int toBeUnmarked) throws IOException {
            if (!tasks[toBeUnmarked].isDone) {
                Printer.printAlreadyUnmarked();
            } else {
                tasks[toBeUnmarked].isDone = false;
                Printer.printUnmarkConfirm(tasks[toBeUnmarked]);
            }
        }

        private static void addTaskFromSave(String taskType, String taskStatus, String taskDesc) {
            switch (taskType) {
            case "T" -> {
                Task newTask = new ToDo(taskDesc, listCount);
                tasks[listCount] = newTask;
            }
            case "D" -> {
                String[] words = taskDesc.split(" /by ");
                Task newTask = new Deadline(words[0], listCount, words[1]);
                tasks[listCount] = newTask;
            }
            case "E" -> {
                String[] words = taskDesc.split(" /from | /to ");
                Task newTask = new Event(words[0], listCount, words[1], words[2]);
                tasks[listCount] = newTask;
            }
            }
            listCount += 1;
            if (taskStatus.equals("X")) {
                tasks[listCount - 1].isDone = true;
            }
        }

    }

    private static class File {

        private static java.io.File f;
        private static String path;

        private static void init() throws IOException{
            f = new java.io.File(".data/SaveFile.txt");
            path = f.getAbsolutePath();
            if (!f.exists()) {
                if (f.getParentFile().mkdirs() && f.createNewFile()) {
                    Printer.printSaveFileCreated();
                }
            }
        }

        private static void clear() throws IOException {
            FileWriter fw = new FileWriter(path);
            fw.write("");
            fw.close();
        }

        private static void appendToFile(String textToAppend) throws IOException {
            FileWriter fw = new FileWriter(path, true);
            fw.write(textToAppend);
            fw.close();
        }

        private static void loadFile() throws IOException {
            Scanner s = new Scanner(f);
            StringBuilder fileContent = new StringBuilder();
            while (s.hasNext()) {
                fileContent.append(s.nextLine());
                fileContent.append("\n");
            }
            String fileString = fileContent.toString();
            if (!fileString.isBlank()) {
                decipherAllInfo(fileString);
            }
        }

        private static void decipherAllInfo(String fileContent) {
            String[] arrayOfTasks = fileContent.split("\n");
            for (String task : arrayOfTasks) {
                String[] stringArray = task.split("\\|");
                String taskType = stringArray[0];
                String taskStatus = stringArray[1];
                String taskDesc = stringArray[2].trim();
                List.addTaskFromSave(taskType, taskStatus, taskDesc);
            }
            Printer.printLoadConfirm();
        }

        private static void save() {
            for (int i = 0; i < List.listCount; i += 1) {
                try {
                    String taskString = List.tasks[i].toString();
                    String stringToWrite = taskString.substring(3);
                    stringToWrite = stringToWrite.replaceAll("\\[", "|");
                    stringToWrite = stringToWrite.replaceAll("]", "|");
                    stringToWrite = stringToWrite.replaceAll("BY:", "/by");
                    stringToWrite = stringToWrite.replaceAll("FROM:", "/from");
                    stringToWrite = stringToWrite.replaceAll("TO:", "/to");
                    stringToWrite = stringToWrite.replace("||", "|");
                    appendToFile(stringToWrite);
                    appendToFile("\n");
                } catch (IOException | NullPointerException e) {
                    return;
                }
            }
        }

    }

}