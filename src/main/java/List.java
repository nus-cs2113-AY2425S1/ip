import java.util.ArrayList;

public class List {

    public ArrayList<String> listOfItems;
    public int listLength;

    public List(){
        listOfItems = new ArrayList<>();
        listLength = 0;
    }

    public void addItem(String item){
        listOfItems.add(item);
        listLength++;
    }

    public void removeItem(String item){
        listOfItems.remove(item);
        listLength--;
    }

    public void listItems(){
        for(int i = 0; i < listLength; i++){
            System.out.printf("%d. " + listOfItems.get(i) + "\n", i);
        }
    }

}
