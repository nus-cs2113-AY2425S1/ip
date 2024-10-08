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

        private static void printDeleteConfirm(List.Task task) {
            Printer.printSeparator();
            System.out.println("I have deleted the following task.");
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
            }
        }

    }

    private static class List {

        public static final int MAX_TASKS = 100;

        private static class Task {
            protected String description;
            protected boolean isDone;
            public Task(String description) {
                this.description = description;
                this.isDone = false;
            }
            public String getStatusIcon() {
                return (isDone ? "[X]" : "[ ]");
            }
            @Override
            public String toString() {
                return this.getStatusIcon() + " " + this.description;
            }
        }

        private static class Deadline extends Task {
            protected String by;
            private Deadline(String description, String by) {
                super(description);
                this.by = by;
            }
            @Override
            public String toString() {
                return "[D]" + super.toString() + " (by: " + by + ")";
            }
        }

        private static class ToDo extends Task {
            private ToDo(String description) {
                super(description);
            }
            @Override
            public String toString() {
                return "[T]" + super.toString();
            }
        }

        private static class Event extends Task {
            protected String from;
            protected String to;
            private Event(String description, String from, String to) {
                super(description);
                this.from = from;
                this.to = to;
            }
            @Override
            public String toString() {
                return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
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
                Task newTask = new Deadline(words[0].substring(9), words[1]);
                tasks[listCount] = newTask;
                listCount += 1;
                Printer.printAddConfirm(newTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                Printer.printInvalidDeadline();
            }
        }

        private static void addTodo(String taskDesc) {
            try {
                Task newTask = new ToDo(taskDesc.substring(5));
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
                    Task newTask = new Event(words[0].substring(6), words[1], words[2]);
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
                        while (toBeDeleted < listCount) {
                            tasks[toBeDeleted] = tasks[toBeDeleted + 1];
                            toBeDeleted += 1;
                        }
                        listCount -= 1;
                    }
                } catch (NumberFormatException e) {
                    Printer.printInvalidDelete();
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
                    System.out.println(i + 1 + "." + tasks[i].toString());
                }
                Printer.printSeparator();
            }
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