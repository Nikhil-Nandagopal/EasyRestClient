package com.easydroid.services;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import android.content.Context;
import android.util.Log;

import com.easydroid.utils.EasyDroid;
import com.easydroid.utils.ResponseCodes;

public class EasyRestClient {

    private final String TAG = getClass().getName();
    private EasyServiceRequest easyServiceRequest;
    private HttpParams httpParameters;
    private HttpRequestBase httpRequest;
    private HttpResponse httpResponse;
    private Context context;
    public static final int MAX_CONNECTIONS = 10;
    public static final int MAX_CONNECTIONS_PERROUTE = 1;
    public static final int MAX_CONNECTION_TIMEOUT = 30000;

    public EasyRestClient(EasyServiceRequest request, Context context) {
        this.context = context;
        this.easyServiceRequest = request;
        httpParameters = new BasicHttpParams();
        httpParameters.setParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, MAX_CONNECTIONS);
        httpParameters.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, new ConnPerRouteBean(MAX_CONNECTIONS_PERROUTE));
        httpParameters.setParameter(HttpProtocolParams.USE_EXPECT_CONTINUE,false);
        httpParameters.setLongParameter(ConnManagerParams.TIMEOUT, request.getConnectionTimeout());
        HttpProtocolParams.setVersion(httpParameters, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(httpParameters, "utf8");
        HttpConnectionParams.setConnectionTimeout(httpParameters, request.getConnectionTimeout());
        HttpConnectionParams.setSoTimeout(httpParameters, request.getConnectionTimeout());
        ConnManagerParams.setTimeout(httpParameters, request.getConnectionTimeout());
    }

    public InputStream execute() throws ClientProtocolException, IOException {
        this.httpRequest = easyServiceRequest.createHttpRequest();
        if(EasyDroid.enableLogging)
            Log.d(TAG, this.httpRequest.getURI().toString());
        return getInputStream(getSecuredHttpClient().execute(this.httpRequest));
    }

    public ResponseCodes getHttpResponseCode() {
        return ResponseCodes.getEnum(httpResponse.getStatusLine().getStatusCode());
    }
    
    private InputStream getInputStream(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            try {
                return entity.getContent();
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public void abortHttpClient() {
        if(httpRequest != null)
            httpRequest.abort();
    }

    private HttpClient getSecuredHttpClient() {
        if(easyServiceRequest.isSecureConnectionRequest && EasyDroid.BOUNCY_CASTLE_KEYSTORE_ID != 0)
            return new EasySecureHttpClient(context);
        else
            return new DefaultHttpClient(httpParameters);
    }

}
