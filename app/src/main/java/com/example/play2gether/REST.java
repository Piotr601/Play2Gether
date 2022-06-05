package com.example.play2gether;


import android.app.ProgressDialog;
import android.util.JsonReader;
import android.util.Log;
import org.json.JSONObject;
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
    public static HttpURLConnection myConnection = null;
    public static final int DEFAULT_BUFFER_SIZE = 8192;

    public static boolean login(String login, String password) {

        Thread loginThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(myURL + "/api/Login");
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("Email", login);
                    jsonParam.put("Password", password);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Content-Type","application/json");
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
                    if (myConnection != null) {
                        myConnection.disconnect();
                    }
                }
            }
        });
        loginThread.start();

        try {
            loginThread.join();
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
}
