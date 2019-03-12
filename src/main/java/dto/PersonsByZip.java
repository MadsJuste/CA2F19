/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mwn
 */
public class PersonsByZip {
    
    private String zip;
    private List<SimplePersonWithAddressDTO> persons = new ArrayList();

    public PersonsByZip(String zip, List<Person> p) {
        this.zip = zip();
        for (Person person : p) {
            persons.add(new SimplePersonWithAddressDTO(person));
        }
    }
    
}
