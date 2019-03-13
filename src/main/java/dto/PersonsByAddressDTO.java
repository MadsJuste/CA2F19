/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Address;
import entities.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mwn
 */
public class PersonsByAddressDTO {
    
    private String street, zip, city;
    private List<SimplePersonDTO> persons = new ArrayList();

    public PersonsByAddressDTO(Address a) {
        this.street = a.getStreet();
        this.zip = a.getCityinfo().getZip();
        this.city = a.getCityinfo().getCity();
        for (Person p : a.getPersons()) {
            persons.add(new SimplePersonDTO(p));
        }
    }
    
    
}
