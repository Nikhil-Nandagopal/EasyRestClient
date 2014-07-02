package com.easydroid.services;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.conn.ConnectTimeoutException;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.Window;

import com.easydroid.domains.RawResponseContainer;
import com.easydroid.domains.ResponseContainer;
import com.easydroid.interfaces.OnEasyServiceCompleteListener;
import com.easydroid.utils.EasyJsonTransformer;
import com.easydroid.utils.ResponseCodes;
import com.easydroid.utils.ResponseFormat;
import com.google.gson.JsonParseException;

public class EasyServiceThread extends AsyncTask<EasyServiceRequest, Void, ResponseContainer> {

    private ProgressDialog progressDialog;
    private boolean showProgressDialog;
    private OnEasyServiceCompleteListener onEasyServiceCompleteListener;
    private int requestCode;
    private boolean isCancelable = true;
    private boolean isCancelableOnTouchOutside;
    private EasyRestClient easyRestClient;
    private Context context;
    private DialogInterface.OnCancelListener onDialogCancelListener = new DialogInterface.OnCancelListener() {

        @Override
        public void onCancel(DialogInterface arg0) {
            // TODO Auto-generated method stub
            EasyServiceThread.this.cancel(true);
        }
    };

    public EasyServiceThread(OnEasyServiceCompleteListener onServiceCompleteListener, boolean showProgressDialog, Context context) {
        this.onEasyServiceCompleteListener = onServiceCompleteListener;
        this.showProgressDialog = showProgressDialog;
        this.context = context;
        if(showProgressDialog) {
            progressDialog = new ProgressDialog(context);
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.setOnCancelListener(onDialogCancelListener);
            progressDialog.setCancelable(isCancelable);
            progressDialog.setCanceledOnTouchOutside(isCancelableOnTouchOutside);
        }
    }

    public EasyServiceThread(OnEasyServiceCompleteListener onServiceCompleteListener, ProgressDialog progressDialog, Context context) {
        this.onEasyServiceCompleteListener = onServiceCompleteListener;
        this.showProgressDialog = true;
        this.progressDialog = progressDialog;
        this.context = context;
    }

    public void setOnDialogCanCelListener(DialogInterface.OnCancelListener onDialogCancelListener) {
        this.onDialogCancelListener = onDialogCancelListener;
        this.progressDialog.setOnCancelListener(onDialogCancelListener);
    }

    public boolean isCancelable() {
        return isCancelable;
    }

    public void setCancelable(boolean isCancelable) {
        this.isCancelable = isCancelable;
    }

    public boolean isCancelableOnTouchOutside() {
        return isCancelableOnTouchOutside;
    }

    public void setCancelableOnTouchOutside(boolean isCancelableOnTouchOutside) {
        this.isCancelableOnTouchOutside = isCancelableOnTouchOutside;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (showProgressDialog && progressDialog != null) {
            progressDialog.show();
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        try {
            abortThread();
        } catch(Exception e) {

        }
        if (showProgressDialog && progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
            showProgressDialog = false;
        }
    }

    public void abortThread() {
        if(easyRestClient != null)
            easyRestClient.abortHttpClient();
    }

    @Override
    protected ResponseContainer doInBackground(EasyServiceRequest... serviceRequests) {
        ResponseContainer responseContainer = null;
        try{
            EasyServiceRequest easyServiceRequest = serviceRequests[0];
            this.requestCode = easyServiceRequest.getRequestCode();
            easyRestClient = new EasyRestClient(easyServiceRequest, context);
            InputStream inputStream = easyRestClient.execute();
            if(easyServiceRequest.getResponseFormat().ordinal() == ResponseFormat.JSON.ordinal()) {
                responseContainer = EasyJsonTransformer.parseJson(inputStream, easyServiceRequest.getResponsibleClass());
            } else if(easyServiceRequest.getResponseFormat().ordinal() == ResponseFormat.RAW.ordinal()) {
                RawResponseContainer rawResponseContainer = new RawResponseContainer();
                rawResponseContainer.setInputStream(inputStream);
                responseContainer = rawResponseContainer;
            }
        } catch (ConnectTimeoutException e) {
            responseContainer = new ResponseContainer();
            responseContainer.setResponseCode(ResponseCodes.CONNECTION_TIMEOUT);
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
            responseContainer = new ResponseContainer();
            responseContainer.setResponseCode(ResponseCodes.JSON_PARSE_EXCEPTION);
        } catch (IOException e) {
            e.printStackTrace();
            responseContainer = new ResponseContainer();
            responseContainer.setResponseCode(ResponseCodes.IO_EXCEPTION);
        }
        return responseContainer;
    }

    @Override
    protected void onPostExecute(ResponseContainer responseContainer) {
        try{
            if(showProgressDialog) {
                progressDialog.cancel();
                showProgressDialog = false;
            }
            super.onPostExecute(responseContainer);
            if(responseContainer.getResponseCode().getResponseValue() == ResponseCodes.OK.getResponseValue()) { 
                onEasyServiceCompleteListener.onEasyServiceSuccess(responseContainer, requestCode);
            } else
                onEasyServiceCompleteListener.onEasyServiceError(responseContainer, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
