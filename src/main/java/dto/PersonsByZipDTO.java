/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Address;
import entities.CityInfo;
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

    public PersonsByZipDTO(CityInfo cityinfo) {

        this.zip = cityinfo.getZip();
        for (Address add : cityinfo.getAddresses()) {
            for (Person person : add.getPersons()) {
                persons.add(new SimplePersonWithAddressDTO(person));
            }
        }
    }

    public String getZip() {
        return zip;
    }

    public List<SimplePersonWithAddressDTO> getPersons() {
        return persons;
    }
    
    
}
