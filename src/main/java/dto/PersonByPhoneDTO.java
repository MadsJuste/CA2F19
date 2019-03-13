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
 * @author Christian
 */
public class PersonByPhoneDTO {
    private String phone;
    private PersonDTO personDTO;

    public PersonByPhoneDTO(Phone phone) {
        this.phone = phone.getNumber();
        Person person = phone.getPerson();
        personDTO = new PersonDTO(person);
    }

}
