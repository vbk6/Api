package com.example.vishnubk.api;

import android.content.Context;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;


public class APIRequestBuilder {


    private static final String BASE_URL = "http://api.androidhive.info/contacts/";



    public APIRequestBuilder(Context context) {
    }


    public enum Type {GET, POST, PUT}

    private Type type;
    private String method;
    private FormBody.Builder body = new FormBody.Builder();
    private RequestBody requestBody;
    private boolean auth = false;
    private String userKey;
    private String langauge;
    private Call call;

//    public APIRequestBuilder(Context context) {
//        langauge = context.getString(R.string.language);
//
//    }

//
//    public APIRequestBuilder setType(Type type) {
//        this.type = type;
//        return this;
//    }
//
//
//    public APIRequestBuilder setMethod(String method) {
//        this.method = method;
//        return this;
//    }
//
//
//    public APIRequestBuilder addParameters(String key, String value) {
//        this.body.add(key, value);
//        return this;
//    }
//
//    public APIRequestBuilder addFile(String key, String value) {
//        this.body.add(key, value);
//        return this;
//    }

//    public APIRequestBuilder authenticate(Context context) {
//        auth = true;
//        userKey = UserManager.getInstance().getUserKey(context);
//        return this;
//    }


//    public APIRequestBuilder setRequestBody(RequestBody requestBody) {
//        this.requestBody = requestBody;
//        return this;
//    }
//
//    public Call getCall() {
//        return call;
//    }
//
//    public void setCall(Call call) {
//        this.call = call;
//    }
//
//    public void cancelCall() {
//        if (call != null) {
//            call.cancel();
//        }
//    }

    public Request buildRequest() {

        Request.Builder builder = new Request.Builder();
        builder.addHeader("Accept", "application/json,*/*");
        //builder.addHeader("Android-ver", String.valueOf(BuildConfig.VERSION_CODE));
        //builder.addHeader("Language", langauge);

        if (requestBody == null) {
            requestBody = this.body.build();
        }

        if (auth) {
            builder.addHeader("api_token", userKey);
        }
        builder.url(BASE_URL);
        if (type == Type.POST) {
            builder.post(requestBody);
        } else if (type == Type.PUT) {
            builder.put(requestBody);
        }
        return builder.build();
    }
}
