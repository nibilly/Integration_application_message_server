package objects;

public class Message {
    public static int compteurId = 0;
    User sender;
    User receiver;
    String content;
    Boolean isRead;
    int id;

    public Message(User sender, User receiver, String content, Boolean isRead) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.isRead = isRead;
        this.id = compteurId;
        compteurId++;
    }

    public void showMessageDetails(){
        System.out.println(content + " || (id:" + id + ") Ce message a été envoyé par " + sender.getFirstName() + " pour " + receiver.getFirstName() + ". Lu ? " + isRead);
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Message(String content, Boolean isRead, int id) {
        this.content = content;
        this.isRead = isRead;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
