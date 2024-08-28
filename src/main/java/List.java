public class List {
    private int numItems;
    private Task[] itemList = new Task[100];

    public List() {
        numItems = 0;
    }

    public int getNumItems() {
        return numItems;
    }

    public void addItem(String item) {
        itemList[numItems] = new Task(item);
        numItems += 1;
    }

    public void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < numItems; i++) {
            System.out.println("\t" + (i + 1) + ".[" + itemList[i].getStatusIcon() + "] " + itemList[i].description);
        }
    }

    public void markItem(String line) {
        int itemNum = Integer.parseInt(line.substring(5));
        if (itemNum > this.getNumItems() || itemNum <= 0) {
            System.out.println("Input item number out of range.");
        } else {
            this.markListItemAsDone(itemNum);
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t[" + this.itemGetStatusIcon(itemNum) + "] " + this.getItemDescription(itemNum));
        }
    }

    public void unmarkItem(String line) {
        int itemNum = Integer.parseInt(line.substring(7));
        if (itemNum > this.getNumItems() || itemNum <= 0) {
            System.out.println("Input item number out of range.");
        } else {
            this.markListItemAsUnDone(itemNum);
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t\t[" + this.itemGetStatusIcon(itemNum) + "] " + this.getItemDescription(itemNum));
        }
    }

    public String getItemDescription(int itemNum) {
        return itemList[itemNum - 1].description;
    }

    public void markListItemAsDone(int itemNum) {
        itemList[itemNum - 1].markAsDone();
    }

    public void markListItemAsUnDone(int itemNum) {
        itemList[itemNum - 1].markAsUnDone();
    }

    public String itemGetStatusIcon(int itemNum) {
        return itemList[itemNum - 1].getStatusIcon();
    }
}
