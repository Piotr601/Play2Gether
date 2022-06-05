package com.example.play2gether;


import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class REST {
    //if local it is IPv4 Address
    public static String myURL = "http://192.168.0.52:5000";
    public static URL url;
    public static String JWT = "";
    public static HttpURLConnection myConnection = null;

    public static String login(String login, String password) {
        String result = "";
        try {
            url = new URL(myURL+"/api/Login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("Email",login);
            jsonParam.put("Password", password);

            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            DataOutputStream os = new DataOutputStream(connection.getOutputStream());
            os.writeBytes(jsonParam.toString());
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            Log.d("REST responseCode", String.valueOf(responseCode));
            Log.d("REST Result: ",connection.getResponseMessage());
            Log.d("REST Result: ",result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (myConnection != null) {
                myConnection.disconnect();
            }
        }

        Log.d("REST Failed: ",result);
        return result;
    }
}
