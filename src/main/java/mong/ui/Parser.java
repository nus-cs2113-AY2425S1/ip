package mong.ui;

import mong.exception.IllegalTaskTypeException;
import mong.task.Task;
import mong.task.TaskType;

import static mong.Mong.addTodo;
import static mong.Mong.addEvent;
import static mong.Mong.addDeadline;

import static mong.Mong.list;

public class Parser {
    /**
     * Returns the TaskType enum value of the first word in the input.
     */
    public static TaskType decipherTaskType(String input) throws IllegalTaskTypeException {
        try {
            return TaskType.fromCommand(input);
        } catch (IllegalTaskTypeException e) {
            throw new IllegalTaskTypeException(e.getMessage());
        }
    }

    /**
     * Formats latest list to txt file format.
     */
    public static String parseListToTxt() {
        StringBuilder textToAdd = new StringBuilder();
        for (Task task : list) {
            textToAdd.append(task.toFileFormat()).append(System.lineSeparator());
        }
        return textToAdd.toString();
    }

    /**
     * Adds tasks line by line from txt file.
     */
    public static void parseLineContents(String line) {
        String[] lineData = line.split(" \\| ");
        if (lineData[0].contentEquals("T")) {
            addTodo(lineData[1], lineData[2]);
        } else if (lineData[0].contentEquals("D")) {
            addDeadline(lineData[1], lineData[2], lineData[3]);
        } else if (lineData[0].contentEquals("E")) {
            addEvent(lineData[1], lineData[2], lineData[3], lineData[4]);
        }
    }
}
