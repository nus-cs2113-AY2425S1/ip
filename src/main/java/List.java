    //LIST CLASS
    public class List {
        //make a list of task
        private Task[] list;
        private int size;

        //constructor
        public List() {
            list = new Task[100];
            size = 0;
        }

        //Method to add an item to the list
        public void addTask(Task task) {
            //add the text into the list
            list[size] = task;
            //account for the item
            size++;
        }

        public Task[] getList() {
            return this.list;
        }

        public int getSize() {
            return size;
        }

        //To mark
        public void mark(int index) {
            if (index >= 0 && index < size) {
                list[index].markTask();
                int remainingTask = countUnmarkedTasks();
                System.out.println(Constants.SPACE + "Well Done! 1 task down, " + remainingTask + " to go.");
                System.out.println(Constants.SPACE+ "[" + list[index].getMarkedStatus() + "] " + list[index].description);
            } else {
                System.out.println(Constants.SPACE+ "Invalid task number.");
            }
        }

        //To unmark
        public void unmark(int index) {
            if (index >= 0 && index < size) {
                list[index].unmarkTask();
                int remainingTask = countUnmarkedTasks();
                System.out.println(Constants.SPACE + "Hmmm, not quite done yet, " + remainingTask + " to go.");
                System.out.println(Constants.SPACE + "[" + list[index].getMarkedStatus() + "] " + list[index].description);
            } else {
                System.out.println(Constants.SPACE + "Invalid task number.");
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
                Task task = list[i];
                //use a switch to determine
                switch (task.getType()) {
                    case TODO:
                        Todo todoTask = (Todo) task;
                        System.out.println(Constants.SPACE + number + ".[T][" + todoTask.getMarkedStatus() + "] " + todoTask.description);
                        break;

                    case DEADLINE:
                        Deadline deadlineTask = (Deadline) task;
                        System.out.println(Constants.SPACE + number + ".[D][" + deadlineTask.getMarkedStatus() + "] " + deadlineTask.description + " (by: " + deadlineTask.by + ")");
                        break;

                    case EVENT:
                        Event eventTask = (Event) task;
                        System.out.println(Constants.SPACE + number + ".[E][" + eventTask.getMarkedStatus() + "] " + eventTask.description + " (from: " + eventTask.start + " to: " + eventTask.end + ")");
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
                if (!list[i].isDone) {
                    count++;
                }
            }
            return count;
        }
    }