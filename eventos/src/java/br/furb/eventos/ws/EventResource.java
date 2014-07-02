package br.furb.eventos.ws;

import br.furb.eventos.dao.EventDAO;
import br.furb.eventos.dao.UserDAO;
import br.furb.eventos.dto.CommentDto;
import br.furb.eventos.dto.EventDto;
import br.furb.eventos.dto.NewCommentDto;
import br.furb.eventos.dto.NewEventDto;
import br.furb.eventos.dto.UserDto;
import br.furb.eventos.entity.Comment;
import br.furb.eventos.entity.Event;
import br.furb.eventos.entity.Guest;
import br.furb.eventos.entity.Guest.UserStatus;
import br.furb.eventos.entity.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Produces(JSON)
    @Path("{id:[0-9]+}")
    public Response getEvent(@PathParam("id") long id) {

        Event ev = dao.getById(id);

        if (ev == null) {
            return notFound();
        }

        ArrayList<CommentDto> cmDto = new ArrayList<>();
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
        e.setDate(fmt.format(ev.getInitialdate()) + " - " + fmt.format(ev.getFinaldate()));
        e.setInitialDate(ev.getInitialdate().toString());
        e.setFinalDate(ev.getFinaldate().toString());
        e.setLocation(ev.getAddress());
        e.setDetail(ev.getDescription());

        String s = "";

        List<Guest> g = ev.getGuests();

        if (g != null) {

            int count = 0;

            for (Guest entry : g) {
                User user = entry.getGuest();
                UserStatus userStatus = entry.getStatus();

                if (userStatus == UserStatus.YES) {
                    s += user.getName() + " " + user.getLastname();
                }

                count++;

                if (count < 5) {
                    s += ", ";
                } else {
                    s += " e mais";
                    break;
                }
            }
        }

        e.setGuests(s);

        for (Comment comment : ev.getComments()) {

            CommentDto c = new CommentDto();
            u = new UserDto();
            User userComment = comment.getUser();

            u.setId(userComment.getId());
            u.setFullName(userComment.getName() + " " + userComment.getLastname());
            u.setName(userComment.getName());
            u.setSurName(userComment.getLastname());
            u.setLogin(userComment.getLogin());
            u.setPhotoUrl(userComment.getPhotoUrl());

            c.setId(comment.getId());
            c.setUser(u);
            c.setComment(comment.getComment());

            cmDto.add(c);
        }

        e.setComments(cmDto);

        e.setGuests("João");

        int total = 0;
        e.setTotalGuests(total);
        e.setLike(true);
        e.setLikes(10);

        return ok(e);
    }

    @GET
    @Produces(JSON)
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
            e.setDate(fmt.format(event.getInitialdate()) + " - " + fmt.format(event.getFinaldate()));
            e.setInitialDate(event.getInitialdate().toString());
            e.setFinalDate(event.getFinaldate().toString());
            e.setLocation(event.getAddress());
            e.setDetail(event.getDescription());

            String s = "";

            List<Guest> g = event.getGuests();

            if (g != null) {

                int count = 0;

                for (Guest entry : g) {
                    User user = entry.getGuest();
                    UserStatus userStatus = entry.getStatus();

                    if (userStatus == UserStatus.YES) {
                        s += user.getName() + " " + user.getLastname();
                    }

                    count++;

                    if (count < 5) {
                        s += ", ";
                    } else {
                        s += " e mais";
                        break;
                    }
                }
            }

            e.setGuests(s);

            ev.add(e);
        }

        return ok(ev);
    }

    @GET
    @Produces(JSON)
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
            e.setDate(fmt.format(event.getInitialdate()) + " - " + fmt.format(event.getFinaldate()));
            e.setInitialDate(event.getInitialdate().toString());
            e.setFinalDate(event.getFinaldate().toString());
            e.setLocation(event.getAddress());
            e.setDetail(event.getDescription());

            String s = "";

            List<Guest> g = event.getGuests();

            if (g != null) {

                int count = 0;

                for (Guest entry : g) {
                    User user = entry.getGuest();
                    UserStatus userStatus = entry.getStatus();

                    if (userStatus == UserStatus.YES) {
                        s += user.getName() + " " + user.getLastname();
                    }

                    count++;

                    if (count < 5) {
                        s += ", ";
                    } else {
                        s += " e mais";
                        break;
                    }
                }
            }

            e.setGuests(s);

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
            e.setDate(fmt.format(event.getInitialdate()) + " - " + fmt.format(event.getFinaldate()));
            e.setInitialDate(event.getInitialdate().toString());
            e.setFinalDate(event.getFinaldate().toString());
            e.setLocation(event.getAddress());
            e.setDetail(event.getDescription());

            String s = "";

            List<Guest> g = event.getGuests();

            if (g != null) {

                int count = 0;

                for (Guest entry : g) {
                    User user = entry.getGuest();
                    UserStatus userStatus = entry.getStatus();

                    if (userStatus == UserStatus.YES) {
                        s += user.getName() + " " + user.getLastname();
                    }

                    count++;

                    if (count < 5) {
                        s += ", ";
                    } else {
                        s += " e mais";
                        break;
                    }
                }
            }

            e.setGuests(s);
            
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

        e.setCoverImage(c.getCoverUrl());
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

        return noContent();
    }

    /*@PUT
     @Produces(JSON)
     @Consumes(JSON)
     @Path("{id:[0-9]+}/comment/{idComment:[0-9]+}/like")
     public Response likeComment(@PathParam("id") long id, @PathParam("idComment") long idComment, long idUser) {
        
     Event e = dao.getById(id);

     if (e == null) {
     return notFound();
     }
        
     CommentDAO commentDAO = CommentDAO.getInstance();        
     Comment c = commentDAO.getById(idComment);
        
     if (c == null) {
     return notFound();
     }
        
     UserDAO userDAO = UserDAO.getInstance();        
     List<User> l = e.getLikes();
        
     l.add(userDAO.getById(idUser));
        
     e.setLikes(l);              
        
     dao.save(e);

     return noContent();
     }*/
    @PUT
    @Produces(JSON)
    @Consumes(JSON)
    @Path("{id:[0-9]+}/like")
    public Response likeEvent(@PathParam("id") long id, long idUser) {

        Event e = dao.getById(id);

        if (e == null) {
            return notFound();
        }

        UserDAO userDAO = UserDAO.getInstance();
        List<User> l = e.getLikes();

        l.add(userDAO.getById(idUser));

        e.setLikes(l);

        dao.save(e);

        return noContent();
    }

    @PUT
    @Produces(JSON)
    @Consumes(JSON)
    @Path("{id:[0-9]+}/share")
    public Response shareEvent(@PathParam("id") long id, long idUser) {

        Event e = dao.getById(id);

        if (e == null) {
            return notFound();
        }

        UserDAO userDAO = UserDAO.getInstance();
        List<User> s = e.getShares();

        s.add(userDAO.getById(idUser));

        e.setShares(s);

        dao.save(e);

        return noContent();
    }

    @PUT
    @Produces(JSON)
    @Consumes(JSON)
    @Path("{id:[0-9]+}/guest/{idUser:[0-9]+}/answer/{status}")
    public Response addGuest(@PathParam("id") long id, @PathParam("idUser") long idUser, @PathParam("status") UserStatus status) {

        Event e = dao.getById(id);

        if (e == null) {
            return notFound();
        }

        UserDAO userDAO = UserDAO.getInstance();
        List<Guest> gs = e.getGuests();
        Guest g = new Guest();
        
        g.setGuest(userDAO.getById(idUser));
        g.setStatus(status);
        gs.add(g);
        
        e.setGuests(gs);

        dao.save(e);

        return noContent();
    }
}
