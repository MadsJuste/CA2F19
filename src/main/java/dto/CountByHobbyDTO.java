/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Hobby;
import entities.Person;
import java.util.List;

/**
 *
 * @author Christian
 */
public class CountByHobbyDTO {

    private HobbyDTO hobby;
    private int count;

    public CountByHobbyDTO(Hobby hobby) {
        this.hobby = new HobbyDTO(hobby);
        this.count = hobby.getPersons().size();
    }

    public HobbyDTO getHobby() {
        return hobby;
    }

    public void setHobby(HobbyDTO hobby) {
        this.hobby = hobby;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
