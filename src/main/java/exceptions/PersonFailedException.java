/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author thomas
 */
public class PersonFailedException extends Exception {

    public PersonFailedException(String msg) {
        super(msg);
    }

    public PersonFailedException() {
    }
}
