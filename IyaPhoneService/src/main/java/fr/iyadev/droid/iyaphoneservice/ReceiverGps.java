package fr.iyadev.droid.iyaphoneservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import fr.iyadev.droid.iyaphoneservice.Lib.MyLog;

/**
 * Created by iya on 01/12/15.
 */

public class ReceiverGps extends Service {

    //Get Smartphone Position
    private LocationManager locationMgr = null;
    private LocationListener onLocationChange = new LocationListener() {
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onLocationChanged(Location location) {
            MyLog.getInstance().log("GPS Position found");
            //Update position stored in InterperteCmd
            InterpreteCmd.getInstance().updatePosition(location.getLatitude(), location.getLongitude());
            //Call stop location service function
            InterpreteCmd.getInstance().callbackGPS();
        }
    };

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {

        locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000,
                0, onLocationChange);
        locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0,
                onLocationChange);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationMgr.removeUpdates(onLocationChange);
    }
}
