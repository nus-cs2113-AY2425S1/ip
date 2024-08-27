import java.util.ArrayList;
import java.util.stream.IntStream;

public class StringList {

    private ArrayList<String> list = new ArrayList<String>();
    private int size;

    StringList() {
        this.size = 0;
    }

    StringList(ArrayList<String> input) {
        this.list = input;
        this.size = input.size();
    }

    public void printList() {
        IntStream.range(0, list.size())
                .forEach(x -> System.out.println((x + 1) + ". " + list.get(x)));
    }

    public void addItem(String item) {
        this.list.add(item);
        this.size++;
    }
}