/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Person;
import entities.Phone;

/**
 *
 * @author Christian
 */
public class DeletePersonByPhoneDTO {
    private SimplePersonDTO persondto;

    public DeletePersonByPhoneDTO(Phone phone) {
        Person person = phone.getPerson();
        this.persondto = new SimplePersonDTO(person);
    }

    public SimplePersonDTO getPersondto() {
        return persondto;
    }

    public void setPersondto(SimplePersonDTO persondto) {
        this.persondto = persondto;
    }
}
