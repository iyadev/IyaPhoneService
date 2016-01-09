package fr.iyadev.droid.iyaphoneservice;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import fr.iyadev.droid.iyaphoneservice.Lib.MyLog;

import static android.provider.Telephony.Sms.Intents.getMessagesFromIntent;

/**
 * Created by iya on 01/12/15.
 */
public class ReceiverSms extends BroadcastReceiver {
    //On SMS receive send to InterpreteCmd cmd
    public static final String TAG = "ReceiverSms";

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] msgs = getMessagesFromIntent(intent);
            Config.context = context ;

        if (msgs != null) {

            for (int i = 0; i < msgs.length; i++) {
                InterpreteCmd.getInstance().cmd(msgs[i].getOriginatingAddress(),msgs[i].getMessageBody().toString());
            }
        }
    }
}