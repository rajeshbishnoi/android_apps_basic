package ysic47.currencyconvertor;

import java.util.ArrayList;

/**
 * Created by hp on 02-07-2017.
 */

public class Gagan {

    ArrayList<String>currArr= new ArrayList<String>();
    ArrayList<String>rateArr= new ArrayList<String>();

    public ArrayList<String> getCurrArr() {
        return currArr;
    }

    public void setCurrArr(String name) {
        this.currArr.add(name);
    }

    public ArrayList<String> getRateArr() {
        return rateArr;
    }

    public void setRateArr(String vic) {
        this.rateArr.add(vic);
    }
}
