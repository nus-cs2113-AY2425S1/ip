package aether.task;

/**
 * Abstract base class for all types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatus() {
        return (isDone ? "1" : "0"); // 1 means done, 0 means not done
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }

    public abstract String toDataString();

    public static Task fromStorage(String data) {
        // Logic to parse the task from a stored string representation
        String[] parts = data.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            task = new Event(description, parts[3], parts[4]);
            break;
        }
        if (task != null) {
            task.setDone(isDone);
        }
        return task;
    }
}
