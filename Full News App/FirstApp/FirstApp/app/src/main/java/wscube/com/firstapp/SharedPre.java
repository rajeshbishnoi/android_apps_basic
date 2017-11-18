package wscube.com.firstapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wscube on 30/12/16.
 */
public class SharedPre {
    SharedPreferences ShP;
    public void createSP(Context c)
    {
        ShP=c.getSharedPreferences("SaveData", c.MODE_PRIVATE);
       // return sp;
    }
    public void EDITint(String key,int value)
    {
        SharedPreferences.Editor spEditor=ShP.edit();
        spEditor.putInt(key,value);
        spEditor.commit();
    }
    public int readInt(String key)
    {
        int data=ShP.getInt(key,0);
        return data;
    }
}
