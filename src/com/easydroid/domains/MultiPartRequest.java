package com.easydroid.domains;

import org.apache.http.entity.mime.MultipartEntity;

public class MultiPartRequest extends Request {

    private MultipartEntity multiPartEntity;
    
    public MultipartEntity getMultiPartEntity() {
        return multiPartEntity;
    }

    public void setMultiPartEntity(MultipartEntity multiPartEntity) {
        this.multiPartEntity = multiPartEntity;
    }

    public MultiPartRequest(MultipartEntity multiPartEntity) {
        super(null);
        this.multiPartEntity = multiPartEntity;
        // TODO Auto-generated constructor stub
    }


}
