package br.furb.eventos.ws;

import java.util.Set;
import javax.ws.rs.core.Application;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 * Classe de configuração dos Web Services.
 *
 * @author Alexandre
 *
 * URL padrão: http://localhost:8084/eventos/api/
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        // Habilita suporte a JSON no Jersey.
        resources.add(JacksonJsonProvider.class);

        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.furb.eventos.ws.EventResource.class);
        resources.add(br.furb.eventos.ws.UserResource.class);
    }
}
