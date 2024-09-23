import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public void showWelcome(){
        System.out.println("CodyChen Welcomes You");
    }

    public String readCommand(){
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        return line;
    }

    public void showLine(){
        System.out.println("____________________");
    }

    public void listPopulated(TaskList taskList){
        System.out.println("____________________\n" + "Let's review! Your List as follows: ");
        int loop = 1;
        ArrayList<Task> tasks = taskList.getTask();
        for (Task task : tasks) {
            System.out.print(loop + "."); // Prints object Array
            System.out.print("[" + task.getType() + "]" + task.getStatusIcon() + task.getDescription());
            switch (task.getType()) {
            case 'T':
                System.out.println();
                break;
            case 'D':
                System.out.println("(by: " + task.getFrom() + ")");
                break;
            case 'E':
                System.out.println("(by: " + task.getFrom() + task.getTo());
                break;
            }
            loop += 1;
        }
    }

    public void listEmpty(){
        System.out.println("____________________\n" + "Empty. Time to add some tasks! ");
    }

    public void showTodoAdded(TaskList tasks){
        int index = tasks.size() - 1;
        System.out.println("____________________\n" + "Got it. I've added this task: ");
        System.out.println("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription());
    }

    public void showDeadlineAdded(TaskList tasks){
        int index = tasks.size() - 1;
        System.out.println("____________________\n" + "Got it. I've added this task: ");
        System.out.println("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription() +
                "(by: " + tasks.getTask(index).getFrom() + ")");
    }

    public void showEventAdded(TaskList tasks){
        int index = tasks.size() - 1;
        System.out.println("____________________\n" + "Got it. I've added this task: ");
        System.out.println("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription() +
                "(by: " + tasks.getTask(index).getFrom() +
                tasks.getTask(index).getTo() + ")");
    }

    public void showTaskDeleted(TaskList tasks, int index) {
        System.out.println("____________________\n" + "Got it. I've removed this task: \n");
        System.out.print((index + 1) + ".");
        System.out.print("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription());
    }

    public void showTaskMarked(TaskList tasks, int index){
        System.out.println("____________________\n" + "Got it. I've removed this task: \n");
        System.out.print("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription());
    }

    public void showTaskUnmarked(TaskList tasks, int index){
        System.out.println("____________________\n" + "Got it. I've removed this task: \n");
        System.out.print("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription());
    }

}
