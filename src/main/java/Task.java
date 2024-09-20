public class Task {
    
    protected String description; 
    protected boolean isDone; 

    public Task (String description) {
        this.description = description; 
        this.isDone = false; 
    }

    public void markDone() {
        this.isDone = true; 
    }

    public void unmarkDone() {
        this.isDone = false; 
    }

    public String isDone() {
        if (isDone) {
            return "[X]"; 
        } else {
            return "[ ]"; 
        }
    }

    @Override 
    public String toString() { 
        return isDone() + " " + description; 
    }
}
