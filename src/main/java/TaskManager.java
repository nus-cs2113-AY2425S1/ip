public class TaskManager {
    Emoji emoji = new Emoji();

    public static Task[] taskList = new Task[100];
    public static int totalTaskCount = 0;

    public TaskManager() {}

    public void displayTaskAction(Task task, int taskNumber, String actionMessage) {
        System.out.println(actionMessage);
        System.out.println(taskNumber + ". " + task);
        System.out.println("--------------------------------------------------------");
    }

    public void displayTaskList() {
        if (totalTaskCount == 0) {
            System.out.println("Nothing in the pipeline yet! Let's get to work!" + " " + emoji.getRocketEmoji() + emoji.getHundredPointsEmoji());
        } else {
            System.out.println("\nHere’s the rundown on the tasks!");
            for (int i = 0; i < totalTaskCount; i++) {
                System.out.println((i + 1) + ". " + taskList[i]);
            }
        }
        System.out.println("--------------------------------------------------------");
    }

    public void addTask(String taskDescription) {
        Task newTask = Task.parseTaskString(taskDescription);

        if (newTask != null) {
            taskList[totalTaskCount] = Task.parseTaskString(taskDescription);
            totalTaskCount++;

            System.out.printf("\nYou have %d task(s) in your list now!\n%n", totalTaskCount);
            String actionMessage = "Let's make it happen! " + emoji.getRockstarHandEmoji() + emoji.getFireEmoji();
            displayTaskAction(taskList[totalTaskCount - 1], totalTaskCount, actionMessage);

        }
    }

    public void completeTask(int taskNumber) {
        if (totalTaskCount == 0) {
            System.out.println("Nothing in the pipeline yet! Let's get to work!" + " " + emoji.getRocketEmoji() + emoji.getHundredPointsEmoji());
        } else {
            try {
                Task task = taskList[taskNumber - 1];
                task.setIsDone(true);

                String actionMessage = "\nGood Job! " + emoji.getPartyPopperEmoji();
                displayTaskAction(task, taskNumber, actionMessage);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                System.out.println("ERROR: Task number out of range. Please enter a valid task number" + emoji.getExclamationMarkEmoji());
            }
        }
    }

    public void undoTask(int taskNumber) {
        if (totalTaskCount == 0) {
            System.out.println("Nothing in the pipeline yet! Let's get to work!" + " " + emoji.getRocketEmoji() + emoji.getHundredPointsEmoji());
        } else {
            try {
                Task task = taskList[taskNumber - 1];
                task.setIsDone(false);

                String actionMessage = "\nNo worries! Task reset. " + emoji.getReverseEmoji();
                displayTaskAction(task, taskNumber, actionMessage);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                System.out.println("ERROR: Task number out of range. Please enter a valid task number" + emoji.getExclamationMarkEmoji());
            }
        }
    }
}
