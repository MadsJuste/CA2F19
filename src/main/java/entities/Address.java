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

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "address")
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
    , @NamedQuery(name = "Address.findByStreet", query = "SELECT a FROM Address a WHERE a.addressPK.street = :street")
    , @NamedQuery(name = "Address.findByAdditionalinfo", query = "SELECT a FROM Address a WHERE a.additionalinfo = :additionalinfo")
    , @NamedQuery(name = "Address.findByCityinfozipcode", query = "SELECT a FROM Address a WHERE a.addressPK.cityinfozipcode = :cityinfozipcode")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Basic(optional = false)
    @Column(name = "additionalinfo", nullable = false, length = 45)
    private String additionalinfo;
    @JoinColumn(name = "Cityinfo_zipcode", referencedColumnName = "zipcode", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cityinfo cityinfo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private Collection<Person> personCollection;

    public Address() {
    }

 

   

    public String getAdditionalinfo() {
        return additionalinfo;
    }

    public void setAdditionalinfo(String additionalinfo) {
        this.additionalinfo = additionalinfo;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
}
