/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author mwn
 */
public class PhoneDTO {
    
    private String number, description;

    public PhoneDTO(Phone p) {
        this.number = p.getNumber();
        this.description = p.getDescription();
    }
    
    
}
