package at.sw2017.xp4.hobit;

/**
 * Created by Michaela on 10.05.2017.
 */

import java.util.ArrayList;

public class Continent {

    private String name;
    private ArrayList<Country> countryList = new ArrayList<Country>();

    public Continent(String name, ArrayList<Country> countryList) {
        super();
        this.name = name;
        this.countryList = countryList;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Country> getCountryList() {
        return countryList;
    }
    public void setCountryList(ArrayList<Country> countryList) {
        this.countryList = countryList;
    };
}
