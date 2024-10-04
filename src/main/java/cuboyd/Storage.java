package cuboyd;

import cuboyd.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.Scanner;

/**
 * Handles storage
 */
public class Storage {
    public String filePath;

    /**
     * Creates the Storage Object. Sets the file path to load from and save to
     * @param filePath File Path to load from and save to
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes a task to a file using FileWriter.
     * @param task Task to write
     * @param fw FileWriter
     * @throws IOException If there are issues writing to the file
     */
    private void writeTaskToFileWriter(Task task, FileWriter fw) throws IOException {
        if (task instanceof ToDo todo) {
            writeToDoToFileWriter(todo, fw);
        } else if (task instanceof Deadline deadline) {
            writeDeadlineToFileWriter(deadline, fw);
        } else if (task instanceof Event event) {
            writeEventToFileWriter(event, fw);
        }
    }

    /**
     * Writes a ToDo to a file using FileWriter.
     * @param todo ToDo to write
     * @param fw FileWriter
     * @throws IOException If there are issues writing to the file
     */
    private void writeToDoToFileWriter(ToDo todo, FileWriter fw) throws IOException {
        fw.write(String.format(
                "T|%s|%s\n",
                todo.getStatusIcon(),
                Base64.getEncoder().encodeToString(todo.getDescription().getBytes(StandardCharsets.UTF_8))
        ));
    }

    /**
     * Writes a Deadline to a file using FileWriter.
     * @param deadline Deadline to write
     * @param fw FileWriter
     * @throws IOException If there are issues writing to the file
     */
    private void writeDeadlineToFileWriter(Deadline deadline, FileWriter fw) throws IOException {
        fw.write(String.format(
                "D|%s|%s|%s\n",
                deadline.getStatusIcon(),
                Base64.getEncoder().encodeToString(
                        deadline.getDescription().getBytes(StandardCharsets.UTF_8)),
                Base64.getEncoder().encodeToString(deadline.getBy().getBytes(StandardCharsets.UTF_8))
        ));
    }

    /**
     * Writes a Event to a file using FileWriter.
     * @param event Event to write
     * @param fw FileWriter
     * @throws IOException If there are issues writing to the file
     */
    private void writeEventToFileWriter(Event event, FileWriter fw) throws IOException {
        fw.write(String.format(
                "E|%s|%s|%s|%s\n",
                event.getStatusIcon(),
                Base64.getEncoder().encodeToString(event.getDescription().getBytes(StandardCharsets.UTF_8)),
                Base64.getEncoder().encodeToString(event.getFrom().getBytes(StandardCharsets.UTF_8)),
                Base64.getEncoder().encodeToString(event.getTo().getBytes(StandardCharsets.UTF_8))
        ));
    }

    /**
     * Writes a list of tasks to a file using FileWriter.
     * @param fw FileWriter
     * @throws IOException If there are issues writing to the file
     */
    private void writeTaskListToFileWriter(TaskList taskList, FileWriter fw) throws IOException {
        for (int i = 0; i < taskList.size(); i++) {
            writeTaskToFileWriter(taskList.getTask(i), fw);
        }
    }

    /**
     * Saves data from a task list into a file.
     * @param taskList Task List to save into a file
     * @throws CuboydException If there are any issues saving
     */
    public void save(TaskList taskList) throws CuboydException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
            writeTaskListToFileWriter(taskList, fw);
            fw.close();
        } catch (IOException e) {
            throw new CuboydException(String.format(
                    "Having issues saving to %s! Check your file permissions!\n", filePath));
        }
    }

    /**
     * Returns the task represented by a given line (from the save file).
     * @param line Line
     * @return Task parsed from the line
     */
    private Task parseLineAsTask(String line) {
        String[] list = line.split("\\|");
        Task currentTask = null;
        switch (list[0]){
        case "T":
            currentTask = new ToDo(
                    new String(Base64.getDecoder().decode(list[2]), StandardCharsets.UTF_8)
            );
            break;
        case "D":
            currentTask = new Deadline(
                    new String(Base64.getDecoder().decode(list[2]), StandardCharsets.UTF_8),
                    new String(Base64.getDecoder().decode(list[3]), StandardCharsets.UTF_8)
            );
            break;
        case "E":
            currentTask = new Event(
                    new String(Base64.getDecoder().decode(list[2]), StandardCharsets.UTF_8),
                    new String(Base64.getDecoder().decode(list[3]), StandardCharsets.UTF_8),
                    new String(Base64.getDecoder().decode(list[4]), StandardCharsets.UTF_8)
            );
            break;
        }
        if (currentTask != null && Objects.equals(list[1], "X")) {
            currentTask.markAsDone();
        }
        return currentTask;
    }

    /**
     * Loads data from a scanner into a task list.
     * @param scanner Scanner to load data from
     * @param taskList Task List to load the save data into
     * @throws CuboydException If there are any issues on loading
     */
    private void loadFromScanner(Scanner scanner, TaskList taskList) throws Exception {
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Task currentTask = parseLineAsTask(line);
            if (currentTask == null){ throw new Exception(); }
            taskList.addTask(currentTask);
        }
    }

    /**
     * Loads the file into a task list.
     * @param taskList Task List to load the save data into
     * @throws CuboydException If there are any issues on loading
     */
    public void load(TaskList taskList) throws CuboydException {
        File f = new File(filePath);
        Scanner s = null; // create a Scanner using the File as the source
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new CuboydException(String.format(
                    "Having issues reading from %s! Check your file permissions!", filePath));
        }

        taskList.clear();
        try {
            loadFromScanner(s, taskList);
        } catch (Exception e) {
            throw new CuboydException(String.format(
                    "Error when reading from %s! Savefile might be corrupted!", filePath));
        }
    }
}