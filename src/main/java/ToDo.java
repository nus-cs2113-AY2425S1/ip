public class ToDo extends Task {

    static String typeIcon = "[T]";

    public ToDo(String newName) {
        super(newName);
    }

    @Override
    public String getTypeIcon() {
        return typeIcon;
    }


    // Irrelevant abstract methods declared in "Task" superclass
    public String getBy() {
        return null;
    }
    public String getEventStart() {
        return null;
    }
    public String getEventEnd() {
        return null;
    }

}
