package com.easydroid.services;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

import android.util.Log;

import com.easydroid.domains.HttpDeleteWithBody;
import com.easydroid.domains.ResponseContainer;
import com.easydroid.utils.EasyCommonUtils;
import com.easydroid.utils.EasyDroid;
import com.easydroid.utils.RequestMethod;
import com.easydroid.utils.ResponseFormat;

public abstract class EasyServiceRequest {

    protected final String TAG = getClass().getName();
    protected final String HTTP_METHOD = "http://";
    protected final String SECURE_HTTP_METHOD = "https://";
    protected String url;
    protected String path="";
    protected String method="";
    protected String contentType;
    protected boolean isSecureConnectionRequest;
    protected int requestCode;
    protected int connectionTimeout;
    protected RequestMethod requestMethod;
    protected Class<? extends ResponseContainer> responsibleClass = ResponseContainer.class;
    protected ResponseFormat responseFormat = ResponseFormat.JSON;

    public EasyServiceRequest(RequestMethod requestMethod, String contentType) {
        this.url = EasyDroid.BASE_URL;
        this.connectionTimeout = EasyRestClient.MAX_CONNECTION_TIMEOUT;
        this.requestMethod = requestMethod;
        this.contentType = contentType;
    }

    public EasyServiceRequest(RequestMethod requestMethod) {
        this.url = EasyDroid.BASE_URL;
        this.connectionTimeout = EasyRestClient.MAX_CONNECTION_TIMEOUT;
        this.requestMethod = requestMethod;
    }

    private EasyServiceRequest() {

    }

    public abstract HttpRequestBase createHttpRequest();

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public HttpRequestBase getHttpRequest(String uri, HttpEntity httpEntity) {
        HttpEntityEnclosingRequestBase httpEntityEnclosingBaseRequest = null;
        switch (requestMethod) {
        case POST:
            httpEntityEnclosingBaseRequest = new HttpPost(uri);
            break;
        case PUT:
            httpEntityEnclosingBaseRequest = new HttpPut(url);
            break;
        case DELETE:
            httpEntityEnclosingBaseRequest = new HttpDeleteWithBody(uri);
            break;
        default:
            return httpEntityEnclosingBaseRequest;
        }
        httpEntityEnclosingBaseRequest.addHeader("content-type", contentType);
        httpEntityEnclosingBaseRequest.setEntity(httpEntity);
//        if(EasyDroid.enableLogging) {
//            try {
//                Log.d(TAG, EasyCommonUtils.convertStreamToString(httpEntity.getContent()));
//            } catch (IllegalStateException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
        return httpEntityEnclosingBaseRequest;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean isSecureConnectionRequest() {
        return isSecureConnectionRequest;
    }

    public void setSecureConnectionRequest(boolean isSecureConnectionRequest) {
        this.isSecureConnectionRequest = isSecureConnectionRequest;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Class<? extends ResponseContainer> getResponsibleClass() {
        return responsibleClass;
    }

    public void setResponsibleClass(
            Class<? extends ResponseContainer> responsibleClass) {
        this.responsibleClass = responsibleClass;
    }

    public ResponseFormat getResponseFormat() {
        return responseFormat;
    }

    public void setResponseFormat(ResponseFormat responseFormat) {
        this.responseFormat = responseFormat;
    }

}
