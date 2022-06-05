package com.example.play2gether;


import android.app.ProgressDialog;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class REST {
    //if local it is IPv4 Address
    public static String myURL = "http://192.168.0.52:5000";
    public static String JWT = "";
    public static HttpURLConnection connection = null;
    public static final int DEFAULT_BUFFER_SIZE = 8192;

    public static boolean login(String login, String password) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(myURL + "/api/Login");
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("Email", login);
                    jsonParam.put("Password", password);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    DataOutputStream os = new DataOutputStream(connection.getOutputStream());
                    os.writeBytes(jsonParam.toString());
                    os.flush();
                    os.close();

                    if (connection.getResponseCode() == 200) {
                        InputStream responseBody = connection.getInputStream();
                        JWT = convertInputStreamToString(responseBody);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!JWT.isEmpty()) {
            return true;
        }
        return false;
    }

    private static String convertInputStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
        // Java 10
        //return result.toString(StandardCharsets.UTF_8);
    }

    public static void GetActivities() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(myURL + "/api/UserActivitie/GetActivities");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Authorization","Bearer " + JWT);

                    if (connection.getResponseCode() == 200) {
                        connection.getInputStream();
                        Log.d("will see", convertInputStreamToString(connection.getInputStream()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }


}
