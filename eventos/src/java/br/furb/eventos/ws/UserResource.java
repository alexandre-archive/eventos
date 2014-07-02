package br.furb.eventos.ws;

import br.furb.eventos.dto.UserDto;
import br.furb.eventos.entity.User;
import br.furb.eventos.dao.UserDAO;
import br.furb.eventos.dto.LoginDto;
import java.io.IOException;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * REST Web Service para a entidade de Usuários.
 *
 * @author Alexandre
 */
@Path("user")
public class UserResource extends BaseWs {

    private final UserDAO dao = UserDAO.getInstance();

    public UserResource() {
    }

    @GET
    @Produces(JSON)
    @Path("{id:[0-9]+}")
    public Response getUser(@PathParam("id") long id) {

        User usr = dao.getById(id);

        if (usr == null) {
            return notFound();
        }

        UserDto u = new UserDto();
        u.setId(id);
        u.setFullName(usr.getName() + " " + usr.getLastname());
        u.setName(usr.getName());
        u.setSurName(usr.getLastname());
        u.setLogin(usr.getLogin());
        u.setPhotoUrl(usr.getPhotoUrl());

        return ok(u);
    }

    @GET
    @Produces(JSON)
    @Path("{login}")
    public Response getUser(@PathParam("login") String login) throws IOException {

        User usr = dao.getByLogin(login);

        if (usr == null) {
            return notFound();
        }

        UserDto u = new UserDto();
        u.setId(usr.getId());
        u.setFullName(usr.getName() + " " + usr.getLastname());
        u.setName(usr.getName());
        u.setSurName(usr.getLastname());
        u.setLogin(usr.getLogin());
        u.setPhotoUrl(usr.getPhotoUrl());

        return ok(u);
    }

    @POST
    @Produces(JSON)
    @Consumes(JSON)
    public Response addUser(UserDto c) {

        if (!modelIsValid(c)) {
            return modelError();
        }
        
        User u = new User();

        u.setName(c.getName());
        u.setLogin(c.getLogin());
        u.setEmail(c.getLogin());
        u.setName(c.getName());
        u.setLastname(c.getSurName());
        u.setPwd(c.getPwd());

        if (dao.verify(u, "login")) {
            return modelError("Email já cadastrado.");
        }
        
        dao.save(u);

        return created(u.getId());
    }
    
    @POST
    @Produces(JSON)
    @Consumes(JSON)
    @Path("auth")
    public Response validateUser(LoginDto c) {

        if (!modelIsValid(c)) {
            return badRequest();
        }
        
        User u = new User();

        u.setLogin(c.getLogin());
        u.setPwd(c.getPwd());

        if (dao.verify(u, "login") && dao.verify(u, "pwd")) {
            return noContent();
        }

        return badRequest();
    }

    @PUT
    @Produces(JSON)
    @Consumes(JSON)
    @Path("{id:[0-9]+}/photo")
    public Response updatePhoto(@PathParam("id") long id, String url) {
        
        User usr = dao.getById(id);

        if (usr == null) {
            return notFound();
        }
        
        usr.setPhotoUrl(url);
        dao.save(usr);
        
        return noContent();
    }
    
    @PUT
    @Produces(JSON)
    @Consumes(JSON)
    @Path("{id:[0-9]+}")
    public Response editUser(@PathParam("id") long id, UserDto c) {
        
        if (!modelIsValid(c)) {
            return modelError();
        }
        
        return noContent();
    }
}
