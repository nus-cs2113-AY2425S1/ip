public class UI {


    private TaskList taskListInstance;


    public UI(TaskList taskListInstance) {
        this.taskListInstance = taskListInstance;
    }



    public void Warning(String message) throws DukeException {
        throw new DukeException(message);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void addTaskMessage(int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskListInstance.getTask(taskCount-1).toString());
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }

    public void deleteTaskMessage(int taskCount, String deleted) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deleted);
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }


    public void markTaskMessage(int taskIndex) {
        System.out.println("Noted. I've marked this task:");
        System.out.print(taskIndex + ". ");
        System.out.println(taskListInstance.getTask(taskIndex-1).toString());

    }

    public void unmarkTaskMessage(int taskIndex) {
        System.out.println("Noted. I've unmarked this task:");
        System.out.print(taskIndex + ". ");
        System.out.println(taskListInstance.getTask(taskIndex-1).toString());
    }

    public void displayTask(int index) {
        System.out.print((index + 1) + ". ");
        System.out.println(taskListInstance.getTask(index).toString());
    }


}
