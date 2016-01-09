package fr.iyadev.droid.iyaphoneservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import fr.iyadev.droid.iyaphoneservice.Lib.JsonDialog;
import fr.iyadev.droid.iyaphoneservice.Lib.MyLog;


/**
 * Created by iya on 01/12/15.
 */
public class MainService  extends Service {
    private Context actualcontext = this;

    @Override
    public void onCreate() {
        MyLog.getInstance().log("MainService CREATE");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Config.context = this;
        MyLog.getInstance().log("MainService START");
        //new JsonDialog().execute("https://www.iyadev.fr/test.json?client="+ReceiverPhone.getPhoneNumber() );
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Config.context = this;
        MyLog.getInstance().log("MainService STOP");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}
