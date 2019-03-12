package facade;

import dto.PersonDTO;
import dto.PersonsByAddressDTO;
import dto.PersonsByHobbyDTO;
import dto.PersonsByZipDTO;
import entities.Address;
import entities.Cityinfo;
import entities.Hobby;
import entities.Phone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Mark
 */
public class Facade implements IFacade {

    EntityManagerFactory emf;

    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public static void main(String[] args) {
        Facade f = new Facade(Persistence.createEntityManagerFactory("pu"));
        System.out.println(f.getPersonsByAddress(new Address("Strandvejen 215")));
    }
    
    @Override
    public PersonsByAddressDTO getPersonsByAddress(Address address) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery(
            "SELECT NEW dto.PersonsByAddressDTO(a) FROM Address AS a WHERE a.street = :street"
            ,PersonsByAddressDTO.class)
            .setParameter("street", address.getStreet());
            
            return (PersonsByAddressDTO) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    //@Override
    public PersonDTO getPersonByPhone(Phone phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public PersonsByHobbyDTO getPersonsByHobby(Hobby hobby) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery(
            "SELECT NEW dto.PersonsByHobbyDTO(a) FROM Hobby AS a WHERE a.name = :hobbyname"
            ,PersonsByHobbyDTO.class)
            .setParameter("hobbyname", hobby.getName());
            
            return (PersonsByHobbyDTO) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    //@Override
    public PersonsByZipDTO getPersonsByZip(Cityinfo cityinfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override 
    /*
    public CountByHobbyDTO getCountByHobby(Hobby hobby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
/*
    //@Override
    public ZipCodesDTO getZipCodes(Cityinfo cityinfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */

    //@Override
    public PersonDTO createPerson(String content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public PersonDTO editPersonByPhone(Phone phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public PersonDTO deletePersonByPhone(Phone phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}