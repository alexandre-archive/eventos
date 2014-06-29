/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.furb.eventos.ws;

import br.furb.eventos.dto.NewEventDto;
import br.furb.eventos.entity.Comment;
import br.furb.eventos.entity.CommentDAO;
import br.furb.eventos.entity.Event;
import br.furb.eventos.entity.EventDAO;
import br.furb.eventos.entity.Permission;
import br.furb.eventos.entity.PermissionDAO;
import br.furb.eventos.entity.Profile;
import br.furb.eventos.entity.ProfileDAO;
import br.furb.eventos.entity.User;
import br.furb.eventos.entity.UserDAO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Alexandre
 */
@Path("events")
public class EventsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EventsResource
     */
    public EventsResource() {
    }

    /**
     * Retrieves representation of an instance of br.furb.eventos.ws.EventsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        //User u = new User("Teste", "teste@hehe.com", "ln", "teste", "pwd");
        //UserDAO dao = UserDAO.getInstance();
        //dao.salvar(u);
        //return "User salvo...";
        //Profile p = new Profile("nome do profile");
        //ProfileDAO dao = ProfileDAO.getInstance();
        //dao.salvar(p);
        //return "Profile salvo...";
        //Permission p = new Permission("nome do permission");
        //PermissionDAO dao = PermissionDAO.getInstance();
        //dao.salvar(p);
        //return "Permission salvo...";
        //Event e = new Event("Game", "Metal Gear Solid Version");
        //EventDAO dao = EventDAO.getInstance();
        //dao.salvar(e);
        //return "Event salvo...";
        Comment c = new Comment("HAUahuAhuaHU");
        CommentDAO dao = CommentDAO.getInstance();
        dao.salvar(c);
        return "Comment salvo...";
    }

    /**
     * PUT method for updating or creating an instance of EventsResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes("application/json")
    public void addEvent(String content) {
        System.out.print(content);
    }
}
