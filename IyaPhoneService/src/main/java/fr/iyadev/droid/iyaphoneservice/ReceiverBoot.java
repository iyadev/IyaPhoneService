package fr.iyadev.droid.iyaphoneservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import fr.iyadev.droid.iyaphoneservice.Lib.MyLog;

/**
 * Created by iya on 01/12/15.
 */
public class ReceiverBoot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startServiceIntent = new Intent(context, MainService.class);
        context.startService(startServiceIntent);
    }
}
