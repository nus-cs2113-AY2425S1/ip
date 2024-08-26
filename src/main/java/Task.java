public class Task {
    private String name;
    private boolean status;

    public Task(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    public String getName() {
        return name;
    }

    public boolean getStatus() {
        return status;
    }

    @Override
    public String toString() {
        if (status == false) {
            return "[ ] " + name;
        }
        else {
            return "[X] " + name;
        }
    }
}
