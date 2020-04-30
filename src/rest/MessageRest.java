package rest;

import objects.Message;
import objects.MessageManagement;
import objects.User;
import objects.UserManagement;

import javax.ws.rs.*;
import java.util.List;
import java.util.stream.Collectors;

@Path("/user")
public class MessageRest {
    private int getUserId(String firstName, String lastName){
        int res = -1;
        UserManagement userManagement = new UserManagement();
        User user = userManagement.getUsers().stream()
                .filter(user1 -> firstName.equals(user1.getFirstName()) && lastName.equals(user1.getLastName()))
                .findAny()
                .orElse(null);
        if (user != null){
            res = user.getId();
        }
        return res;
    }

    private String messageToString(Message message){
        return "De : " + message.getSender().getFirstName() + ", Message : " +  message.getContent();
    }

    private String messagesToString(List<Message> messages){
        StringBuilder stringBuilder = new StringBuilder();
        for (Message message: messages){
            stringBuilder.append(messageToString(message)).append("<br>");
        }
        return stringBuilder.toString();
    }

    private String  messagesToTBody(List<Message> messages){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<tbody>");
        for (Message message: messages){
            stringBuilder.append("<tr><td>De : ").append(message.getSender().getFirstName()).append("</td><td>Message : ")
                    .append(message.getContent()).append("</td></tr>");
        }
        stringBuilder.append("</tbody>");
        return stringBuilder.toString();
    }

    @GET
    @Produces("text/html")
    public String getUser(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName){
        return getUserId(firstName, lastName) + "";
    }

    // If don't existe -> create
    @POST
    public String postUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName){
        if(getUserId(firstName, lastName) == -1) {
            UserManagement.createUser(firstName, lastName);
        }
        return "user created";
    }

    // became read after been sent
    @GET
    @Path("{userId}/message")
    public String getMessages(@PathParam("userId") int userId){
        List<Message> messages = MessageManagement.getMessages(userId);
        messages.forEach(message -> message.setRead(true));
        return messagesToTBody(messages);
    }

    @POST
    @Path("{userId}/message")
    public String postMessage(@PathParam("userId") int senderId, @FormParam("receiverFirstName") String receiverFirstName,
                              @FormParam("receiverLastName") String receiverLastName,
                              @FormParam("content") String content){
        User sender = UserManagement.getUserById(senderId);
        User receiver = UserManagement.getUserById(getUserId(receiverFirstName, receiverLastName));
        MessageManagement.newMessage(sender, receiver, content);
        return "Message created";
    }

    @DELETE
    @Path("{userId}/message/read")
    public String deleteReadMessages(@PathParam("userId") int userId){
        List<Message> messages = MessageManagement.getMessages(userId).stream().filter(Message::getRead)
                .collect(Collectors.toList());
        for (Message message: messages) {
            MessageManagement.deleteMessage(message.getId());
        }
        return "Read messages deleted";
    }

    // became read after been sent
    @GET
    @Path("{userId}/message/unread")
    public String getUnreadMessages(@PathParam("userId") int userId){
        List<Message> messages = MessageManagement.getMessages(userId).stream().filter(message -> !message.getRead())
                .collect(Collectors.toList());
        messages.forEach(message -> message.setRead(true));
        return messagesToTBody(messages);
    }
}
