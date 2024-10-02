import java.util.Scanner;
import UserInteraction.ChatBot;

public class Main {
    public static void main(String[] args) {
        ChatBot edith = new ChatBot("Edith");
        edith.giveIntroduction();
        edith.interactWithUser();
        edith.sayGoodbye();
    }
}
