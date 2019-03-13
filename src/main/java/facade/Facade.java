package facade;

import dto.CountByHobbyDTO;
import dto.PersonByPhoneDTO;
import dto.PersonDTO;
import dto.PersonsByAddressDTO;
import dto.PersonsByHobbyDTO;
import dto.PersonsByZipDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.List;
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
            "SELECT NEW dto.PersonsByAddressDTO(a) FROM Address AS a WHERE a.street = :street",
             PersonsByAddressDTO.class)
            .setParameter("street", address.getStreet());

            List a = query.getResultList();
            // Kan dette undgås hvis man i REST tager flere DTO'er
            return (PersonsByAddressDTO) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public PersonByPhoneDTO getPersonByPhone(Phone phone) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery(
                    "SELECT NEW dto.PersonByPhoneDTO(a) FROM Phone AS a WHERE a.number = :number",
                     PersonByPhoneDTO.class)
                    .setParameter("number", phone.getNumber());

            return (PersonByPhoneDTO) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public PersonsByHobbyDTO getPersonsByHobby(Hobby hobby) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery(
                    "SELECT NEW dto.PersonsByHobbyDTO(a) FROM Hobby AS a WHERE a.name = :hobbyname",
                     PersonsByHobbyDTO.class)
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
                    "SELECT NEW dto.PersensByZipDTO(a) FROM CityInfo AS a WHERE a.zip = :zip",
                     PersonsByZipDTO.class)
                    .setParameter("zip", cityinfo.getZip());

            return (PersonsByZipDTO) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public CountByHobbyDTO getCountByHobby(Hobby hobby) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery(
                    "SELECT NEW dto.CountByHobbyDTO(a) FROM Hobby AS a WHERE a.name = :hobbyname",
                     CountByHobbyDTO.class)
                    .setParameter("hobbyname", hobby.getName());

            return (CountByHobbyDTO) query.getSingleResult();
        } finally {
            em.close();
        }

    }

    /*
    @Override
    public ZipCodesDTO getZipCodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     */
    @Override
    public PersonDTO createPerson(Person person) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT NEW dto.PersonDTO(a) FROM ", PersonDTO.class).setParameter("", em);
        } finally {
            em.close();
        }

    }

    @Override
    public PersonDTO editPersonByPhone(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO deletePersonByPhone(Phone phone) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createQuery(
            "SELECT a FROM Person a WHERE a.phones.number = :number"
            ,PersonDTO.class)
            .setParameter("number", phone.getNumber());
            
            Query query1 = em.createQuery(
            "SELECT NEW dto.PersonDTO(a) FROM Person AS a WHERE a.phones.number = :number"
            ,PersonDTO.class)
            .setParameter("number", phone.getNumber());
            
            Person person = (Person) query2.getSingleResult();
            PersonDTO pDTO = (PersonDTO) query1.getSingleResult();
            
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
            
            return pDTO;
        } finally {
            em.close();
        }
    }

}
