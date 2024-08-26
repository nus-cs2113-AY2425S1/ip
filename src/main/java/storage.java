import java.util.ArrayList;
import java.util.List;


public class storage {

    private List<String> lineInputs;

    public storage() {

        lineInputs = new ArrayList<String>();
    }

    public void storageInsert(String input) {

        lineInputs.add("[ ] " + input);
    }

    public void storageDelete(int index) {

        lineInputs.remove(index - 1);
    }

    public void storageMark(int index) {

        lineInputs.set(index - 1, lineInputs.get(index - 1).replace("[ ] ", "[X] "));
    }

    public void storageUnmark(int index) {

        lineInputs.set(index - 1, lineInputs.get(index - 1).replace("[X] ", "[ ] "));
    }

    public void storageList() {

        System.out.println("Here is your current list: ");

        int index = 0;
            for (String s : lineInputs) {
                System.out.println((index + 1)+ "." + s);
                index++;
            }
    }

}
