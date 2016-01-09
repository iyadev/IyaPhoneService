package fr.iyadev.droid.iyaphoneservice.Lib;

import android.util.Log;

/**
 * Created by iya on 08/12/15.
 */
public class MyLog {
    private static MyLog _instance;
    private int typeOfLog ;
    private String nameLog;
    public boolean enable;

    public MyLog()
    {
        nameLog = "IPS";
        typeOfLog = 1;
        enable = true;
    }

    public static MyLog getInstance()
    {
        if(_instance == null)
        {
            _instance = new MyLog();
        }
        return _instance;
    }
    public void log(String txtLog)
    {
        switch (typeOfLog) {
            case 1:
                Log.i(nameLog, txtLog);
                break;
            case 2:
                Log.d(nameLog, txtLog);
                break;
            case 3:
                Log.e(nameLog, txtLog);
                break;
            case 4:
                Log.w(nameLog, txtLog);
                break;

        }
    }
}
