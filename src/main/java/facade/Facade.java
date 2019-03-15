package facade;

import dto.AllCitiesAndAllZipCodesDTO;
import dto.CountByHobbyDTO;
import dto.PersonByPhoneDTO;
import dto.PersonDTO;
import dto.PersonsByAddressDTO;
import dto.PersonsByHobbyDTO;
import dto.PersonsByZipDTO;
import dto.SimplePersonDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
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
                    "SELECT NEW dto.PersonsByZipDTO(a) FROM CityInfo AS a WHERE a.zip = :zip",
                    PersonsByZipDTO.class)
                    .setParameter("zip", cityinfo.getZip()).setMaxResults(1);
            PersonsByZipDTO pbzDTO = (PersonsByZipDTO) query.getSingleResult();
            return pbzDTO;
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
     */
    @Override
    public AllCitiesAndAllZipCodesDTO getZipCodes() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT a FROM CityInfo a");
            return new AllCitiesAndAllZipCodesDTO(query.getResultList());
        } finally {
            em.close();
        }
    }

    public SimplePersonDTO createPersonSimplified(Person p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new SimplePersonDTO(p);
    }

    @Override
    public PersonDTO createPerson(Person person) {
        EntityManager em = emf.createEntityManager();
        try {

            em.getTransaction().begin();

            //Check that Hobby is not already in the db VIRKER IKKE!
            /*
          for(int i = 0; i < person.getHobbies().size(); i++){  
             Query query = em.createQuery(
                    "SELECT h FROM Hobby AS h WHERE h.name = :name");
             query.setParameter("name", person.getHobbies().get(i).getName());

            Hobby h = (Hobby) query.getSingleResult();
            
            if(h.getName().equals(person.getHobbies().get(i).getName())){
                person.getHobbies().set(i, h);
            }
          }*/
            Query query = em.createQuery(
                    "SELECT COUNT(h) FROM Hobby AS h WHERE h.name = :name");
            query.setParameter("name", person.getHobbies().get(0).getName());

            long q = (long) query.getSingleResult();

            if (q == 1) {
                query = em.createQuery(
                        "SELECT h FROM Hobby AS h WHERE h.name = :name");
                query.setParameter("name", person.getHobbies().get(0).getName());

                Hobby h = (Hobby) query.getSingleResult();
                person.getHobbies().set(0, h);
            }

            //check that Zip is not already in the db
            query = em.createQuery(
                    "SELECT COUNT(z) FROM CityInfo AS z WHERE z.zip = :zip");
            query.setParameter("zip", person.getAddress().getCityinfo().getZip());

            q = (long) query.getSingleResult();

            if (q == 1) {
                query = em.createQuery(
                        "SELECT z FROM CityInfo AS z WHERE z.zip = :zip");
                query.setParameter("zip", person.getAddress().getCityinfo().getZip());

                CityInfo ci = (CityInfo) query.getSingleResult();
                person.getAddress().setCityinfo(ci);
            }

            em.persist(person);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    @Override
    public PersonDTO editPersonByPhone(Person person, String number) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Query query2 = em.createQuery(
                    "SELECT p FROM Person p JOIN p.phones ph WHERE ph.number = :number");
            query2.setParameter("number", number);
            Person temp = (Person) query2.getSingleResult();
            person.setId(temp.getId());

            em.merge(person);
            em.getTransaction().commit();

            Query query = em.createQuery(
                    "SELECT NEW dto.PersonDTO(a) FROM Person AS a WHERE a.id = :id",
                    PersonDTO.class)
                    .setParameter("id", person.getId());

            return (PersonDTO) query.getSingleResult();
        } finally {
            em.close();
        }

    }

    @Override
    public SimplePersonDTO deletePersonByPhone(Phone phone) {
        EntityManager em = emf.createEntityManager();
        Person person = null;
        try {
            em.getTransaction().begin();
            Query query2 = em.createQuery(
                    "SELECT p FROM Person p JOIN p.phones ph WHERE ph.number = :number");
            query2.setParameter("number", phone.getNumber());

            person = (Person) query2.getSingleResult();

            SimplePersonDTO pDTO = new SimplePersonDTO(person);

            em.remove(person);
            em.getTransaction().commit();
            return pDTO;

        } finally {

            em.close();
        }
    }

    @Override
    public CityInfo zipAssister(CityInfo cityinfo) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery(
                    "SELECT a FROM CityInfo a WHERE a.zip = :zip")
                    .setParameter("zip", cityinfo.getZip());

            return (CityInfo) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public boolean checkIfAddressExist(Address address) {
        EntityManager em = emf.createEntityManager();
        List<Address> queryAddress = new ArrayList();
        try {
            queryAddress = (List<Address>) em.createQuery(
                    "SELECT a FROM Address a WHERE a.street = :street")
                    .setParameter("street", address.getStreet()).getResultList();
        } catch (Exception ex) {

        } finally {
            em.close();
        }
        if (!queryAddress.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Hobby checkIfHobbyExist(String hobby) {
        EntityManager em = emf.createEntityManager();
        List<Hobby> hob = (List<Hobby>) em.createQuery("SELECT a FROM Hobby a WHERE a.name = :name")
                .setParameter("name", hobby).getResultList();
        if (!hob.isEmpty()) {
            return hob.get(0);
        }
        return null;
    }

    public Phone checkifPhoneExists(String phone) {
        EntityManager em = emf.createEntityManager();
        List<Phone> hob = (List<Phone>) em.createQuery("SELECT a FROM Phone a WHERE a.number = :number")
                .setParameter("number", phone).getResultList();
        if (!hob.isEmpty()) {
            return hob.get(0);
        }
        return null;
    }

    public CityInfo checkifZipExists(String zip) {
        EntityManager em = emf.createEntityManager();
        List<CityInfo> hob = (List<CityInfo>) em.createQuery("SELECT a FROM CityInfo a WHERE a.zip = :zip")
                .setParameter("zip", zip).getResultList();
        if (!hob.isEmpty()) {
            return hob.get(0);
        }
        return null;
    }
}
