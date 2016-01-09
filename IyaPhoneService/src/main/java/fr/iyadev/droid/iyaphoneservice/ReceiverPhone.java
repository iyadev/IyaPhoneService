package fr.iyadev.droid.iyaphoneservice;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by iya on 14/12/15.
 */
public class ReceiverPhone {
    public static String getPhoneNumber()
    {
        TelephonyManager mTelephonyMgr;
        mTelephonyMgr = (TelephonyManager)Config.context.getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyMgr.getLine1Number();
    }
}
