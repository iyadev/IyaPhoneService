package fr.iyadev.droid.iyaphoneservice.Lib;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import fr.iyadev.droid.iyaphoneservice.Config;
import fr.iyadev.droid.iyaphoneservice.Lib.MyLog;

public class JsonDialog extends AsyncTask<String, String, JSONObject> {
    //jsonreq(GET,POST)

    protected void onPostExecute(JSONObject feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
        try {
            Config.getInstance().setPhoneNumber(feed.getString("number"));
            Config.getInstance().setEnable(feed.getBoolean("enable"));
            MyLog.getInstance().enable = (feed.getBoolean("mylog"));
            MyLog.getInstance().log(feed.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject jsonArray = null;

        try {
            URL url = new URL(params[0]);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            jsonArray = new JSONObject(responseStrBuilder.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
