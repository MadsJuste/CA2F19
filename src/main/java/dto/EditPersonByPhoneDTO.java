/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.List;

/**
 *
 * @author Christian
 */
public class EditPersonByPhoneDTO {

    private String phone;
    private PersonDTO personDTO;

    public EditPersonByPhoneDTO(Phone phone, Address address, String email, String firstName, String lastname, List<Hobby> hobbies) {
        this.phone = phone.getNumber();
        Person person = phone.getPerson();
        person.setAddress(address);
        person.setEmail(email);
        person.setFirstName(firstName);
        person.setLastName(lastname);
        person.setHobbies(hobbies);
        personDTO = new PersonDTO(person);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public void PersonDTO(PersonDTO personDTO) {
        this.personDTO = personDTO;
    }
}
