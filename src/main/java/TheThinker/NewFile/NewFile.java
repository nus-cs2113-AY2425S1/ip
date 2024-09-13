package TheThinker.NewFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NewFile {

    public File file;

    public NewFile(String filename) {
        this.file = new File("Data/" + filename);
    }

    public String[] getLines() throws FileNotFoundException {
        Scanner SCANNER = new Scanner(this.file);
        ArrayList<String> listInputs =new ArrayList<>();
        while (SCANNER.hasNext()) {
            listInputs.add(SCANNER.nextLine());
        }

        String [] arrayInputs = new String[listInputs.size()];
        arrayInputs = listInputs.toArray(arrayInputs);
        return arrayInputs;
    }

    public void writeTaskToFile(){

    }

}
