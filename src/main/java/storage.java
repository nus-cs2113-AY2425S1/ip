import java.util.ArrayList;
import java.util.List;


public class storage {


    private List<Task> taskList;

    public storage() {

        taskList = new ArrayList<Task>();
    }

    public void storageInsert(Task task) {

        taskList.add(task);
    }

    public void storageDelete(int index) {

        taskList.remove(index - 1 );
    }

    public void storageMark(int index) {

        taskList.get(index - 1).setStatus();
    }

    public void storageUnmark(int index) {

        taskList.get(index - 1).unsetStatus();
    }

    public void storageList() {

        System.out.println("Here is your current list: ");

        int index = 0;
            for (Task task : taskList) {
                System.out.println((index + 1) + "." + task.getStatusIcon() + " " + task);
                index++;
            }
    }

}
