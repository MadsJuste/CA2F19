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
public class ZipNotFoundException extends Exception {

    public ZipNotFoundException(String msg) {
        super(msg);
    }

    public ZipNotFoundException() {
    }
}
