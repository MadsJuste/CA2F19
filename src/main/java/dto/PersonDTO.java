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
        this.firstName = p.getFirstname();
        this.lastName = p.getLastname();
        this.email = p.getEmail();
        this.street = p.getAddress().getStreet();
        this.zip = p.getAddress().getCityinfozipcode();
        this.city = p.getAddress().getCityinfo().getCity();
        
        for (Phone phone : p.getPhoneCollection()) {
            phones.add(new PhoneDTO(phone));
        }
        
        for (Hobby h : p.getHobbyCollection()) {
            hobbies.add(new HobbyDTO(h));
        }
    }
}
