package com.easydroid.domains;

import java.util.HashMap;
import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

public class Request implements Parcelable {

	protected transient HashMap<String, String> urlRequestParams;

	public HashMap<String, String> getUrlRequestParams() {
		return urlRequestParams;
	}

	public void setUrlRequestParams(HashMap<String, String> requestParams) {
		this.urlRequestParams = requestParams;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int parcelableFlags) {
		final int N = urlRequestParams.size();
		dest.writeInt(N);
		if (N > 0) {
			for (Map.Entry<String, String> entry : urlRequestParams.entrySet()) {
				dest.writeString(entry.getKey());
				dest.writeString(entry.getValue());
			}
		}
	}

	public static final Creator<Request> CREATOR = new Creator<Request>() {
		public Request createFromParcel(Parcel source) {
			return new Request(source);
		}
		public Request[] newArray(int size) {
			return new Request[size];
		}
	};

	private Request(Parcel source) {
		urlRequestParams = new HashMap<String, String>();
		final int N = source.readInt();

		for (int i = 0; i < N; i++) {
			String key = source.readString();
			String val = source.readString();
			urlRequestParams.put(key, val);
		}
	}

	public Request(HashMap<String, String> requestParams) {
		// TODO Auto-generated constructor stub
	    this.urlRequestParams = requestParams;
	}
	
	public Request() {
        // TODO Auto-generated constructor stub
    }

}
