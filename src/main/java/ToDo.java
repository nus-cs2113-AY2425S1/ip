public class ToDo extends Task {

    static String typeIcon = "[T]";

    public ToDo(String newName) {
        super(newName);
    }

    @Override
    public String getTypeIcon() {
        return typeIcon;
    }
}
