import java.io.IOException;

public class TaskList {

    public static final int MAX_TASKS = 100;

    public static class Task {
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

    public static class Deadline extends TaskList.Task {
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

    public static class ToDo extends TaskList.Task {
        private ToDo(String description, int taskNum) {
            super(description, taskNum);
        }
        @Override
        public String toString() {
            return (this.taskNum + 1) + ".[T]" + super.toString();
        }
    }

    public static class Event extends TaskList.Task {
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

    public static int listCount = 0;

    public static TaskList.Task[] tasks;

    public static void init() throws IOException {
        Ui.printEntry();
        tasks = new TaskList.Task[MAX_TASKS];
        Storage.init();
    }

    public static void addDeadline(String taskDesc) {
        try {
            String[] words = taskDesc.split(" /by ");
            TaskList.Task newTask = new TaskList.Deadline(words[0].substring(9), listCount, words[1]);
            tasks[listCount] = newTask;
            listCount += 1;
            Ui.printAddConfirm(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printInvalidDeadline();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addTodo(String taskDesc) {
        try {
            TaskList.Task newTask = new TaskList.ToDo(taskDesc.substring(5), listCount);
            tasks[listCount] = newTask;
            listCount += 1;
            Ui.printAddConfirm(newTask);
        }
        catch (StringIndexOutOfBoundsException e) {
            Ui.printInvalidTodo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addEvent(String taskDesc) {
        try {
            String[] words = taskDesc.split(" /from | /to ");
            try {
                TaskList.Task newTask = new TaskList.Event(words[0].substring(6), listCount, words[1], words[2]);
                tasks[listCount] = newTask;
                listCount += 1;
                Ui.printAddConfirm(newTask);
            } catch (StringIndexOutOfBoundsException e) {
                Ui.printInvalidEvent();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printInvalidEvent();
        }
    }

    public static void deleteTask (String command) throws IOException {
        try {
            String numberString = command.split(" ")[1];
            int toBeDeleted;
            try {
                toBeDeleted = Integer.parseInt(numberString) - 1;
                if (listCount == 0) {
                    Ui.printEmptyList();
                } else if (toBeDeleted >= listCount) {
                    Ui.printNonExistentTask();
                } else {
                    Ui.printDeleteConfirm(tasks[toBeDeleted]);
                    while (toBeDeleted < listCount - 1) {
                        tasks[toBeDeleted] = tasks[toBeDeleted + 1];
                        tasks[toBeDeleted].taskNum -= 1;
                        toBeDeleted += 1;
                    }
                    listCount -= 1;
                }
            } catch (NumberFormatException e) {
                Ui.printInvalidDelete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printInvalidDelete();
        }
        Storage.clear();
        Storage.save();
    }

    public static void list() {
        if (listCount == 0) {
            Ui.printEmptyList();
        } else {
            Ui.printSeparator();
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
            Ui.printSeparator();
        }
    }

    public static void mark(int toBeMarked) throws IOException {
        if (tasks[toBeMarked].isDone) {
            Ui.printAlreadyMarked();
        } else {
            tasks[toBeMarked].isDone = true;
            Ui.printMarkConfirm(tasks[toBeMarked]);
        }
    }

    public static void unmark(int toBeUnmarked) throws IOException {
        if (!tasks[toBeUnmarked].isDone) {
            Ui.printAlreadyUnmarked();
        } else {
            tasks[toBeUnmarked].isDone = false;
            Ui.printUnmarkConfirm(tasks[toBeUnmarked]);
        }
    }

    public static void addTaskFromSave(String taskType, String taskStatus, String taskDesc) {
        switch (taskType) {
        case "T" -> {
            TaskList.Task newTask = new TaskList.ToDo(taskDesc, listCount);
            tasks[listCount] = newTask;
        }
        case "D" -> {
            String[] words = taskDesc.split(" /by ");
            TaskList.Task newTask = new TaskList.Deadline(words[0], listCount, words[1]);
            tasks[listCount] = newTask;
        }
        case "E" -> {
            String[] words = taskDesc.split(" /from | /to ");
            TaskList.Task newTask = new TaskList.Event(words[0], listCount, words[1], words[2]);
            tasks[listCount] = newTask;
        }
        }
        listCount += 1;
        if (taskStatus.equals("X")) {
            tasks[listCount - 1].isDone = true;
        }
    }

}
