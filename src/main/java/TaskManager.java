public class Task {
    Emoji emoji = new Emoji();

    public static Task[] taskList = new Task[100];
    public static int totalTaskCount = 0;

    private String taskName;
    private Boolean isDone;

    public Task() {};

    public Task(String taskName) {
        setTaskName(taskName);
        isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public String getTaskTag() {
        return "";
    }

    public Task parseTaskString(String taskName) {
        if (taskName.indexOf("BY") > 0) {
            String by = taskName.substring(taskName.indexOf("BY") + 2);
            taskName = taskName.substring(0, taskName.indexOf("BY"));
            return new Deadline(taskName, by);
        }
        else if (taskName.indexOf("FROM") > 0) {
            String from = taskName.substring(taskName.indexOf("FROM") + 4);
            String to = taskName.substring(taskName.indexOf("TO") + 2);
            taskName = taskName.substring(0, taskName.indexOf("FROM"));
            return new Event(taskName, from, to);
        }
        else {
            return new Todo(taskName);
        }
        totalTaskCount++;

//        int taskNumber = totalTaskCount - 1;
//        String actionMessage = "Let's make it happen! " + emoji.getRockstarHandEmoji() + emoji.getFireEmoji();

        System.out.println("Let's make it happen! " + emoji.getRockstarHandEmoji() + emoji.getFireEmoji());
        System.out.println("Task added: " +  taskList[totalTaskCount-1]);
    }

    public void displayTaskAction(Task task, int taskNumber, String actionMessage) {
        System.out.println(actionMessage);
        System.out.println(taskNumber + ". " + task);
        System.out.println("--------------------------------------------------------");
    }

    public void displayTaskList() {
        if (totalTaskCount == 0) {
            System.out.println("Nothing in the pipeline yet! Let's get to work!" + " " + emoji.getRocketEmoji() + emoji.getHundredPointsEmoji());
        } else {
            System.out.println("Here’s the rundown on the tasks!");
            for (int i = 0; i < totalTaskCount; i++) {
                System.out.println((i + 1) + ". " + taskList[i]);
            }
        }
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
            System.out.println(taskNumber + ". " + task);
            System.out.println("--------------------------------------------------------");
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
            System.out.println(taskNumber + ". " + task);
            System.out.println("--------------------------------------------------------");
        }
    }
}
