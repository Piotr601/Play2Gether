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
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class REST {
    //if local it is IPv4 Address
    public static String myURL = "http://87.205.116.41:5000";
    public static String JWT = "";
    public static HttpURLConnection connection = null;
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    public static URL confirmAccount;
    public static String tempValue = "";

    // register API
    // it returns URL which you should use to activate your account
    // or null if error occured
    public static URL register(String login, String password, String name, String surname, Integer age) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(myURL + "/api/Login/Register");
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("Email", login);
                    jsonParam.put("Password", password);
                    jsonParam.put("Name",name);
                    jsonParam.put("Surname",surname);
                    jsonParam.put("Age",age);
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
                        confirmAccount = new URL(convertInputStreamToString(responseBody));
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

        if (confirmAccount != null) {
            return confirmAccount;
        }
        return null;
    }

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

    public static boolean changePassword(String oldPassword, String newPassword) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(myURL + "/api/Login/ChangePassword");
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("Email", oldPassword);
                    jsonParam.put("Password", newPassword);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Authorization","Bearer " + JWT);
                    connection.setDoOutput(true);
                    DataOutputStream os = new DataOutputStream(connection.getOutputStream());
                    os.writeBytes(jsonParam.toString());
                    os.flush();
                    os.close();

                    if (connection.getResponseCode() == 200) {
                        InputStream responseBody = connection.getInputStream();
                        tempValue = convertInputStreamToString(responseBody);
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

        if (tempValue.equals("Password has been changed!")) {
            tempValue = "";
            return true;
        }
        tempValue = "";
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
                        Log.d("Value to convert: ", convertInputStreamToString(connection.getInputStream()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

    public static void GetPlace(String placeID) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(myURL + "/api/UserPlace/GetPlace/" + placeID);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Authorization","Bearer " + JWT);

                    if (connection.getResponseCode() == 200) {
                        connection.getInputStream();
                        Log.d("Value to convert: ", convertInputStreamToString(connection.getInputStream()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static void GetAllPlaces() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(myURL + "/api/UserPlace/GetPlaces");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Authorization","Bearer " + JWT);

                    if (connection.getResponseCode() == 200) {
                        connection.getInputStream();
                        Log.d("Value to convert: ", convertInputStreamToString(connection.getInputStream()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static boolean isPremium() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(myURL + "/api/UserPremium/IsPremium");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Authorization","Bearer " + JWT);

                    if (connection.getResponseCode() == 200) {
                        tempValue = "isPremium";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (tempValue.equals("isPremium")) {
            tempValue = "";
            return true;
        }
        tempValue = "";
        return false;
    }

    public static ArrayList getUserEventArray = null;

    public static ArrayList getUserEvents() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(myURL + "/api/UserEvent/GetUserEvents");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Authorization","Bearer " + JWT);

                    if (connection.getResponseCode() == 200) {
                        InputStream is = connection.getInputStream();
                        Log.d("inpustream: ",convertInputStreamToString(connection.getInputStream()));

                        ObjectInputStream oin = new ObjectInputStream(connection.getInputStream());
                        Log.d("oin", String.valueOf(oin));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getUserEventArray;
    }

}
