/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facadetest;

import dto.AllCitiesAndAllZipCodesDTO;
import dto.CountByHobbyDTO;
import dto.PersonByPhoneDTO;
import dto.PersonsByAddressDTO;
import dto.PersonsByHobbyDTO;
import dto.PersonsByZipDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import facade.Facade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mwn
 */
public class FacadeTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu", null);

    Facade facade = new Facade(emf);

    @Before
    public void setUp() {

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from Phone").executeUpdate();
            em.createQuery("delete from Hobby").executeUpdate();
            em.createQuery("delete from Person").executeUpdate();
            em.createQuery("delete from Address").executeUpdate();
            em.createQuery("delete from CityInfo").executeUpdate();
            

//            em.createQuery("delete from Person_Hobby").executeUpdate();
            Person p1 = new Person("Jens", "Henriksen", "jens@henriksen.dk");
            Person p2 = new Person("Morten", "Hansen", "morten@hansen.dk");
            Person p3 = new Person("Karsten", "Eriksen", "karsten@eriksen.dk");

            Hobby h1 = new Hobby("Soccer");

            p1.addHobby(h1);
            p1.addHobby(new Hobby("Handball"));
            p2.addHobby(new Hobby("Surfing"));
            p2.addHobby(new Hobby("Golf"));
            p3.addHobby(h1);
            p3.addHobby(new Hobby("Tennis"));

            Address a1 = new Address("Strandvejen 215");
            Address a2 = new Address("Klampenborgvej 38");

            a1.setCityinfo(new CityInfo("2950", "Vedbæk"));
            a2.setCityinfo(new CityInfo("2800", "Lyngby"));

            p1.setAddress(a1);
            p2.setAddress(a1);
            p3.setAddress(a2);

            Phone phone1 = new Phone("28475739");
            Phone phone2 = new Phone("58373895");
            Phone phone3 = new Phone("75839204");

            p1.addPhones(phone1);
            p2.addPhones(phone2);
            p3.addPhones(phone3);
            
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    @Test
    public void testGetPersonsByAddress() {
        PersonsByAddressDTO res = facade.getPersonsByAddress(new Address("Strandvejen 215"));
        assertEquals(2, res.getPersons().size());
    }

    @Test
    public void testCityForAddress() {
        PersonsByAddressDTO res = facade.getPersonsByAddress(new Address("Klampenborgvej 38"));
        assertEquals("Lyngby", res.getCity());
    }

    @Test
    public void testGetPersonByPhone() {
        PersonByPhoneDTO res = facade.getPersonByPhone(new Phone("75839204"));
        assertEquals("Karsten", res.getPersonDTO().getFirstName());
    }

    @Test
    public void testGetPersonsByHobby() {
        PersonsByHobbyDTO res = facade.getPersonsByHobby(new Hobby("Soccer"));
        assertEquals(2, res.getPersons().size());
    }

    @Test
    public void testGetPersonsByZip() {
        PersonsByZipDTO res = facade.getPersonsByZip(new CityInfo("2950"));
        assertEquals(2, res.getPersons().size());
    }

    @Test
    public void testGetCountByHobby() {
        CountByHobbyDTO res = facade.getCountByHobby(new Hobby("Soccer"));
        assertEquals(2, res.getCount());
    }

    @Test
    public void testGetZipCodes() {
        AllCitiesAndAllZipCodesDTO res = facade.getZipCodes();
        assertEquals(2, res.getCityzipMap().size());
    }

    @Test
    public void testCreatePerson() {
        Address a = new Address("Nørregaardsvej 25");
        a.setCityinfo(new CityInfo("2800"));
        Person p = new Person("Jens", "Mikkelsen", a);
        facade.createPerson(p);
        PersonsByZipDTO res = facade.getPersonsByZip(new CityInfo("2800"));
        assertEquals(3, res.getPersons().size());
    }

    @Test
    public void testDeletePersonByPhone() {
        facade.deletePersonByPhone(new Phone("75839204"));
        PersonsByZipDTO res = facade.getPersonsByZip(new CityInfo("2800"));
        assertEquals(0, res.getPersons().size());
    }
}
