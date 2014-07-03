package com.easydroid.services;

import java.util.HashMap;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

import android.content.Context;

import com.easydroid.utils.EasyCommonUtils;
import com.easydroid.utils.RequestMethod;

public class EasyGetServiceRequest extends EasyServiceRequest {

    private HashMap<String, String> requestParams;

    public EasyGetServiceRequest(HashMap<String, String> requestParams, Context context) {
        super(RequestMethod.GET);
        this.requestParams = requestParams;
    }

    public HashMap<String, String> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(HashMap<String, String> requestParams) {
        this.requestParams = requestParams;
    }

    @Override
    public HttpRequestBase createHttpRequest() {
        // TODO Auto-generated method stub
        if(isSecureConnectionRequest)
            return new HttpGet(this.SECURE_HTTP_METHOD+this.url+"/"+this.path+"/"+this.method+EasyCommonUtils.buildEncodedQueryString(requestParams));
        else
            return new HttpGet(this.HTTP_METHOD+this.url+"/"+this.path+"/"+this.method+EasyCommonUtils.buildEncodedQueryString(requestParams));
    }

}
