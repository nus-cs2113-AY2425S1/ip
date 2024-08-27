import java.util.ArrayList;

public class List {

    public ArrayList<String> listOfItems;
    public ArrayList<Boolean> isMarkedAsDone;
    public int listLength;

    public List(){
        listOfItems = new ArrayList<>();
        isMarkedAsDone = new ArrayList<>();
        listLength = 0;
    }

    public void addItem(String item){
        listOfItems.add(item);
        isMarkedAsDone.add(false);
        listLength++;
    }

    public void setAsDone(int listNumber){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] " + listOfItems.get(listNumber-1));
        isMarkedAsDone.set(listNumber-1 , true);
    }

    public void setAsNotDone(int listNumber){
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [ ] " + listOfItems.get(listNumber-1));
        isMarkedAsDone.set(listNumber-1 , false);
    }

    public void listItems(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < listLength; i++){
            char markingChar = ' ';
            if(isMarkedAsDone.get(i)){
                markingChar = 'X';
            }
            System.out.printf("%d.[%c] " + listOfItems.get(i) + "\n", i , markingChar);
        }
    }

}
