    //LIST CLASS
    public class List {
        //make a list of task
        private Task[] tasks;
        private int size;

        //constructor
        public List() {
            tasks = new Task[100];
            size = 0;
        }

        //Method to add an item to the list
        public void addTask(Task task) {
            //add the text into the list
            tasks[size] = task;
            //account for the item
            size++;
        }

        public Task getTasks(int index) {
            return this.tasks[index];
        }

        public Task[] getList() {
            return this.tasks;
        }

        public int getSize() {
            return size;
        }

        //To mark
        public void mark(int index) {
            if (index >= 0 && index < size) {
                tasks[index].markTask();
                int remainingTask = countUnmarkedTasks();
                System.out.println(StringDesign.SPACE + "Well Done! 1 task down, " + remainingTask + " to go.");
                System.out.println(StringDesign.SPACE+ "[" + tasks[index].getMarkedStatus() + "] " + tasks[index].description);
            } else {
                System.out.println(StringDesign.SPACE+ "Invalid task number.");
            }
        }

        //To unmark
        public void unmark(int index) {
            if (index >= 0 && index < size) {
                tasks[index].unmarkTask();
                int remainingTask = countUnmarkedTasks();
                System.out.println(StringDesign.SPACE + "Hmmm, not quite done yet, " + remainingTask + " to go.");
                System.out.println(StringDesign.SPACE + "[" + tasks[index].getMarkedStatus() + "] " + tasks[index].description);
            } else {
                System.out.println(StringDesign.SPACE + "Invalid task number.");
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
                Task task = tasks[i];
                //use a switch to determine
                switch (task.getType()) {
                case TODO:
                    Todo todoTask = (Todo) task;
                    System.out.println(StringDesign.SPACE + number + ".[T][" + todoTask.getMarkedStatus() + "] "
                            + todoTask.description);
                    break;
                case DEADLINE:
                    Deadline deadlineTask = (Deadline) task;
                    System.out.println(StringDesign.SPACE + number + ".[D][" + deadlineTask.getMarkedStatus() + "] "
                            + deadlineTask.description + " (by: " + deadlineTask.by + ")");
                    break;
                case EVENT:
                    Event eventTask = (Event) task;
                    System.out.println(StringDesign.SPACE + number + ".[E][" + eventTask.getMarkedStatus() + "] "
                            + eventTask.description + " (from: " + eventTask.start + " to: " + eventTask.end + ")");
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
                if (!tasks[i].isDone) {
                    count++;
                }
            }
            return count;
        }
    }