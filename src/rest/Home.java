package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class Home {
    @GET
    @Produces("text/html")
    public String getHomeMessage() {
        return "Le serveur MessageServer fonctionne !";
    }
}