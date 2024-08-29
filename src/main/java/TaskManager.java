public class TaskManager {
    private int totalTaskCount;
    private Task[] taskList;
    Emoji emoji = new Emoji();

    public TaskManager() {
        totalTaskCount = 0;
        taskList = new Task[100];
    }

    public void addTask(String taskName) {
        taskList[totalTaskCount] = new Task(taskName, false);
        totalTaskCount++;

        System.out.println("Let's make it happen! " + emoji.getRockstarHandEmoji() + emoji.getFireEmoji());
        System.out.println("Task added: " + taskName);
        System.out.println("--------------------------------------------------------");
    }

    public void displayTaskList() {
        if (totalTaskCount == 0) {
            System.out.println("Nothing in the pipeline yet! Let's get to work!" + " " + emoji.getRocketEmoji() + emoji.getHundredPointsEmoji());
        } else {
            System.out.println("Here’s the rundown on the tasks!");
            for (int i = 0; i < totalTaskCount; i++) {
                String taskStatus = taskList[i].getIsDone() ? emoji.getTickEmoji() : emoji.getHourglassEmoji();
                System.out.println((i + 1) + ". " + taskList[i].getTaskName() + " " + taskStatus);
            }
        }
        System.out.println("--------------------------------------------------------");
    }

    public void displayTaskStatus(Task task, int taskNumber) {
        String taskStatus = task.getIsDone() ? emoji.getTickEmoji() : emoji.getHourglassEmoji();
        System.out.println((taskNumber) + ". " + task.getTaskName() + " " + taskStatus);
        System.out.println("--------------------------------------------------------");
    }

    public void completeTask(int taskNumber) {
        if (totalTaskCount == 0) {
            System.out.println("Nothing in the pipeline yet! Let's get to work!");
        }
        else if (taskNumber <= 0 || taskNumber > totalTaskCount) {
            System.out.println("Invalid task number!");
        }
        else {
            Task task = taskList[taskNumber - 1];
            task.setIsDone(true);
            System.out.println("Good Job! " + emoji.getPartyPopperEmoji() + "\n");
            displayTaskStatus(task, taskNumber);
        }
    }

    public void undoTask(int taskNumber) {
        if (totalTaskCount == 0) {
            System.out.println("Nothing in the pipeline yet! Let's get to work!");
        }
        else if (taskNumber <= 0 || taskNumber > totalTaskCount) {
            System.out.println("Invalid task number!");
        }
        else {
            Task task = taskList[taskNumber - 1];
            task.setIsDone(false);
            System.out.println("No worries! Task reset. " + emoji.getReverseEmoji() + "\n");
            displayTaskStatus(task, taskNumber);
        }
    }
}
