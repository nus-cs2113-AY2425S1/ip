package freedom.tasks;

import freedom.ui.UiTaskList;

import java.util.ArrayList;

public class TaskList {
    private static final UiTaskList ui = new UiTaskList();

    private ArrayList<Task> tasks;
    private int lastIndex;

    public TaskList() {
        setTasks(new ArrayList<>());
        setLastIndex(0);
    }

    public TaskList(ArrayList<Task> tasks) {
        setTasks(tasks);
        setLastIndex(tasks.size());
    }

    public void addTask(String type, String input) {
        String description;
        try {
            switch (type) {
            case "todo":
                description = input.replaceFirst("todo", "");
                tasks.add(new ToDo(description));
                break;
            case "deadline":
                description = input.replaceFirst("deadline", "");
                tasks.add(new Deadline(description));
                break;
            case "event":
                description = input.replaceFirst("event", "");
                tasks.add(new Event(description));
                break;
            default:
                throw new Exception();
            }

            ui.printAddedTask(tasks.get(getLastIndex()).generateTaskLine(), getLastIndex() + 1);
            setLastIndex(getLastIndex() + 1);

        } catch (Exception e) {
            ui.printPlaceholder();
        }
    }

    public void editTask(String[] words) {
        final int COMMAND_INDEX = 0;
        final int TASK_INDEX = 1;
        int listNumber = Integer.parseInt(words[TASK_INDEX]);
        int taskIndexInStorage = listNumber - 1;
        Task taskToBeMarked;

        try {
            if (taskIndexInStorage >= lastIndex) {
                throw new ArrayIndexOutOfBoundsException();
            }
            taskToBeMarked = tasks.get(taskIndexInStorage);
            switch (words[COMMAND_INDEX]) {
            case "mark":
                taskToBeMarked.markDone();
                tasks.set(taskIndexInStorage, taskToBeMarked);
                ui.printEditedTask(taskToBeMarked.generateTaskLine(), "mark");
                break;
            case "unmark":
                taskToBeMarked.markUndone();
                tasks.set(taskIndexInStorage, taskToBeMarked);
                ui.printEditedTask(taskToBeMarked.generateTaskLine(), "unmark");
                break;
            case "delete":
                tasks.remove(taskIndexInStorage);
                ui.printEditedTask(taskToBeMarked.generateTaskLine(), "delete");
                setLastIndex(getLastIndex() - 1);
                break;
            default:
                throw new Exception();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printArrayIndexOutOfBoundsException();
        } catch (Exception e) {
            ui.printPlaceholder();
        }
    }

    public void printList(ArrayList<Task> tasks) {
        ui.printListHeader();
        int counter = 1;
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + counter + "." + tasks.get(i).generateTaskLine());
            counter++;
        }
        ui.printTailDivider();
    }

    public void findTasks(String input) {
        String keyword = input.replaceFirst("find", "").trim();
        Task taskToCheck;
        ArrayList<Task> compactTaskList = new ArrayList<>();
        for (int i = 0; i < getLastIndex(); i++) {
            taskToCheck = tasks.get(i);
            if (taskToCheck.containKeyword(keyword)) {
                compactTaskList.add(taskToCheck);
            }
        }
        printList(compactTaskList);
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
