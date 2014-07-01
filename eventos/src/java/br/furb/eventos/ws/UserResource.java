package br.furb.eventos.ws;

import br.furb.eventos.dto.UserDto;
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

    /* O exemplo abaixo sempre retorna 200. Nos metodos abaixo pode-se definir o status.
     @GET
     @Produces("application/json")
     @Path("{id:[0-9]+}")
     public UserDto getJson(@PathParam("id") long id) {

     UserDto u = new UserDto();
     u.setId(id);
     u.setFullName("Dick Piroca");
     u.setLogin("go@foo.com");

     return u;
     }
     */
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

    /*
     @POST
     @Produces(JSON)
     @Consumes(JSON)
     public Response addUser(String content) {
     UserDto u = (UserDto) fromJson(content, UserDto.class);
     return created(u.getId());
     }*/
    @POST
    @Produces(JSON)
    @Consumes(JSON)
    public Response addUser(UserDto content) {
        return created(content.getId());
    }

    /*
     @PUT
     @Produces(JSON)
     @Consumes(JSON)
     @Path("{id:[0-9]+}")
     public Response editUser(@PathParam("id") long id, String content) {
     UserDto u = (UserDto) fromJson(content, UserDto.class);
     return noContent();
     }*/
    @PUT
    @Produces(JSON)
    @Consumes(JSON)
    @Path("{id:[0-9]+}")
    public Response editUser(@PathParam("id") long id, UserDto content) {
        return noContent();
    }
}
