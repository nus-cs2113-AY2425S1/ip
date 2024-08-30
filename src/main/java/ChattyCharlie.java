import java.util.Scanner;

public class ChattyCharlie {

    //TASK CLASS
    public static class Task{
        protected String description;
        protected boolean isDone;

        //constructor
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        //check if its marked as done
        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        //to toggle the task
        public void markTask() {
            this.isDone = true; //change the variable
        }

        public void unmarkTask() {
            this.isDone = false; //change the variable
        }
    }


    //LIST CLASS
    public static class List {
        //make a list of task
        private Task[] list;
        private int size;

        //constructor
        public List() {
            list = new Task[100];
            size = 0;
        }

        //Method to add an item to the list
        public void addTask(String text) {
            //create an instance for Task
            Task newTask = new Task(text);
            //add the text into the list
            list[size] = newTask;
            //account for the item
            size++;
        }

        //To mark
        public void mark(int index) {
            if (index >= 0 && index < size) {
                list[index].markTask();
                int remainingTask = countUnmarkedTasks();
                System.out.println("        Well Done! 1 task down, " + remainingTask + " to go.");
                System.out.println("        [" + list[index].getStatusIcon() + "] " + list[index].description);
            } else {
                System.out.println("        Invalid task number.");
            }
        }

        //To unmark
        public void unmark(int index) {
            if (index >= 0 && index < size) {
                list[index].unmarkTask();
                int remainingTask = countUnmarkedTasks();
                System.out.println("        Hmmm, not quite done yet, " + remainingTask + " to go.");
                System.out.println("        [" + list[index].getStatusIcon() + "] " + list[index].description);
            } else {
                System.out.println("        Invalid task number.");
            }
        }

        //To print list
        public void toPrintList() {
            //print all
            int remainingTask = countUnmarkedTasks();
            System.out.println("ToDo List:");
            System.out.println("pending Task: " + remainingTask);
            for (int i = 0; i < size; i++) {
                int number = i+1;
                System.out.println("        " + number + ".[" +list[i].getStatusIcon() + "] " + list[i].description);
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

    //MAIN ALGO
    public static void toDoMaker() { //Echo as a function
        String line;
        String you = "User: ";

        //make the scanner
        Scanner in = new Scanner(System.in);

        //create an instance of list class
        List toDo = new List();

        //accept an insert
        while (true) {
            System.out.print(you);
            line = in.nextLine();

            //if the line contains bye, it signals the end
            if (line.contains("Bye") || line.contains("bye")) {
                break;
            }
            //add or print
            if (line.equals("list")) {
                toDo.toPrintList();
            } else if (line.startsWith("mark ")) {
                //trim to get the task name
                String taskDescription = line.substring(5).trim();
                boolean found = false;

                //loop to find
                for(int index = 0; index < toDo.size; index++)
                {
                    if(toDo.list[index].description.equals(taskDescription)) {
                        toDo.mark(index);
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    System.out.println("        Invalid task description");
                }
            } else if (line.startsWith("unmark ")) {
                String taskDescription = line.substring(7).trim();
                boolean found = false;

                //loop to find
                for(int index = 0; index < toDo.size; index++)
                {
                    if(toDo.list[index].description.equals(taskDescription)) {
                        toDo.unmark(index);
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    System.out.println("        Invalid task description.");
                }
            } else {
                //add the item
                toDo.addTask(line);
                System.out.println("        Added: " + line);
            }
        }
    }

    public static void main(String[] args) {
        String logo = "   _____      \n"
                + "  /     \\     \n"
                + " |  O O  |    \n"
                + " | \\___/ |    \n"
                + "  \\_____/     \n"
                + " /\\_____/\\    \n"
                + " |       |    \n"
                + " |       |    \n"
                + " |_______|    \n"
                + "              \n";
        String charlie = "Charlie: ";

        String greeting = "Hello! I'm ChattyCharlie, your consistent buddy.\n"
                + "         What shall we do today?\n" ;

        String farewell = "All the best in clearing your list!";

        System.out.println(logo + charlie+ greeting);
        toDoMaker();
        System.out.println(charlie + farewell);

    }
}
