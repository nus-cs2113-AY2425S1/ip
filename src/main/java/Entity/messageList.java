package Entity;

import java.util.ArrayList;
import java.util.List;

public class messageList {
    private static List<Message> messages;

    public messageList() {
        messages = new ArrayList<Message>();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public static void add(Message message) {
        messages.add(message);
    }
}
