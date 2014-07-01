package br.furb.eventos.ws;

import br.furb.eventos.dto.UserDto;
import br.furb.eventos.entity.User;
import br.furb.eventos.entity.UserDAO;
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

    public UserResource() {
    }

    @GET
    @Produces("application/json")
    @Path("{id:[0-9]+}")
    public Response getUser(@PathParam("id") long id) {

        UserDto u = new UserDto();
        u.setId(id);
        u.setFullName("Dick Piroca1");
        u.setLogin("go@foo.com");

        return ok(u);
    }

    @GET
    @Produces(JSON)
    @Path("{login}")
    public Response getUser(@PathParam("login") String login) throws IOException {
        // VALIDAR USUARIO AQUI
        UserDto u = new UserDto();
        u.setId(2);
        u.setFullName("Dick Piroca2");
        u.setLogin(login);

        return ok(u);
    }

    @POST
    @Produces(JSON)
    @Consumes(JSON)
    public Response addUser(UserDto c) {
        
        User u = new User();
        
        u.setName(c.getName());
        u.setLogin(c.getLogin());
        u.setEmail(c.getLogin());
        u.setName(c.getName());
        u.setLastname(c.getSurName());
        u.setPwd(c.getPwd());
        
        UserDAO dao = UserDAO.getInstance();
        dao.save(u);
        
        return created(u.getId());
    }

    @PUT
    @Produces(JSON)
    @Consumes(JSON)
    @Path("{id:[0-9]+}")
    public Response editUser(@PathParam("id") long id, UserDto content) {
        return noContent();
    }
}
