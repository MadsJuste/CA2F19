/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Hobby;
import entities.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mwn
 */
public class PersonsByHobbyDTO {

    private String hobby;
    private List<SimplePersonDTO> persons = new ArrayList();

    public PersonsByHobbyDTO(Hobby h) {
        this.hobby = h.getName();
        for (Person p : h.getPersons()) {
            persons.add(new SimplePersonDTO(p));
        }
    }

}
