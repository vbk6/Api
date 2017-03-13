package com.example.vishnubk.api;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;


public class APIClient {

    private APIResultCallback callback;
    private Context context;

    public void execute(Context context, APIRequestBuilder request, APIResultCallback callback) {
        this.callback = callback;
        this.context = context;
        if (!NetworkUtil.isNetworkAvailable(context)) {
            if (callback != null) {
                callback.onNoInternet();
            }
        } else {
            new doAPITask().execute(request);
        }
    }


    public class doAPITask extends AsyncTask<APIRequestBuilder, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(APIRequestBuilder... requests) {
            OkHttpClient client = new OkHttpClient();

            try {

                Call call=client.newCall(requests[0].buildRequest());
               // requests[0].setCall(call);
                Response response = call.execute();
                try {
                    String rsp = response.body().string();
                    Log.d("NOOOH", "doInBackground: "+rsp);
                    JSONObject result = new JSONObject(rsp);
                    return result;
                } catch (JSONException e) {
                    return null;
                }
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            if (!NetworkUtil.isNetworkAvailable(context) && jsonObject == null) {
                if (callback != null) {
                    callback.onNoInternet();
                }
            } else {
                if (callback != null) {
                    callback.onFinish(jsonObject);
                }
            }
        }
    }

    public interface APIResultCallback {
        void onFinish(JSONObject object);
        void onNoInternet();
    }
}
