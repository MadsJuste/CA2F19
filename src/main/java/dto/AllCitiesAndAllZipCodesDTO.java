/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.CityInfo;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import org.eclipse.persistence.internal.helper.ConcurrencyManager;

/**
 *
 * @author Christian
 */
public class AllCitiesAndAllZipCodesDTO {

    private LinkedHashMap<String, String> cityzipMap = new LinkedHashMap();

    public AllCitiesAndAllZipCodesDTO(List<CityInfo> cityinfoList) {
        for (CityInfo cityinfo : cityinfoList) {
            cityzipMap.put(cityinfo.getCity(), cityinfo.getZip());
        }
    }

    public LinkedHashMap<String, String> getCityzipMap() {
        return cityzipMap;
    }

    public void setCityzipMap(LinkedHashMap<String, String> cityzipMap) {
        this.cityzipMap = cityzipMap;
    }

}
