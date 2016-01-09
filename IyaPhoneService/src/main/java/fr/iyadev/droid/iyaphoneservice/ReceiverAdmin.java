package fr.iyadev.droid.iyaphoneservice;

/**
 * Created by iya on 01/12/15.
 */
import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReceiverAdmin extends DeviceAdminReceiver {
    @Override
    public void onDisabled(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Config.context = context;
        InterpreteCmd.getInstance().deviceAdmin("Disable");
        super.onDisabled(context, intent);
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Config.context = context;
        InterpreteCmd.getInstance().deviceAdmin("Enable");
        super.onEnabled(context, intent);
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Config.context = context;
        InterpreteCmd.getInstance().deviceAdmin("Ask for Disable");
        return super.onDisableRequested(context, intent);
    }

}