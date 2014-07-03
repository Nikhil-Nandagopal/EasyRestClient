package com.easydroid.domains;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpRequestBase;

import android.content.Context;

import com.easydroid.services.EasyServiceRequest;
import com.easydroid.utils.EasyCommonUtils;
import com.easydroid.utils.RequestMethod;

public class FormServiceRequest extends EasyServiceRequest {

    private FormRequest request;

    public FormServiceRequest(FormRequest request, RequestMethod requestMethod, Context context) {
        super(requestMethod);
        this.request = request;
    }

    @Override
    public HttpRequestBase createHttpRequest() {
        // TODO Auto-generated method stub
        try {
            UrlEncodedFormEntity urlEncodedEntity = new UrlEncodedFormEntity(request.getFormDataPairList());
            String requestString = null;
            if(isSecureConnectionRequest)
                requestString = this.SECURE_HTTP_METHOD;
            else
                requestString = this.HTTP_METHOD;
            requestString += this.url+"/"+this.path+"/"+this.method;
            if(request.getUrlRequestParams() != null) {
                requestString += EasyCommonUtils.buildEncodedQueryString(request.getUrlRequestParams());
            }
            return getHttpRequest(requestString, urlEncodedEntity);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

}
