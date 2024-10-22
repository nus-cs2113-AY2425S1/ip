/**
 * Handles storage of tasks in a file. Storage class is responsible for loading tasks from a file
 * and saving tasks back to the same file.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

  private String filePath;

  /**
   * Constructs a Storage object with the specified file path.
   */
  public Storage(String filePath) {
    this.filePath = filePath;
  }

  public TaskList load() throws IOException {
    /**
     * Loads tasks from specified file and returns TaskList
     * containing loaded tasks.
     */
    TaskList taskList = new TaskList();
    File file = new File(filePath);
    if (!file.exists()) {
      File dir = new File("./data");
      if (!dir.exists()) {
        dir.mkdirs(); //creating directory if it does not exist
      }
      file.createNewFile(); //creating file if it does not exist
    } else {
      Scanner fileScanner = new Scanner(file);
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
          System.out.println("Skipping malformed line: " + line);
          continue; // Skip this iteration if the line doesn't have enough parts
        }

        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task = new Task(description);
        if (isDone) {
          task.markAsDone(); //mark task as done if it was completed by user
        }
        taskList.addTask(task);
      }
      fileScanner.close(); //closing the scanner to release resources to the system
    }
    return taskList;
  }

  public void save(TaskList tasks) throws IOException {
    FileWriter writer = new FileWriter(filePath);
    for (Task task : tasks.getTasks()) {
      writer.write((task.isDone() ? "[D]" : "[T]") + " | " + task.getDescription() + "\n");
    }
    writer.close();
  }

}
