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

/**
 *
 * @author Mark
 */
public interface IFacade {

    //GETS
    //getPersonsByAddress() : GET - api/person/{street}
    public PersonsByAddressDTO getPersonsByAddress(Address address);

    //getPersonByPhone() : GET - api/person/{phone}
    public PersonByPhoneDTO getPersonByPhone(Phone phone);

    //getPersonsByHobby() : GET api/person/{hobby}
    public PersonsByHobbyDTO getPersonsByHobby(Hobby hobby);

    //getPersonsByZip() : GET api/person/{zip}
    public PersonsByZipDTO getPersonsByZip(CityInfo cityinfo);

    //getCountByHobby() : GET api/person/count/{hobby}
    public CountByHobbyDTO getCountByHobby(Hobby hobby);

    //getZipCodes() : GET api/zip/
    public AllCitiesAndAllZipCodesDTO getZipCodes();
/*
*/

    //POST
    //createPerson() : POST api/person
    public PersonDTO createPerson(Person person); //parameter check
    
    //PUT
    //editPersonByPhone() : PUT api/person/{phone}
    public PersonDTO editPersonByPhone(Person person);

    //DELETE
    //deletePersonByPhone() : DELETE api/person/{phone}
    public SimplePersonDTO deletePersonByPhone(Phone phone); 
    
}
