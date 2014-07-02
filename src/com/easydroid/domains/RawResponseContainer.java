package com.easydroid.domains;

import java.io.InputStream;

public class RawResponseContainer extends ResponseContainer {

    private InputStream inputStream;
    
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    
    public InputStream getInputStream() {
        return inputStream;
    }
    
}
