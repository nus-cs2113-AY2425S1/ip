public class AddList {
    private final String[] entries = new String[100]; 
    private static final String SEPARATOR = "\t ___________________________"; 
    private int index = 0; 

    public void addEntry(String entry) {
        if (index < entries.length) {
            entries[index] = entry; 
            index++; 
            System.out.println(SEPARATOR); 
            System.out.println("\t added: " + entry); 
            System.out.println(SEPARATOR); 

        }
        else {
            System.out.println("\t Cannot add entry"); 
        }

    }

    public void displayEntries() {
        System.out.println(SEPARATOR); 
        if (index == 0) {
            System.out.println("\t No entries available. "); 
        } else {
            for (int i = 0; i < index; i++) {
                System.out.println("\t" + String.valueOf(i+1) + ". " + entries[i]); 
            }
        }
    }
}
