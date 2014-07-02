package com.easydroid.services;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import android.content.Context;

import com.easydroid.domains.Request;
import com.easydroid.utils.EasyCommonUtils;
import com.easydroid.utils.EasyJsonTransformer;
import com.easydroid.utils.EasyJsonTransformer.GsonType;
import com.easydroid.utils.RequestMethod;

public class EasyJsonServiceRequest extends EasyServiceRequest {

    private Request request;
    
    public EasyJsonServiceRequest(Request request, RequestMethod requestMethod, Context context) {
        super(requestMethod, "application/sjon");
        this.request = request;
    }

    @Override
    public HttpRequestBase createHttpRequest() {
        // TODO Auto-generated method stub
        try {
            StringEntity jsonEntity = new StringEntity(EasyJsonTransformer.tranformObjectToJson(request, GsonType.DEFAULT));
            String requestString = null;
            if(isSecureConnectionRequest)
                requestString = this.SECURE_HTTP_METHOD;
            else
                requestString = this.HTTP_METHOD;
            requestString += this.url+"/"+this.path+"/"+this.method;
            if(request.getRequestParams() != null) {
                requestString += EasyCommonUtils.buildEncodedQueryString(request.getRequestParams());
            }
            return getHttpRequest(requestString, jsonEntity);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }        
    }

}
