/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mwn
 */
public class SimplePersonDTO {

    private String firstName, lastName;
    private List<PhoneDTO> phones = new ArrayList();

    public SimplePersonDTO(Person p) {
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        for (Phone phone : p.getPhones()) {
            phones.add(new PhoneDTO(phone));
        }
    }

}
