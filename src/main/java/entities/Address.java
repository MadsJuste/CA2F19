/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Madsj
 */
@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String additionalInfo;
    
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Person> persons = new ArrayList();
    
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private CityInfo cityinfo;

    public Address() {
    }

    public Address(String street) {
        this.street = street;
    }
    
    

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person person) {
        this.persons.add(person);
        if (person.getAddress() == null) {
            person.setAddress(this);
        }
    }

    public CityInfo getCityinfo() {
        return cityinfo;
    }

    public void setCityinfo(CityInfo cityinfo) {
        this.cityinfo = cityinfo;
        cityinfo.addAddress(this);
    }
    
   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
