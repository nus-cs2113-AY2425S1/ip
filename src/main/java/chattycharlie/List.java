package chattycharlie;

import chattycharlie.task.Deadline;
import chattycharlie.task.Event;
import chattycharlie.task.Task;
import chattycharlie.task.Todo;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//LIST CLASS
    public class List {
        //make a list of task
        private ArrayList<Task> tasks;
        private int size;

        //constructor
        public List() {
            tasks = new ArrayList<Task>();
            size = 0;
        }

        //Method to add an item to the list
        public void addTask(Task task) {
            //add the text into the list
            tasks.add(task);
            //account for the item
            size++;
        }

        public ArrayList<Task> getList() {
            return this.tasks;
        }

        public int getSize() {
            return size;
        }

        //To mark
        public void mark(int index) {
            if (index >= 0 && index < size) {
                tasks.get(index).markTask();
                int remainingTask = countUnmarkedTasks();
                System.out.println(StringDesign.SPACE + "Well Done! 1 task down, " + remainingTask + " to go.");
                System.out.println(StringDesign.SPACE+ tasks.get(index).toString());
            } else {
                System.out.println(StringDesign.SPACE+ "Invalid task number.");
            }
        }

        //To unmark
        public void unmark(int index) {
            if (index >= 0 && index < size) {
                tasks.get(index).unmarkTask();
                int remainingTask = countUnmarkedTasks();
                System.out.println(StringDesign.SPACE + "Hmmm, not quite done yet, " + remainingTask + " to go.");
                System.out.println(StringDesign.SPACE + tasks.get(index).toString());
            } else {
                System.out.println(StringDesign.SPACE + "Invalid task number.");
            }
        }

        //To delete
    public void delete(int index) {
            if (index >= 0 && index < size) {
                int remainingTask;
                if(tasks.get(index).getIsDoneStatus()) {
                    remainingTask = countUnmarkedTasks();
                } else {
                    remainingTask = countUnmarkedTasks() -1;
                }
                System.out.println(StringDesign.SPACE + "Task is removed." + " Pending task: " + remainingTask);
                System.out.println(StringDesign.SPACE + tasks.get(index).toString());
                tasks.remove(index);
                size--;
            }
    }

        //To print list
        public void printList() {
            //print all
            int remainingTask = countUnmarkedTasks();
            System.out.println("ToDo List:");
            System.out.println("pending Task: " + remainingTask);
            for (int i = 0; i < size; i++) {
                int number = i+1;
                Task task = tasks.get(i);
                //use a switch to determine
                switch (task.getType()) {
                case TODO:
                    Todo todoTask = (Todo) task;
                    System.out.println(StringDesign.SPACE + number + todoTask);
                    break;
                case DEADLINE:
                    Deadline deadlineTask = (Deadline) task;
                    System.out.println(StringDesign.SPACE + number + deadlineTask);
                    break;
                case EVENT:
                    Event eventTask = (Event) task;
                    System.out.println(StringDesign.SPACE + number + eventTask);
                    break;
                default:
                    break;
                }
            }
        }

        // Method to count how many tasks are unmarked
        public int countUnmarkedTasks() {
            int count = 0;
            for (int i = 0; i < size; i++) {
                if (!tasks.get(i).getIsDoneStatus()) {
                    count++;
                }
            }
            return count;
        }

        //read in a file
    public void readTaskFromFile(String filename) {
            File file = new File(filename);

            try {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String taskText = scanner.nextLine();
                    //create task object from the text line
                    parseTaskFromText(taskText);
                }
                scanner.close();
                System.out.println(StringDesign.CHARLIE + "Task have been loaded from: " + filename);
            } catch (FileNotFoundException e) {
                System.out.println("Oh first time here? Welcome to a life of Productivity, lets start!");
            }
    }

    // converting text to task
    public void parseTaskFromText(String taskText) {
            taskText = taskText.trim(); //trim away the formatting
            char taskType = taskText.charAt(1); //get the commandType
            boolean isMarked = taskText.charAt(4) == 'X'; //get status for marked
        String description;
        if (taskType == 'T') {
            description = taskText.substring(7).trim();
        } else {
            int parenIndex = taskText.indexOf('(');
            description = taskText.substring(7, parenIndex).trim();  // Get description before '('
        }
        Task task;
        switch (taskType) {
        case 'T':
            task = new Todo(description);
            if (isMarked) {
                task.markTask();
            }
            addTask(task);
            break;
        case 'D':
            String by = taskText.substring(taskText.indexOf("(by: ") + 5, taskText.length() - 1).trim();
            task = new Deadline(description, by);  // Add Deadline task
            //check if its marked
            if(isMarked) {
                task.markTask();
            }
            addTask(task);
            break;
        case 'E':
            String eventInfo = taskText.substring(taskText.indexOf("(from: ") + 7, taskText.length() - 1).trim();
            String[] eventTimes = eventInfo.split(" to: ");
            if (eventTimes.length == 2) {
                String startTime = eventTimes[0].trim();
                String endTime = eventTimes[1].trim();
                task = new Event(description, startTime, endTime);  // Add Event task
                if (isMarked) {
                    task.markTask();
                }
                addTask(task);
            } else {
                System.out.println("Invalid event time format: " + taskText);
            }
            break;
        default:
            throw new IllegalArgumentException("Unknown task type");
        }
    }

            //to save a file (called before the program ends)
            public void saveTasksToFile(String filename) {
            File file = new File(filename);

            try {
                FileWriter writer = new FileWriter(file);

                for (Task task : tasks) {
                   if (task != null) {
                       writer.write(taskToString(task) + "\n");
                    }
                }

                writer.close();
                System.out.println(StringDesign.CHARLIE + "We saved your file in: " + filename);
            } catch (IOException e) {
                System.out.println("An error has occured when saving tasks");
            }
        }

        public String taskToString(Task task) {
        return StringDesign.SPACE + task;
        }
    }