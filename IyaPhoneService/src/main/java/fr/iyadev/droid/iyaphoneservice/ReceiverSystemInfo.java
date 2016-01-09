package fr.iyadev.droid.iyaphoneservice;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

/**
 * Created by iya on 01/12/15.
 */
public class ReceiverSystemInfo {
    private static ReceiverSystemInfo _instance;

    public static ReceiverSystemInfo getInstance()
    {
        if(_instance == null)
        {
            _instance = new ReceiverSystemInfo();
        }
        return _instance;
    }

    public float getBatteryLevel() {
        Intent batteryIntent = Config.context.getApplicationContext().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        // Error checking that probably isn't needed but I added just in case.
        if(level == -1 || scale == -1) {
            return 50.0f;
        }

        return ((float)level / (float)scale) * 100.0f;
    }

    public String getBatteryState() {
        String returnText ;
        Intent batteryIntent = Config.context.getApplicationContext().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        if(scale == 0) {
            returnText = "OnBattery";
        }
        else if(scale != -1)
        {
            returnText = "OnCharger";
        }
        else
        {
            returnText = "Error" ;
        }
        return returnText;
    }
}
