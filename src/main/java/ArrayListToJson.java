import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayListToJson {
    public static void main(String[] args) {
        // Create an ArrayList
        ArrayList<String> list = new ArrayList<>();
        list.add("item1");
        list.add("item2");
        list.add("item3");

        // Create a Gson instance
        Gson gson = new Gson();

        // Serialize the ArrayList to JSON
        String json = gson.toJson(list);

        // Print the JSON string (optional)
        System.out.println(json);

        // Store the JSON locally in a file
        try (FileWriter writer = new FileWriter("list.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
