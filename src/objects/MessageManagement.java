package objects;

import java.util.ArrayList;
import java.util.List;

public class MessageManagement {
    public static List<Message> allMessage = new ArrayList<Message>();

    public static List<Message> getMessages(int userId){
        List<Message> userMessage = new ArrayList<>();
        for(int i = 0; i < allMessage.size(); i++){
            if(allMessage.get(i).getReceiver().getId() == userId){
                userMessage.add(allMessage.get(i));
            }
        }
        return userMessage;
    }

    public static List<Message> getUnreadMessages(int userId){
        List<Message> userMessage = new ArrayList<>();

        for(int i = 0; i < allMessage.size(); i++){
            if((allMessage.get(i).getReceiver().getId()== userId) && (!allMessage.get(i).getRead())){
                userMessage.add(allMessage.get(i));
            }
        }
        return userMessage;
    }

    public static void showMessages(){
        for (int i = 0; i < allMessage.size(); i++){
            Message monMessage = allMessage.get(i);
            monMessage.showMessageDetails();
        }
    }

    public static void read(int messageId){
        for(int i = 0; i < allMessage.size(); i++){
            if (allMessage.get(i).getId() == messageId){
                allMessage.get(i).setRead(true);
            }
        }
    }


    public static void newMessage(User sender, User receiver, String content){
        Message message = new Message(sender, receiver, content, false);
        allMessage.add(message);
    }

    public static void deleteMessage(int messageId){
        for(int i = 0; i < allMessage.size(); i++){
            if (allMessage.get(i).getId() == messageId){
                allMessage.remove(i);
            }
        }
    }
}
