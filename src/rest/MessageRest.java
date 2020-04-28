package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/user")
public class MessageRest {

    @GET
    public String getUserId(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName){
        return "Not implemented";
    }

    // If don't existe -> create
    @POST
    public String postUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName){
        return "Not implemented";
    }

    @GET
    @Path("{userId}/message")
    public String getMessages(@PathParam("userId") int userId){
        return "Not implemented";
    }

    @POST
    @Path("{userId}/message")
    public String postMessage(@PathParam("userId") int senderId, @FormParam("receiverId") int receiverId,
                              @FormParam("content") String content){
        return "Not implemented";
    }

    @DELETE
    @Path("{userId}/message/read")
    public String deleteReadMessages(@PathParam("userId") int userId){
        return "Not implemented";
    }

    // became read after been sent
    @GET
    @Path("{userId}/message/unread")
    public String getUnreadMessages(@PathParam("userId") int userId){
        return "Not implemented";
    }
}
