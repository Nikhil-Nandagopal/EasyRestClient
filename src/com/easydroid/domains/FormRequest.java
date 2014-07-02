package com.easydroid.domains;

import java.util.List;

import org.apache.http.NameValuePair;

public class FormRequest extends Request {

    private List<NameValuePair> formDataPairList;
    
    public List<NameValuePair> getFormDataPairList() {
        return formDataPairList;
    }

    public void setFormDataPairList(List<NameValuePair> formDataPairList) {
        this.formDataPairList = formDataPairList;
    }
    
    public FormRequest(List<NameValuePair> formDataPairList) {
        super(null);
        this.formDataPairList = formDataPairList;
        // TODO Auto-generated constructor stub
    }


}
