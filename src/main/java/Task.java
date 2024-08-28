public class Task {
    private int taskCount;
    private String[] taskList;
    Emoji emoji = new Emoji();

    public Task() {
        taskCount = 0;
        taskList = new String[100];
    }

    public void addTask(String task) {
        taskList[taskCount] = task;
        taskCount++;

        System.out.println("Let's make it happen!");
        System.out.println("Task added: " + task + " " + emoji.getRockstarHandEmoji() + emoji.getFireEmoji());
        System.out.println("---------------------------------------------------------------------------");
    }

    public void displayTaskList() {
        if (taskCount == 0) {
            System.out.println("Nothing in the pipeline yet! Let's get to work!" + " " + emoji.getRocketEmoji() + emoji.getHundredPointsEmoji());
        } else {
            System.out.println("Here’s the rundown on the tasks!");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + taskList[i] + " " + emoji.getMemoEmoji());
            }
        }
        System.out.println("--------------------------------------------------------");
    }
}
