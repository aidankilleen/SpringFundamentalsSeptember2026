package ie.pt;

public class Message {

    private String title;
    private String text;

    public Message() {
    }

    public Message(String title, String text) {
        this.title = title;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
