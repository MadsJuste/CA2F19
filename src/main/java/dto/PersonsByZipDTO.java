/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mwn
 */
public class PersonsByZipDTO {
    
    private String zip;
    private List<SimplePersonWithAddressDTO> persons = new ArrayList();

    public PersonsByZipDTO(String zip, List<Person> p) {

        this.zip = zip;
        for (Person person : p) {
            persons.add(new SimplePersonWithAddressDTO(person));
        }
    }
    
}
