package facade;

import dto.PersonDTO;
import dto.PersonsByAddressDTO;
import dto.PersonsByHobbyDTO;
import dto.PersonsByZipDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
    @Override
    public PersonDTO getPersonByPhone(Phone phone) {
        EntityManager em = emf.createEntityManager();
        try {
            
        } finally {
            em.close();
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/*
*/

    @Override
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

    @Override
    public PersonsByZipDTO getPersonsByZip(CityInfo cityinfo) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery(
            "SELECT NEW dto.PersensByZipDTO(a) FROM CityInfo AS a WHERE a.zip = :zip"
            ,PersonsByZipDTO.class)
            .setParameter("zip", cityinfo.getZip());
            
            return (PersonsByZipDTO) query.getSingleResult();
        } finally {
            em.close();
        }        
    }
/*
    @Override 
    public CountByHobbyDTO getCountByHobby(Hobby hobby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    @Override
    public ZipCodesDTO getZipCodes(CityInfo cityinfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/

    @Override
    public PersonDTO createPerson(String content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO editPersonByPhone(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO deletePersonByPhone(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}