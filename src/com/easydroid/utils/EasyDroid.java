package com.easydroid.utils;

public class EasyDroid {

    public static String BASE_URL = null;
    public static int BOUNCY_CASTLE_KEYSTORE_ID = 0;
    public static String BOUNCY_CASTLE_KEYSTORE_PASSWORD = null;
    public static boolean enableLogging;
    
    public static void init(String BASE_URL, int BKS_RES_ID, String BKS_PASSWORD) {
        EasyDroid.BASE_URL = BASE_URL;
        EasyDroid.BOUNCY_CASTLE_KEYSTORE_ID = BKS_RES_ID;
        EasyDroid.BOUNCY_CASTLE_KEYSTORE_PASSWORD = BKS_PASSWORD;
    }
    
    public static void init(String BASE_URL) {
        EasyDroid.BASE_URL = BASE_URL;
        EasyDroid.BOUNCY_CASTLE_KEYSTORE_ID = 0;
        EasyDroid.BOUNCY_CASTLE_KEYSTORE_PASSWORD = null;
    }
    
}
