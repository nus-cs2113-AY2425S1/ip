package Tars;

import Tars.Task.Deadline;
import Tars.Task.Event;
import Tars.Task.Task;
import Tars.Task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    // 构造函数初始化文件路径
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // 保存任务到文件
    public void saveTasks(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        createDirectoryIfNotExists(file);

        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Task task : tasks) {
                fileWriter.write(task.toSaveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
            throw e;  // 重新抛出异常以让上层调用处理
        }
    }

    // 从文件加载任务列表
    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;  // 如果文件不存在，返回空的任务列表
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                Task task = convertStringToTask(taskString);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
            throw e;
        }
        return tasks;
    }

    // 将字符串转换为任务对象
    private Task convertStringToTask(String taskString) {
        String[] taskParts = taskString.split(" \\| ");
        String type = taskParts[0];
        boolean isDone = taskParts[1].equals("1");

        // 根据任务类型创建不同的任务对象
        switch (type) {
            case "T":
                return new Todo(taskParts[2], isDone);
            case "D":
                return new Deadline(taskParts[2], taskParts[3], isDone);
            case "E":
                return new Event(taskParts[2], taskParts[3], taskParts[4], isDone);
            default:
                System.err.println("Unknown task type found: " + type);
                return null;  // 返回 null 以处理未知的任务类型
        }
    }

    // 创建文件目录（如果不存在）
    private void createDirectoryIfNotExists(File file) {
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            boolean isCreated = directory.mkdirs();
            if (isCreated) {
                System.out.println("Directory created: " + directory.getAbsolutePath());
            }
        }
    }
}

































//package Tars;
//import Tars.Task.Deadline;
//import Tars.Task.Task;
//import Tars.Task.Todo;
//import Tars.Task.Event;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Scanner;
//import java.util.List;
//import java.util.ArrayList;
//
//public class Storage {
//    private String filePath;
//
//    public Storage(String filePath) {
//        this.filePath = filePath;
//    }
//
//    // Save tasks to file
//    public void saveTasks(List<Task> tasks) throws IOException {
//        File file = new File(filePath);
//        File directory = file.getParentFile();
//        if (directory != null && !directory.exists()) {
//            directory.mkdirs();  // Create the "data" directory if it doesn't exist
//        }
//
//        FileWriter fileWriter = new FileWriter(file);
//        for (Task task : tasks) {
//            fileWriter.write(task.toSaveFormat() + System.lineSeparator());
//        }
//        fileWriter.close();
//    }
//
//    // Load tasks from file
//    public List<Task> loadTasks() throws IOException {
//        List<Task> tasks = new ArrayList<>();
//        File file = new File(filePath);
//        if (!file.exists()) {
//            return tasks;
//        }
//
//        Scanner scanner = new Scanner(file);
//        while (scanner.hasNextLine()) {
//            String taskString = scanner.nextLine();
//            tasks.add(convertStringToTask(taskString));
//        }
//        scanner.close();
//        return tasks;
//    }
//
//    // Convert the saved string to a Task object
//    private Task convertStringToTask(String taskString) {
//        String[] taskParts = taskString.split(" \\| ");
//        String type = taskParts[0];
//        boolean isDone = taskParts[1].equals("1");
//
//        switch (type) {
//            case "T":
//                return new Todo(taskParts[2], isDone);
//            case "D":
//                return new Deadline(taskParts[2], taskParts[3], isDone);
//            case "E":
//                return new Event(taskParts[2], taskParts[3], taskParts[4], isDone);
//            default:
//                return null;
//        }
//    }
//}
