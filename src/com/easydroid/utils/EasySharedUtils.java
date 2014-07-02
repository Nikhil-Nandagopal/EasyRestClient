package com.easydroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class EasySharedUtils {
    
    private static final String DEVICE_ID_FILE = "device_file";
    private static final String DEVICE_ID_KEY = "device_id";

    public static String getDeviceId(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences(DEVICE_ID_FILE, Context.MODE_PRIVATE);
        return mPrefs.getString(DEVICE_ID_KEY, null);
    }

    public static void storeDevieId(String deviceId, Context context) {
       SharedPreferences mPrefs = context.getSharedPreferences(DEVICE_ID_FILE, Context.MODE_PRIVATE);
       mPrefs.edit().putString(deviceId, DEVICE_ID_KEY).commit(); 
    }
    
}
