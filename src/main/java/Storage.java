import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
  private String filePath;

  public Storage(String filePath) {
    this.filePath = filePath;
  }

  public TaskList load() throws IOException {
    TaskList taskList = new TaskList();
    File file = new File(filePath);
    if (!file.exists()) {
      File dir = new File("./data");
      if (!dir.exists()) {
        dir.mkdirs();
      }
      file.createNewFile();
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
          task.markAsDone();
        }
        taskList.addTask(task);
      }
      fileScanner.close();
    }
    return taskList;
  }

  public void save(TaskList tasks) throws IOException {
    FileWriter writer = new FileWriter(filePath);
    for (Task task : tasks.getTasks()) {
      writer.write("T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + "\n");
    }
    writer.close();
  }
}
