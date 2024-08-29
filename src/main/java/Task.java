public class Task {
    private String description;
    private boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void mark(){
        this.isMarked = true;
    }

    public String toString(){
        return(isMarked?"[X] ":"[ ] ") + description;
    }
}
