package com.easydroid.services;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.MultipartEntity;

import android.content.Context;

import com.easydroid.domains.MultiPartRequest;
import com.easydroid.utils.EasyCommonUtils;
import com.easydroid.utils.RequestMethod;

public class EasyMultiPartServiceRequest extends EasyServiceRequest {

    private MultiPartRequest request;

    public EasyMultiPartServiceRequest(MultiPartRequest request, String contentType, RequestMethod requestMethod, Context context) {
        super(requestMethod, contentType);
        this.request = request;
    }

    @Override
    public HttpRequestBase createHttpRequest() {
        // TODO Auto-generated method stub
        MultipartEntity multiPartEntity = request.getMultiPartEntity();
        String requestString = null;
        if(isSecureConnectionRequest)
            requestString = this.SECURE_HTTP_METHOD;
        else
            requestString = this.HTTP_METHOD;
        requestString += this.url+"/"+this.path+"/"+this.method;
        if(request.getRequestParams() != null) {
            requestString += EasyCommonUtils.buildEncodedQueryString(request.getRequestParams());
        }
        return getHttpRequest(requestString, multiPartEntity);
    }

}
