public abstract class Task {
    
    protected String description; 
    protected boolean isDone; 

    public Task (String description) {
        this.description = description; 
        this.isDone = false; 
    }

    public Task (String description, boolean isDone) {
        this.description = description; 
        this.isDone = isDone; 
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

    public String getDescription() {
        return description; 
    }

    @Override 
    public String toString() { 
        return isDone() + " " + description; 
    }

    public abstract String writeFile(); 
}
