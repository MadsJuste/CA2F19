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
public class SimplePersonWithAddressDTO {

    private String firstName, lastName;
    private List<String> addresses = new ArrayList();

    public SimplePersonWithAddressDTO(Person p) {
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();

        for (Address a : p.getAddresses()) {
            addresses.add(a.getStreet());
        }
    }
}
