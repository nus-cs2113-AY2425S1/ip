public class Todo extends Task{
    Emoji emoji = new Emoji();

    public Todo(String taskName) {
        super(taskName);
    }
    @Override
    public String getTaskTag() {
        return "[T]";
    }

    public String toString() {
        String taskStatus = this.getIsDone() ? emoji.getTickEmoji() : emoji.getHourglassEmoji();
        return String.format("%s %s %s", this.getTaskTag(), this.getTaskName(), taskStatus);
    }
}
