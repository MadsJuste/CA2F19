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
            System.out.println("begin transaction");
            CityInfo cityinfo = person.getAddress().getCityinfo();
            List<Hobby> hobbies = person.getHobbies();
            CityInfo setCity = (CityInfo) em.createQuery("SELECT a FROM CityInfo a WHERE a.zip = :zip").setParameter("zip", cityinfo.getZip()).getSingleResult();
            if (setCity != null) {
                person.getAddress().setCityinfo(setCity);
            }
            System.out.println("after setcity transaction");
            List<Hobby> hobbiesreturn = new ArrayList();
            for (Hobby hob : hobbies) {
                Hobby setHobby = (Hobby) em.createQuery("SELECT a FROM Hobby a WHERE a.name = :name").setParameter("name", hob.getName()).getSingleResult();
                if (setHobby != null) {
                    hobbiesreturn.add(setHobby);
                } else {
                    hobbiesreturn.add(hob);
                }
            }
            System.out.println("after hobby loop");
            person.setHobbies(hobbiesreturn);
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            System.out.println("returning personDTO\n" + person.getFirstName() + "\n: " + person.getAddress().getStreet() + "\n"
                    + person.getEmail() + "\n" + person.getAddress().getCityinfo().getZip() + "\n");
            return new PersonDTO(person);
        } catch (Exception ex) {
            System.out.println("begin transaction catch");
            /*
            CityInfo cityinfo = person.getAddress().getCityinfo();
            List<Hobby> hobbies = person.getHobbies();
            CityInfo setCity = (CityInfo) em.createQuery("SELECT a FROM CityInfo a WHERE a.zip = :zip").setParameter("zip", cityinfo.getZip());
            if (setCity != null) {
                person.getAddress().setCityinfo(setCity);
            }
            System.out.println("after setcity transaction");
            List<Hobby> hobbiesreturn = new ArrayList();
            for (Hobby hob : hobbies) {
                Hobby setHobby = (Hobby) em.createQuery("SELECT a FROM Hobby a WHERE a.name = :name").setParameter("name", hob.getName());
                if (setHobby != null) {
                    hobbiesreturn.add(setHobby);
                } else {
                    hobbiesreturn.add(hob);
                }
            }
            person.setHobbies(hobbiesreturn);
            System.out.println("pre transaction catch");
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
             */
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

}
