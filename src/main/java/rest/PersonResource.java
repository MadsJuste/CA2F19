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
import entities.Person;
import entities.Phone;
import exceptions.AddressNotFoundException;
import exceptions.HobbyNotFoundException;
import exceptions.PersonFailedException;
import exceptions.PhoneNotFoundException;
import exceptions.ZipNotFoundException;
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

@Path("person")
public class PersonResource {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;
    
    Facade f = new Facade(Persistence.createEntityManagerFactory("PU"));

    public PersonResource() {
    }

    @GET
    @Path("/street/{street}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsByAddress(@PathParam("street") String street) throws AddressNotFoundException {
        Address address = new Address(street);
        if (address == null) {
            return Response.status(404)
                    .entity(gson.toJson(new AddressNotFoundException("no Address found")))
                    .type(MediaType.APPLICATION_JSON).
                    build();
        }
        return Response.ok().entity(gson.toJson(f.getPersonsByAddress(address))).build();
    }

    @GET
    @Path("/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByPhone(@PathParam("phone") String phone) throws PhoneNotFoundException {
        Phone p = new Phone(phone);
        if (p == null) {
            return Response.status(404)
                    .entity(gson.toJson(new PhoneNotFoundException("Phone number unknown")))
                    .type(MediaType.APPLICATION_JSON).
                    build();
        }
        return Response.ok().entity(gson.toJson(f.getPersonByPhone(p))).build();
    }

    @GET
    @Path("/hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsByHobby(@PathParam("hobby") String hobby) throws HobbyNotFoundException {
        Hobby h = new Hobby(hobby);
        if (h == null) {
            return Response.status(404)
                    .entity(gson.toJson(new HobbyNotFoundException("Hobby unknown")))
                    .type(MediaType.APPLICATION_JSON).
                    build();
        }
        return Response.ok().entity(gson.toJson(f.getPersonsByHobby(h))).build();
    }

    @GET
    @Path("/zip/{zip}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsByZip(@PathParam("zip") String zip) throws ZipNotFoundException {
        CityInfo ci = new CityInfo(zip);
        if (ci == null) {
            return Response.status(404)
                    .entity(gson.toJson(new ZipNotFoundException("ZIP unknown")))
                    .type(MediaType.APPLICATION_JSON).
                    build();
        }
        return Response.ok().entity(gson.toJson(f.getPersonsByZip(ci))).build();
    }

    @GET
    @Path("count/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountByHobby(@PathParam("hobby") String hobby) throws HobbyNotFoundException {
        Hobby h = new Hobby(hobby);
        if (h == null) {
            return Response.status(404)
                    .entity(gson.toJson(new HobbyNotFoundException("Hobby unknown")))
                    .type(MediaType.APPLICATION_JSON).
                    build();
        }
        return Response.ok().entity(gson.toJson(f.getCountByHobby(h))).build();
    }

    
        //DENNE SKAL JEG KIGGE PÅ!
    @PUT
    @Path("/phone/{phone}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editPersonByPhone(String content) throws PhoneNotFoundException {
        Person p = gson.fromJson(content, Person.class);
        if (p == null) {
            return Response.status(404)
                    .entity(gson.toJson(new PhoneNotFoundException("Phone number unknown")))
                    .type(MediaType.APPLICATION_JSON).
                    build();
        }
        return Response.ok().entity(gson.toJson(f.editPersonByPhone(p))).build();

    }

    @DELETE
    @Path("delete/{phone}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePersonByPhone(@PathParam("phone") String phone) throws PhoneNotFoundException {
        Phone p = new Phone(phone);
        if (p == null) {
            return Response.status(404)
                    .entity(gson.toJson(new PhoneNotFoundException("Phone number unknown")))
                    .type(MediaType.APPLICATION_JSON).
                    build();
        }
        return Response.ok().entity(gson.toJson(f.deletePersonByPhone(p))).build();
    }
    // og denne her =? 
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(String content) throws PersonFailedException {
        Person p = gson.fromJson(content, Person.class);
        if (p == null) {
            return Response.status(404)
                    .entity(gson.toJson(new PersonFailedException("Content for creating person is incorrect")))
                    .type(MediaType.APPLICATION_JSON).
                    build();
        }
        return Response.ok().entity(gson.toJson(f.createPerson(p))).build();
    }
}
