package cristiano.goals;

public abstract class Goal {
    protected String description;
    protected boolean isDone;

    public Goal(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Completion status of goal.
     *
     * @return "X" if goal is marked, else a space.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        if (isDone) {
            System.out.println("Goal is already marked!");
            return;
        }
        this.isDone = true;
    }

    public void markAsUndone() {
        if (!isDone) {
            System.out.println("Goal is already unmarked!");
            return;
        }
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Converts goal from the current program's format to a file format
     * Marks task as '1' if marked, '0' if unmarked.
     *
     * @return string of goal in file format
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Converts goal from a file format to the current program's format
     * Helps to determine the goal type in order to convert the goal
     * into its proper format.
     *
     * @param line contains goal in file format
     * @return goal in program format
     * null is returned if an unknown goal type is detected.
     */
    public static Goal fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        String goalType = parts[0];
        return switch (goalType) {
            case "T" -> Todo.fromFileFormat(line);
            case "D" -> Deadline.fromFileFormat(line);
            case "E" -> Event.fromFileFormat(line);
            default -> {
                System.out.println("Unknown goal type!");
                yield null;
            }
        };
    }

}
