package com.easydroid.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;

import android.util.Log;

import com.easydroid.domains.ResponseContainer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

public class EasyJsonTransformer {
	
	public enum GsonType { DEFAULT, EXCLUDE_TRANSIENT_FIELDS, EXCLUDE_FIELDS_WITHOUT_EXPOSE }

	public static Gson newGson(GsonType gsonType) {
		if(gsonType == GsonType.EXCLUDE_TRANSIENT_FIELDS)
			return new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
		else if(gsonType == GsonType.EXCLUDE_FIELDS_WITHOUT_EXPOSE)
			return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		else
			return new Gson();
	}

	public static String tranformObjectToJson(Object obj) {
		return tranformObjectToJson(obj, GsonType.DEFAULT);
	}

	public static String tranformObjectToJson(Object obj, GsonType gsonType) {
		return newGson(gsonType).toJson(obj);
	}
	
	public static ResponseContainer parseJson(InputStream responseStream, Class<? extends ResponseContainer> responsibleClass) throws IOException, JsonParseException {
        ResponseContainer mResponse = null;
        try {
            String responseString = EasyCommonUtils.convertStreamToString(responseStream);
            if(EasyDroid.enableLogging)
                Log.d(EasyJsonTransformer.class.getName(), responseString);
            Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
            mResponse = gson.fromJson(responseString, responsibleClass);
            responseStream.close();
            return mResponse;
        } catch (JsonParseException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

}
