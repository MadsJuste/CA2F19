/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facadetest;

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

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-test", null);

    Facade facade = new Facade(emf);

    @Before
    public void setUp() {

        EntityManager em = emf.createEntityManager();

        Person p1 = new Person("Jens", "Henriksen", "jens@henriksen.dk");
        Person p2 = new Person("Morten", "Hansen", "morten@hansen.dk");
        Person p3 = new Person("Karsten", "Eriksen", "karsten@eriksen.dk");

        p1.addHobby(new Hobby("Soccer"));
        p1.addHobby(new Hobby("Handball"));
        p2.addHobby(new Hobby("Surfing"));
        p2.addHobby(new Hobby("Golf"));
        p3.addHobby(new Hobby("Kitesurfing"));
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

        phone1.setPerson(p1);
        phone2.setPerson(p2);
        phone3.setPerson(p3);

        try {
            em.getTransaction().begin();
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
    
    @Test
    public void testDB() {
        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, 1);
        assertEquals("Jens", p.getFirstName());
    }

}
