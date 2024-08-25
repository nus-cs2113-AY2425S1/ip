public class ListItem {
    private String text;
    private boolean isCompleted;
    private int index;
    public static int currentIndex = 0;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Prints out command and the following 'mark' action that has been completed on it.
     */
    public void setCompleted(boolean completed) {
        isCompleted = completed;
        if (isCompleted) {
            System.out.println("Mong >_<!!I have marked it as completed:");
            System.out.println("[X] " + text);
        } else {
            System.out.println("Mong-mong :<! The task has been unmarked:");
            System.out.println("[ ] " + text);
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex() {
        this.index = currentIndex;
        currentIndex++;
    }

    public ListItem(String text) {
        setText(text);
        // default value for isCompleted is false
        this.isCompleted = false;
        setIndex();
    }
}
