import java.util.ArrayList;
import java.util.List;


public class storage {

    private List<String> inputs;

    public storage() {
        inputs = new ArrayList<String>();
    }

    public void storageInsert(String input) {
        inputs.add("[ ] " + input);
    }

    public void storageDelete(int index) {
        inputs.remove(index - 1);
    }

    public void storageMark(int index) {
        inputs.set(index - 1, inputs.get(index - 1).replace("[ ] ", "[X] "));
    }

    public void storageUnmark(int index) {
        inputs.set(index - 1, inputs.get(index - 1).replace("[X] ", "[ ] "));
    }

    public void storageList() {
        System.out.println("Here is your current list: ");
        int index = 0;
            for (String s : inputs) {
                System.out.println((index + 1)+ "." + s);
                index++;
            }
    }

}
