package br.furb.eventos.ws;

import br.furb.eventos.dto.EventDto;
import br.furb.eventos.dto.NewCommentDto;
import br.furb.eventos.dto.NewEventDto;
import br.furb.eventos.dto.UserDto;
import br.furb.eventos.entity.Comment;
import br.furb.eventos.entity.CommentDAO;
import br.furb.eventos.entity.Event;
import br.furb.eventos.entity.EventDAO;
import br.furb.eventos.entity.User;
import br.furb.eventos.entity.UserDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Alexandre
 */
@Path("event")
public class EventResource extends BaseWs {

    private final EventDAO dao = EventDAO.getInstance();

    public EventResource() {
    }

    @GET
    @Produces("application/json")
    @Path("{id:[0-9]+}")
    public Response getEvent(@PathParam("id") long id) {

        Event ev = dao.getById(id);

        if (ev == null) {
            return notFound();
        }

        User owner = ev.getOwner();

        EventDto e = new EventDto();
        UserDto u = new UserDto();
        u.setId(owner.getId());
        u.setFullName(owner.getName() + " " + owner.getLastname());
        u.setName(owner.getName());
        u.setSurName(owner.getLastname());
        u.setLogin(owner.getLogin());
        u.setPhotoUrl(owner.getPhotoUrl());

        e.setId(id);
        e.setOwner(u);
        e.setCoverUrl(ev.getCoverImage());
        e.setTitle(ev.getName());
        e.setDate(ev.getInitialdate() + " - " + ev.getFinaldate());
        e.setInitialDate(ev.getInitialdate().toString());
        e.setFinalDate(ev.getFinaldate().toString());
        e.setLocation(ev.getAddress());
        e.setDetail(ev.getDescription());
        //e.setGuests();
        //e.setTotalGuests();
        e.setLike(true);
        //e.setLikes(id);

        return ok(e);
    }
    
    @GET
    @Produces("application/json")
    @Path("user/{idUser:[0-9]+}")
    public Response getEventsUser(@PathParam("idUser") long id) {

        ArrayList<EventDto> ev = new ArrayList<>();

        List<Event> evs = dao.getEventsUser(id);

        for (Event event : evs) {

            EventDto e = new EventDto();
            UserDto u = new UserDto();

            User usr = event.getOwner();

            u.setId(usr.getId());
            u.setFullName(usr.getName() + " " + usr.getLastname());
            u.setName(usr.getName());
            u.setSurName(usr.getLastname());
            u.setLogin(usr.getLogin());
            u.setPhotoUrl(usr.getPhotoUrl());

            e.setId(event.getId());
            e.setOwner(u);
            e.setCoverUrl(event.getCoverImage());
            e.setTitle(event.getName());
            e.setDate(event.getInitialdate() + " - " + event.getFinaldate());
            e.setInitialDate(event.getInitialdate().toString());
            e.setFinalDate(event.getFinaldate().toString());
            e.setLocation(event.getAddress());
            e.setDetail(event.getDescription());

            ev.add(e);
        }

        return ok(ev);
    }
    
    @GET
    @Produces("application/json")
    @Path("user/{idUser:[0-9]+}/othersEvents")
    public Response getOthersEvents(@PathParam("idUser") long id) {

        ArrayList<EventDto> ev = new ArrayList<>();

        List<Event> evs = dao.getOthersEvents(id);

        for (Event event : evs) {

            EventDto e = new EventDto();
            UserDto u = new UserDto();

            User usr = event.getOwner();

            u.setId(usr.getId());
            u.setFullName(usr.getName() + " " + usr.getLastname());
            u.setName(usr.getName());
            u.setSurName(usr.getLastname());
            u.setLogin(usr.getLogin());
            u.setPhotoUrl(usr.getPhotoUrl());

            e.setId(event.getId());
            e.setOwner(u);
            e.setCoverUrl(event.getCoverImage());
            e.setTitle(event.getName());
            e.setDate(event.getInitialdate() + " - " + event.getFinaldate());
            e.setInitialDate(event.getInitialdate().toString());
            e.setFinalDate(event.getFinaldate().toString());
            e.setLocation(event.getAddress());
            e.setDetail(event.getDescription());

            ev.add(e);
        }

        return ok(ev);
    }

    @GET
    @Produces(JSON)
    public Response getAll() {

        ArrayList<EventDto> ev = new ArrayList<>();

        List<Event> evs = dao.getAllEvents();

        for (Event event : evs) {

            EventDto e = new EventDto();
            UserDto u = new UserDto();

            User usr = event.getOwner();

            u.setId(usr.getId());
            u.setFullName(usr.getName() + " " + usr.getLastname());
            u.setName(usr.getName());
            u.setSurName(usr.getLastname());
            u.setLogin(usr.getLogin());
            u.setPhotoUrl(usr.getPhotoUrl());

            e.setId(event.getId());
            e.setOwner(u);
            e.setCoverUrl(event.getCoverImage());
            e.setTitle(event.getName());
            e.setDate(event.getInitialdate() + " - " + event.getFinaldate());
            e.setInitialDate(event.getInitialdate().toString());
            e.setFinalDate(event.getFinaldate().toString());
            e.setLocation(event.getAddress());
            e.setDetail(event.getDescription());

            ev.add(e);
        }

        return ok(ev);
    }

    @POST
    @Produces(JSON)
    @Consumes(JSON)
    public Response addEvent(NewEventDto c) throws ParseException {
        
        Event e = new Event();
        UserDAO userDAO = UserDAO.getInstance();

        e.setOwner(userDAO.getById(c.getOwner()));
        e.setName(c.getTitle());
        e.setDescription(c.getDetail());
        e.setInitialdate(new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(c.getInitialdate()));
        e.setFinaldate(new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(c.getFinaldate()));
        e.setAddress(c.getLocation());

        dao.save(e);

        return created(e.getId());
    }

    @PUT
    @Produces(JSON)
    @Consumes(JSON)
    @Path("{id:[0-9]+}")
    public Response editEvent(@PathParam("id") long id, NewEventDto c) throws ParseException {
        
        Event e = dao.getById(id);

        if (e == null) {
            return notFound();
        }
        
        UserDAO userDAO = UserDAO.getInstance();

        e.setOwner(userDAO.getById(c.getOwner()));
        e.setName(c.getTitle());
        e.setDescription(c.getDetail());
        e.setInitialdate(new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(c.getInitialdate()));
        e.setFinaldate(new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(c.getFinaldate()));
        e.setAddress(c.getLocation());

        dao.save(e);

        return noContent();
    }
    
    @PUT
    @Produces(JSON)
    @Consumes(JSON)
    @Path("{id:[0-9]+}/comment")
    public Response addComment(@PathParam("id") long id, NewCommentDto c) {
        
        Event e = dao.getById(id);

        if (e == null) {
            return notFound();
        }
        
        Comment comment = new Comment();
        UserDAO userDAO = UserDAO.getInstance();        
        
        comment.setUser(userDAO.getById(c.getUser()));
        comment.setComment(c.getComment());
        
        List<Comment> cm = e.getComments();
        cm.add(comment);
        
        e.setComments(cm);              
        
        dao.save(e);

        return created(comment.getId());
    }
}
