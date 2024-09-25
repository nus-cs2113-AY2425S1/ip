import java.util.Scanner;

public class Transcendent {

    public static void main(String[] args) {
        Printer.printEntry();
        List.init();
        InputHandler.takeInput();
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
            System.out.println("Let me know should you need assistance again.");
            System.out.println("Farewell.");
            Printer.printSeparator();
        }

        private static void printAddConfirm(List.Task task) {
            Printer.printSeparator();
            System.out.println("Added: " + task.toString());
            Printer.printSeparator();
        }

        private static void printMarkConfirm(List.Task task) {
            Printer.printSeparator();
            System.out.println("I have marked the following task.");
            System.out.println(task.toString());
            Printer.printSeparator();
        }

        private static void printUnmarkConfirm(List.Task task) {
            Printer.printSeparator();
            System.out.println("I have unmarked the following task.");
            System.out.println(task.toString());
            Printer.printSeparator();
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

        private static void printHelp() {
            Printer.printSeparator();
            System.out.println("All actions:");
            System.out.println("To add a deadline: deadline *task* /by *by*");
            System.out.println("To add a todo: todo *task*");
            System.out.println("To add a event: event *task* /from *from* /to *to*");
            System.out.println("To mark a task: mark *task_number*");
            System.out.println("To unmark a task: unmark *task_number*");
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
                } else {
                    Printer.printInvalidCommand();
                }
            }
        }

        private static void markOrUnmark(String command) {
            String numberString = command.split(" ")[1];
            int index;
            try {
                index = Integer.parseInt(numberString) - 1;
                if (index >= List.listCount) {
                    Printer.printInvalidCommand();
                } else if (command.startsWith("mark ")) {
                    List.mark(index);
                } else {
                    List.unmark(index);
                }
            } catch (NumberFormatException e) {
                Printer.printInvalidCommand();
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
                return (this.taskNum + 1) + "." + "[D]" + super.toString() + " (by: " + by + ")";
            }
        }

        private static class ToDo extends Task {
            private ToDo(String description, int taskNum) {
                super(description, taskNum);
            }
            @Override
            public String toString() {
                return (this.taskNum + 1) + "." + "[T]" + super.toString();
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
                return (this.taskNum + 1) + "." + "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
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
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                Printer.printInvalidEvent();
            }
        }

        private static void list() {
            Printer.printSeparator();
            for (int i = 0; i < listCount; i += 1) {
                System.out.println(tasks[i].toString());
            }
            Printer.printSeparator();
        }

        private static void mark(int toBeMarked){
            if (tasks[toBeMarked].isDone) {
                Printer.printAlreadyMarked();
            } else {
                tasks[toBeMarked].isDone = true;
                Printer.printMarkConfirm(tasks[toBeMarked]);
            }
        }

        private static void unmark(int toBeUnmarked) {
            if (!tasks[toBeUnmarked].isDone) {
                Printer.printAlreadyUnmarked();
            } else {
                tasks[toBeUnmarked].isDone = false;
                Printer.printUnmarkConfirm(tasks[toBeUnmarked]);
            }
        }

    }

}