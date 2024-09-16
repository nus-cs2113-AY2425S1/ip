import java.io.IOException;

public class Diana {
    public static void main(String[] args) {
        DianaAssistant dianaAssistant = null;
        try {
            dianaAssistant = new DianaAssistant();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (dianaAssistant != null) {
            dianaAssistant.interact();
        }
    }
}
