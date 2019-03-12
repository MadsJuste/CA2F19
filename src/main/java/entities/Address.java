/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "address")
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
    , @NamedQuery(name = "Address.findByStreet", query = "SELECT a FROM Address a WHERE a.street = :street")
    , @NamedQuery(name = "Address.findByAdditionalinfo", query = "SELECT a FROM Address a WHERE a.additionalinfo = :additionalinfo")
    , @NamedQuery(name = "Address.findByCityinfozipcode", query = "SELECT a FROM Address a WHERE a.cityinfozipcode = :cityinfozipcode")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 245)
    @Column(name = "Street", nullable = false, length = 245)
    private String street;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "additionalinfo", nullable = false, length = 45)
    private String additionalinfo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cityinfo_zipcode", nullable = false)
    private String cityinfozipcode;
    @JoinColumn(name = "Cityinfo_zipcode1", referencedColumnName = "zipcode", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cityinfo cityinfo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private Collection<Person> personCollection;

    public Address(String street, String additionalinfo, String cityinfozipcode, Cityinfo cityinfo, Collection<Person> personCollection) {
        this.street = street;
        this.additionalinfo = additionalinfo;
        this.cityinfozipcode = cityinfozipcode;
        this.cityinfo = cityinfo;
        this.personCollection = personCollection;
    }

    public Address(String street, String additionalinfo) {
        this.street = street;
        this.additionalinfo = additionalinfo;
    }

    public Address(String street) {
        this.street = street;
    }

    public Address() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalinfo() {
        return additionalinfo;
    }

    public void setAdditionalinfo(String additionalinfo) {
        this.additionalinfo = additionalinfo;
    }

    public String getCityinfozipcode() {
        return cityinfozipcode;
    }

    public void setCityinfozipcode(String cityinfozipcode) {
        this.cityinfozipcode = cityinfozipcode;
    }

    public Cityinfo getCityinfo() {
        return cityinfo;
    }

    public void setCityinfo(Cityinfo cityinfo) {
        this.cityinfo = cityinfo;
    }

    public Collection<Person> getPersonCollection() {
        return personCollection;
    }

    public void setPersonCollection(Collection<Person> personCollection) {
        this.personCollection = personCollection;
    }

 

}
