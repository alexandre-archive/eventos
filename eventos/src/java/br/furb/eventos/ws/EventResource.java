package br.furb.eventos.ws;

import br.furb.eventos.dto.EventDto;
import br.furb.eventos.dto.NewEventDto;
import br.furb.eventos.dto.UserDto;
import br.furb.eventos.entity.Event;
import br.furb.eventos.entity.EventDAO;
import br.furb.eventos.entity.User;
import br.furb.eventos.entity.UserDAO;
import java.text.ParseException;
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
        
        if (ev == null)
        {
            return notFound();
        }
        
        User owner = ev.getOwner();
        
        EventDto e = new EventDto();
        UserDto u = new UserDto();
        u.setId(owner.getId());
        u.setFullName(owner.getName() + " " + owner.getLastname());
        
        e.setId(id);
        e.setOwner(u);
        e.setCoverUrl(ev.getCoverImage());
        e.setTitle(ev.getName());
        e.setDate(ev.getInitialdate() + " - " + ev.getFinaldate());
        e.setLocation(ev.getAddress());
        e.setDetail(ev.getDescription());
        //e.setGuests(ev.getGuests());
        //e.setTotalGuests();
        e.setLike(true);
        //e.setLikes(id);
        
        return ok(e);
    }
    /*@GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        User u = new User();
        u.setName("Teste");
        u.setEmail("teste@hehe.com");
        u.setId(1l);
        UserDAO dao = UserDAO.getInstance();
        
        dao.save(u);
        
        u = dao.getById(1);
        
        if (u == null) {
            return "nao existe";
        } else {
            return "user existe";
        }
        //Profile p = new Profile();
        //p.setName("nome do profile");
        //p.setId(51l);
        //ProfileDAO dao = ProfileDAO.getInstance();
        
        //if (dao.verify(p))
        //    return "existe...";
        //else
        //    return "nao existe...";
        
        //dao.remove(p);
        //return "Removed";
        
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
        //Comment c = new Comment("HAUahuAhuaHU");
        //CommentDAO dao = CommentDAO.getInstance();
        //dao.salvar(c);
        //return "Comment salvo...";
    }*/

    @POST
    @Produces(JSON)
    @Consumes(JSON)
    public Response addEvent(NewEventDto c) throws ParseException {
        Event e = new Event();
        UserDAO userDAO = UserDAO.getInstance();
        
        e.setOwner(userDAO.getById(c.getOwner()));
        e.setName(c.getTitle());
        e.setDescription(c.getDetail());
        //e.setInitialdate(new SimpleDateFormat("dd/MM/yyyy").parse(c.getInitialdate()));
        //e.setFinaldate(new SimpleDateFormat("dd/MM/yyyy").parse(c.getFinaldate()));
        e.setAddress(c.getLocation());
        
        dao.save(e);
       
        return created(e.getId());
    }
    
    @PUT
    @Produces(JSON)
    @Consumes(JSON)
    @Path("{id:[0-9]+}")
    public Response editEvent(@PathParam("id") long id, EventDto c) {        
        Event e = dao.getById(id);

        e.setName(c.getTitle());
        e.setDescription(c.getDetail());
        //e.setInitialdate(new SimpleDateFormat("dd/MM/yyyy").parse(c.getInitialdate()));
        //e.setFinaldate(new SimpleDateFormat("dd/MM/yyyy").parse(c.getFinaldate()));
        e.setAddress(c.getLocation());
        
        dao.save(e);
        
        return noContent();
    }
}
