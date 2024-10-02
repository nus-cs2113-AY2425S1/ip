package TaskTypes;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description, TypeOfTask.ToDos);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public char getTaskCharacter() {
        return 'T';
    }

    public String getStorableString() {
        int isDoneInteger = getIsDone() ? 1 : 0;
        return getTaskCharacter() + " | " + isDoneInteger + " | " + getDescription();
    }
}
