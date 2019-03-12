package facade;

import entities.Address;
import entities.Cityinfo;
import entities.Hobby;
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
    public PersonsByZipDTO getPersonsByZip(Cityinfo cityinfo);

    //getCountByHobby() : GET api/person/count/{hobby}
    public CountByHobbyDTO getCountByHobby(Hobby hobby);

    //getZipCodes() : GET api/zip/
    public ZipCodesDTO getZipCodes(Cityinfo cityinfo);

    //POST
    //createPerson() : POST api/person
    public PersonDTO createPerson(String content); //parameter check
    
    //PUT
    //editPersonByPhone() : PUT api/person/{phone}
    public PersonDTO editPersonByPhone(Phone phone);

    //DELETE
    //deletePersonByPhone() : DELETE api/person/{phone}
    public PersonDTO deletePersonByPhone(Phone phone); 
    
}
