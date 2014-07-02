package com.easydroid.interfaces;

import com.easydroid.domains.ResponseContainer;

public interface OnEasyServiceCompleteListener {

   public void onEasyServiceSuccess(ResponseContainer response, int requestCode);
   public void onEasyServiceError(ResponseContainer response, int requestCode);

}