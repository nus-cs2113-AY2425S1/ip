package cristiano;

public class Goal {
    protected String description;
    protected boolean isDone;

    public Goal(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " "; // mark done task with X
    }

    public void markAsDone(Ronaldo ronaldoInstance) {
        if (isDone()) {
            ronaldoInstance.reject("Marked");
            return;
        }
        this.isDone = true;
        System.out.println("SIUUU! Congrats, one step closer to achieving your dreams! This goal is now achieved:\n" +
                this + "\n");
    }

    public void markAsUndone(Ronaldo ronaldoInstance) {
        if (!isDone()) {
            ronaldoInstance.reject("Unmarked");
            return;
        }
        this.isDone = false;
        System.out.println("Ronaldo is disappointed in you. Work harder! This goal is now yet to achieve:\n" +
                this + "\n");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}