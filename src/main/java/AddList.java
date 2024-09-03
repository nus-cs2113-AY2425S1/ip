public class AddList {
    private final String[] entries = new String[100]; 
    private final boolean[] done = new boolean[100];   
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
                String completion; 
                if (done[i] == true) {
                    completion = "[X] "; 
                }
                else {
                    completion = "[ ] "; 
                }
                System.out.println("\t" + String.valueOf(i+1) + ". " + completion + entries[i]); 
            }
        }
    }

    public void markAsDone(int i) {
        if (i >= 1 && i <= entries.length) {
            done[i-1] = true; 
            System.out.println(SEPARATOR); 
            System.out.println("\t Nice! I've marked this task as done: "); 
            System.out.println("\t \t [X]" + entries[i-1]); 
            System.out.println(SEPARATOR); 
        }
    }

    public void unmarkAsDone(int i) {
        if (i >= 1 && i <= entries.length) {
            done[i - 1] = false;
            System.out.println(SEPARATOR); 
            System.out.println("\t OK, I've marked this task as not done yet: "); 
            System.out.println("\t \t [ ]" + entries[i-1]); 
            System.out.println(SEPARATOR); 
        }
    }
}
