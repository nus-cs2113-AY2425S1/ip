import customexceptions.DeadlineConstructorException;
import customexceptions.EventConstructorException;
import customexceptions.ToDoConstructorException;
import taskpackage.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class handles reading and writing task data from and to a file.
 */
public class Storage {

    private final String dataFilePath;
    private final UI ui;

    /**
     * Constructor for Storage.
     *
     * @param dataFilePath Path to the file for storing task data.
     * @param ui           The user interface to display messages.
     */
    public Storage(String dataFilePath, UI ui) {
        this.dataFilePath = dataFilePath;
        this.ui = ui;
    }

    /**
     * Reads task data from the file and returns a TaskList.
     *
     * @return A TaskList containing the tasks from the file.
     */
    public TaskList readData() {

        TaskList tempTaskList = new TaskList();
        try {
            File dataFile = new File(dataFilePath);
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                try {
                    handleDataLine(inputLine, tempTaskList);

                } catch (Exception e) {
                    ui.printMessage("ERROR READING LINE: " + inputLine);
                    ui.printMessage(e.getMessage());
                }
            }
            ui.printMessage("Data File Read");
        } catch (FileNotFoundException e) {
            ui.printMessage("Data File does not exist");
            return null;
        }

        return tempTaskList;
    }

    /**
     * Handles each line of the data file and adds the corresponding task to the TaskList.
     *
     * @param inputLine The line from the data file.
     * @param taskList  The TaskList to which the task will be added.
     */
    private void handleDataLine(String inputLine, TaskList taskList) {
        String[] lineSegments = inputLine.split(" /isdone ");
        String line = lineSegments[0];
        boolean isDone;
        if (lineSegments[1].trim().equals("true")) {
            isDone = true;
        } else if (lineSegments[1].trim().equals("false")) {
            isDone = false;
        } else {
            ui.printMessage("ERROR READING ISDONE VALUE: " + inputLine);
            return;
        }
        if (line.startsWith(CommandHandling.TODO_COMMAND)) {
            try {
                new ToDo(line.replace(CommandHandling.TODO_COMMAND, ""), taskList, false); // Create a new ToDo object
            } catch (ToDoConstructorException e) {
                ui.printMessage("CORRUPTED: " + line);
                taskList.deleteLatestTask();
            }
        } else if (line.startsWith(CommandHandling.DEADLINE_COMMAND)) {
            try {
                new Deadline(line.replace(CommandHandling.DEADLINE_COMMAND, ""), taskList, false); // Create a new Deadline object
            } catch (DeadlineConstructorException e) {
                ui.printMessage("CORRUPTED: " + line);
                taskList.deleteLatestTask();
            }
        } else if (line.startsWith(CommandHandling.EVENT_COMMAND)) {
            try {
                new Event(line.replace(CommandHandling.EVENT_COMMAND, ""), taskList, false); // Create a new Event object
            } catch (EventConstructorException e) {
                ui.printMessage("CORRUPTED: " + line);
                taskList.deleteLatestTask();
            }
        } else {
            ui.printMessage("CORRUPTED: " + line);
        }

        if (isDone) {
            taskList.markLatestTask();
        }
    }

    /**
     * Writes the task data to the file.
     *
     * @param taskList The TaskList to write to the file.
     */
    public void writeDate(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(dataFilePath);
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.dataFileEntry(i));
            }
            writer.close();
            ui.printMessage("Data File Written");
        } catch (IOException e) {
            ui.porFavorMessage(e.getMessage());
        }
    }
}
