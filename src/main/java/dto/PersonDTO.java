/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mwn
 */
public class PersonDTO {
    
    private String firstName, lastName, email;
    private List<PhoneDTO> phones = new ArrayList();
    private String street, zip, city;
    private List<HobbyDTO> hobbies = new ArrayList();

    public PersonDTO(Person p) {
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.email = p.getEmail();
        this.street = p.getAddress().getStreet();
        this.zip = p.getAddress().getCityinfo().getZip();
        this.city = p.getAddress().getCityinfo().getCity();
        
        for (Phone phone : p.getPhones()) {
            phones.add(new PhoneDTO(phone));
        }
        
        for (Hobby h : p.getHobbies()) {
            hobbies.add(new HobbyDTO(h));
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public List<HobbyDTO> getHobbies() {
        return hobbies;
    }
    
    
}
