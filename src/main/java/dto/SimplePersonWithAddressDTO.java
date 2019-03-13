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
public class SimplePersonWithAddressDTO {

    private String firstName, lastName;
    private String addresses;

    public SimplePersonWithAddressDTO(Person p) {
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        addresses = p.getAddress().getStreet();
    }
}
