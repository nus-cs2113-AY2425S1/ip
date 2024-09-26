package chattycharlie;

import chattycharlie.userinteractions.Ui;
import chattycharlie.task.Deadline;
import chattycharlie.task.Event;
import chattycharlie.task.Task;
import chattycharlie.task.Todo;
import java.util.ArrayList;

//LIST CLASS
    public class TaskList {
        private ArrayList<Task> tasks;
        private int size;

        //constructor
        public TaskList() {
            tasks = new ArrayList<Task>();
            size = 0;
        }

        public TaskList(TaskList list){
            this.tasks = list.tasks;
            this.size = list.size;
        }

        //Method to add an item to the list
        public void addTask(Task task) {
            tasks.add(task);
            size++;
        }

        public ArrayList<Task> getList() {
            return this.tasks;
        }

        public Task getTask(int index) {
            return this.tasks.get(index);
        }

        public int getSize() {
            return size;
        }

        //To mark
        public void markTask(int index) {
            Ui ui = new Ui();
            if (index >= 0 && index < size) {
                tasks.get(index).markTask();
                int remainingTask = countUnmarkedTasks();
                ui.displayMarkingText("yay, 1 down!", remainingTask);
            } else {
                ui.displayError("Invalid task number.");
            }
        }

        //To unmark
        public void unmarkTask(int index) {
            Ui ui = new Ui();
            if (index >= 0 && index < size) {
                tasks.get(index).unmarkTask();
                int remainingTask = countUnmarkedTasks();
                ui.displayMarkingText("Hmmm, not quite done yet,", remainingTask);
            } else {
                ui.displayError("Invalid task number.");
            }
        }

        //To delete
    public void deleteTask(int index) {
        Ui ui = new Ui();
            if (index >= 0 && index < size) {
                int remainingTask;
                if(tasks.get(index).getIsDoneStatus()) {
                    remainingTask = countUnmarkedTasks();
                } else {
                    remainingTask = countUnmarkedTasks() -1;
                }
                ui.displayDeletedTask("Task is removed. Pending task: ", remainingTask);
                ui.displayTask(tasks.get(index));
                tasks.remove(index);
                size--;
            }
    }

        //To print list
        public void printList() {
            int remainingTask = countUnmarkedTasks();
            Ui ui = new Ui();
            ui.displayListHeader(remainingTask);
            for (int i = 0; i < size; i++) {
                int number = i+1;
                Task task = tasks.get(i);
                //use a switch to determine
                switch (task.getType()) {
                case TODO:
                    Todo todoTask = (Todo) task;
                    ui.displayTaskInList(todoTask, number);
                    break;
                case DEADLINE:
                    Deadline deadlineTask = (Deadline) task;
                    ui.displayTaskInList(deadlineTask, number);
                    break;
                case EVENT:
                    Event eventTask = (Event) task;
                    ui.displayTaskInList(eventTask, number);
                    break;
                default:
                    break;
                }
            }
            ui.displayLine();
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
    }