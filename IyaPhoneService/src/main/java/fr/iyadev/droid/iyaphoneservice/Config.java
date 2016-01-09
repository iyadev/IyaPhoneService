package fr.iyadev.droid.iyaphoneservice;

import fr.iyadev.droid.iyaphoneservice.Lib.JsonDialog;
import fr.iyadev.droid.iyaphoneservice.Lib.MyLog;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by iya on 01/12/15.
 */
public class Config {

    private static Config _instance;
    public static Context context;
    private String phoneNumber;
    private String password;
    private Boolean enable;
    private SharedPreferences sharedPref ;
    public Config()
    {
        sharedPref = context.getSharedPreferences(
                String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
        phoneNumber = sharedPref.getString(String.valueOf(R.string.preference_phoneNumber), "");
        password = sharedPref.getString(String.valueOf(R.string.preference_password),"");
    }

    public static Config getInstance()
    {
        if(_instance == null)
        {
            _instance = new Config();
        }
        return _instance;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String newNumber)
    {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(String.valueOf(R.string.preference_phoneNumber), newNumber);
        phoneNumber = newNumber;
        editor.commit();
        MyLog.getInstance().log("Config SetPhoneNumber");
    }

    public void setEnable(Boolean boo)
    {
        enable = boo;
    }
    public int getPasswordLength()
    {
        return password.length();
    }

    public void setPassword(String newpassword)
    {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(String.valueOf(R.string.preference_password), newpassword);
        editor.commit();
        password = newpassword;
    }

    public Boolean comparePassword(String typePassword)
    {
        if(password.equals(typePassword) ==  true )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
