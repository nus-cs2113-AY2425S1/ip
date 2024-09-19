package cristiano;

public class Goal {
    protected String description;
    protected boolean isDone;

    public Goal(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone(Ronaldo ronaldoInstance) {
        this.isDone = true;
    }

    public void markAsUndone(Ronaldo ronaldoInstance) {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toFileFormat() {
        return "G | " + (isDone ? "1" : "0") + " | " + description;
    }

    public static Goal fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        String description = parts[2];
        Goal goal = new Goal(description);
        if (parts[1].equals("1")) {
            goal.isDone = true;
        }
        return goal;
    }
}