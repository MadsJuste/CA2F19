/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import facade.Facade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Madsj
 */
@Path("zip")
public class ZipResource {
    
    Gson gson = new Gson();

    @Context
    private UriInfo context;

    Facade f = new Facade(Persistence.createEntityManagerFactory("pu"));

    public ZipResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getZipCodes() {
      return Response.ok().entity(gson.toJson(f.getZipCodes())).build();
    }
    }
    
}
