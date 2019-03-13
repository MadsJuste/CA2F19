/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Madsj
 */
@Entity
public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String zip;
    private String city;
    
    @OneToMany(mappedBy = "cityinfo")
    private List<Address> addresses = new ArrayList();

    public Integer getId() {
        return id;
    }

    public CityInfo() {
    }


    public CityInfo(String zip) {
        this.zip = zip;
    }
    
    public CityInfo(String zip, String city) {
        this.zip = zip;
        this.city = city;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setCityinfo(this);
    }


    
}
