package com.easydroid.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

public class EasyCommonUtils {

    private static String DEVICE_ID;
    
    public static String convertStreamToString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String streamLine = null;
        try {
            while ((streamLine = reader.readLine()) != null) {
                stringBuilder.append(streamLine + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String buildEncodedQueryString(
            HashMap<String, String> requestParams) {
        String queryString = "?";
        if (requestParams == null) {
            return null;
        }
        Iterator<Entry<String, String>> it = requestParams.entrySet()
                .iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pairs = (Map.Entry <String, String>) it.next();
            try {
                queryString += URLEncoder.encode(pairs.getKey().toString(),
                        "UTF-8")
                        + "="
                        + URLEncoder.encode(pairs.getValue().toString(),
                                "UTF-8") + "&";
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (queryString.length() > 0)
            queryString = queryString.substring(0, queryString.length() - 1);
        return queryString;
    }

    // return a cached unique ID for each device
    public static String getDeviceID(Context context) {
        if (DEVICE_ID == null) {
            DEVICE_ID = EasySharedUtils.getDeviceId(context);
        }

        if (DEVICE_ID == null) {
            DEVICE_ID = generateID(context);
            if (DEVICE_ID != null) {
                EasySharedUtils.storeDevieId(DEVICE_ID, context);
            }
        }
        return DEVICE_ID;
    }

    // generate a unique ID for each device
    // use available schemes if possible / generate a random signature instead
    private static String generateID(Context context) {
        // use the ANDROID_ID constant, generated at the first device boot
        String deviceId = Secure.getString(context.getContentResolver(),
                Secure.ANDROID_ID);
        // in case known problems are occured
        if (deviceId == null) {
            // get a unique deviceID like IMEI for GSM or ESN for CDMA phones
            // don't forget:
            // <uses-permission
            // android:name="android.permission.READ_PHONE_STATE" />
            deviceId = ((TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();

            // if nothing else works, generate a random number
            if (deviceId == null) {
                Random tmpRand = new Random();
                deviceId = String.valueOf(tmpRand.nextLong());
            }
        }
        // any value is hashed to have consistent format
        return getHash(deviceId);
    }

    // generates a SHA-1 hash for any string
    public static String getHash(String stringToHash) {

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] result = null;

        try {
            result = digest.digest(stringToHash.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        for (byte b : result) {
            sb.append(String.format("%02X", b));
        }

        String messageDigest = sb.toString();
        return messageDigest;
    }

}
