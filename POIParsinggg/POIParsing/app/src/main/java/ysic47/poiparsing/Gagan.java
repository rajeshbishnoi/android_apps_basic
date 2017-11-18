package ysic47.poiparsing;

import java.util.ArrayList;

/**
 * Created by hp on 02-07-2017.
 */

public class Gagan {

    ArrayList<String>nameArr= new ArrayList<String>();
    ArrayList<String>vicArr= new ArrayList<String>();

    public ArrayList<String> getNameArr() {
        return nameArr;
    }

    public void setNameArr(String name) {
        this.nameArr.add(name);
    }


    public ArrayList<String> getVicArr() {
        return vicArr;
    }


    public void setVicArr(String vic) {
        this.vicArr.add(vic);
    }
}
