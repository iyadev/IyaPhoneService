package fr.iyadev.droid.iyaphoneservice;

import android.content.Intent;
import android.telephony.SmsManager;
import fr.iyadev.droid.iyaphoneservice.Lib.MyLog;

/**
 * Created by iya on 01/12/15.
 */
public class InterpreteCmd {

private static InterpreteCmd _instance;
private String lastCmdReceive;
private Double lastLatitude;
private Double lastLongitude;
private Intent locationIntent;
private String fromContact;

        //Singleton
        public static InterpreteCmd getInstance()
        {
            if(_instance == null)
            {
                _instance = new InterpreteCmd();
            }
            return _instance;
        }

        //Main of this class
        public void cmd(String from, String message)
        {
            if(Config.getInstance().getPhoneNumber().matches("\\+[0-9]+") ||  Config.getInstance().getPhoneNumber().length() == 0  ) {
                //Save information
                fromContact = from;
                lastCmdReceive = message;
                if ((from.equals(Config.getInstance().getPhoneNumber())  & message.contains("#IS#") )
                        || ( Config.getInstance().getPhoneNumber().length() == 0 & message.contains("#IS#set") ) )

                {
                    MyLog.getInstance().log("SMS RECEIVE "  + from + ";" +message);
                    String msg = "";
                    switch (message) {
                        //Position -> Return GPS position separed with ;
                        case "#IS#set":
                            setPhoneNumber(from);
                            break;
                        //Position -> Return GPS position separed with ;
                        case "#IS#position":
                            startGPS();
                            break;
                        //Position -> Return GPS position separed with ;
                        case "#IS#system":
                            systemInfo();
                            break;
                        //Defaut return invalidecmd
                        default:
                            msg += "#IR#invalide_cmd";
                            sendSms(fromContact, msg);
                    }
                }
            }
        }
        private void setPhoneNumber(String from)
        {
            Config.getInstance().setPhoneNumber(from);
            String msg = "";
            msg += "#IR#SET;"+from;
            sendSms(fromContact, msg);
        }

        ////////////////////////////////////////// GPS
        //Save GPS position
        public void updatePosition(Double lat, Double lon)
        {
            lastLatitude = lat;
            lastLongitude = lon;
        }
        //Start gps 1st step
        public void startGPS()
        {
            //Start Location Service
            locationIntent = new Intent(Config.getInstance().context, ReceiverGps.class);
            Config.getInstance().context.startService(locationIntent);
        }
        //GPS anwser
        public void callbackGPS()
        {
            String msg = "" ;
            //Stop Location Service
            Config.getInstance().context.stopService(locationIntent);
            msg += "#IR#GPS;" + lastLatitude + ";" + lastLongitude;
            sendSms(fromContact, msg);
            //Reset for the last time
            lastLatitude = 0.00;
            lastLongitude = 0.00;
        }
        ////////////////////////////////////////// GPS

        ///////////////////////////////////////// Systeminfo
        private void systemInfo() {
            String msg = "";
            msg += "#IR#SYSTEM;battery:" + ReceiverSystemInfo.getInstance().getBatteryLevel() + ":"
                    + ReceiverSystemInfo.getInstance().getBatteryState();
            sendSms(fromContact, msg);
        }

        /////////////////////////////////////// Syteminfo
        // Ask Help
        public void help()
        {
            fromContact = Config.getInstance().getPhoneNumber() ;
            sendSms(fromContact, "#IR#Help");
            startGPS();
        }

        ///////////////////////////////////// Inform AdminDevice state change
        //
        public void deviceAdmin(String action)
        {
            sendSms(Config.getInstance().getPhoneNumber(), "#IR#" + action);
        }
        //Send a sms
        private void sendSms(String from, String msg)
        {
            //Send only if var is not null
            if(from.isEmpty() == false && msg != null && from.length() >= 10)
            {
                SmsManager.getDefault().sendTextMessage(from, null, msg, null, null);
            }
        }

}
