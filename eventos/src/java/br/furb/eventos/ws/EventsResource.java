/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.furb.eventos.ws;

import br.furb.eventos.dto.NewEventDto;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

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
        return "OK";
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
