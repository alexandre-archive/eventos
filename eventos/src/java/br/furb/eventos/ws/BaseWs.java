package br.furb.eventos.ws;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

/**
 * Classe base para todos os WS.
 *
 * @author Alexandre

 */
public class BaseWs {

    @Context
    private UriInfo context;

    public static final String JSON = MediaType.APPLICATION_JSON;
    
    /***
     * Converte um objeto para JSON.
     * @param o
     * @return 
     */
    public String toJson(Object o) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(o);
        } catch (IOException ex) {
            Logger.getLogger(BaseWs.class.getName()).log(Level.SEVERE, null, ex);
            return "{ \"erro\": \"" + ex.getMessage() + " \" }";
        }
    }

    /***
     * Converte uma string para um objeto.
     * @param json String JSON.
     * @param type Tipo do objeto.
     * @return 
     */
    public Object fromJson(String json, Class type) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, type);
        } catch (IOException ex) {
            Logger.getLogger(BaseWs.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Response ok(Object o) {
        return Response.ok(o, MediaType.APPLICATION_JSON).build();
    }

    public Response created(long id) {
        String url = slash(context.getAbsolutePath().toString()) + id;
        return Response.created(URI.create(url)).build();
    }

    public Response notFound() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    public Response badRequest() {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public Response noContent() {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    public String slash(String s) {
        return s.endsWith("/") ? s : s + "/";
    }
}
