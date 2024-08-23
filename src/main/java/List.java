public class List {
    private final String[] list;
    private int size;

    public List(int capacity) {
        list = new String[capacity];
        size = 0;
    }

    public void printList() {
        Print.line();
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
        Print.line();
    }

    public void addItem(String userInput) {
        Print.line();
        list[size++] = userInput;
        System.out.println("added: " + userInput);
        Print.line();
    }
}
