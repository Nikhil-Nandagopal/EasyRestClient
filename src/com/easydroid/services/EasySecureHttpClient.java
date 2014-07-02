package com.easydroid.services;

import java.io.InputStream;
import java.security.KeyStore;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;

import com.easydroid.utils.EasyDroid;

import android.content.Context;

public class EasySecureHttpClient extends DefaultHttpClient {

  final Context context;
  private InputStream inputStream;

  public EasySecureHttpClient(Context context) {
    this.context = context;
    this.inputStream = context.getResources().openRawResource(EasyDroid.BOUNCY_CASTLE_KEYSTORE_ID);
  }

  @Override protected ClientConnectionManager createClientConnectionManager() {
    SchemeRegistry registry = new SchemeRegistry();
    registry.register(
        new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    registry.register(new Scheme("https", newSslSocketFactory(), 443));
    return new SingleClientConnManager(getParams(), registry);
  }

  private SSLSocketFactory newSslSocketFactory() {
    try {
      KeyStore trusted = KeyStore.getInstance("BKS");
      try {
        trusted.load(inputStream, EasyDroid.BOUNCY_CASTLE_KEYSTORE_PASSWORD.toCharArray());
      } finally {
          inputStream.close();
      }
      return new SSLSocketFactory(trusted);
    } catch (Exception e) {
      throw new AssertionError(e);
    }
  }
}
