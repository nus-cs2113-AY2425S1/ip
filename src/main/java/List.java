public class List {
    private int numItems;
    private String[] itemList = new String[100];

    public List() {
        numItems = 0;
    }

    public void addItem(String item) {
        itemList[numItems] = item;
        numItems += 1;
    }

    public void printList() {
        for (int i = 0; i < numItems; i++) {
            System.out.println("\t" + (i + 1) + ". " + itemList[i]);
        }
    }
}
