/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Phone;
import facade.Facade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Madsj
 */
@Path("person")
public class PersonResource {
    
    @Context
    private UriInfo context;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Facade f = new Facade(Persistence.createEntityManagerFactory("PU"));

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     * @return an instance of java.lang.String
     */
    
    @GET
    @Path("{street}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsByAddress(@PathParam("street") String street) {
       Address address = gson.fromJson(street, Address.class);
       
       return Response.ok().entity(gson.toJson(f.getPersonsByAddress(address))).build();
    }
    
    @GET
    @Path("{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByPhone(@PathParam("phone") String phone) {
        Phone p = gson.fromJson(phone, Phone.class);
       return Response.ok().entity(gson.toJson(f.getPersonByPhone(p))).build();
    }
    
    @GET
    @Path("{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsByHobby(@PathParam("hobby") String hobby) {
        Hobby h = gson.fromJson(hobby, Hobby.class);
      return Response.ok().entity(gson.toJson(f.getPersonsByHobby(h))).build();
    }
    
    @GET
    @Path("{zip}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsByZip(@PathParam("zip") String zip) {
         Cityinfo ci = gson.fromJson(zip, Cityinfo.class);
      return Response.ok().entity(gson.toJson(f.getPersonsByZip(ci))).build();
    }
    
    @GET
    @Path("count/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountByHobby(@PathParam("hobby") String hobby) {
         Hobby h = gson.fromJson(hobby, Hobby.class);
      return Response.ok().entity(gson.toJson(f.getCountByHobby(h))).build();
    }
    
    

    /**
     * PUT method for updating or creating an instance of PersonResource
     * @param content representation for the resource
     */
    @PUT
    @Path("{phone}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editPersonByPhone(String phone) {
        
        
    }
    
    @DELETE
    @Path("delete/{phone}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePersonByPhone(@PathParam("phone") String phone) {
        Phone p = gson.fromJson(phone, Phone.class);
        return Response.ok().entity(gson.toJson(f.deletePersonByPhone(p))).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(String content) {
        
        f.addPerson(JSONConverter.getPersonFromJson(content));
        return Response.ok().build();
    }
}
