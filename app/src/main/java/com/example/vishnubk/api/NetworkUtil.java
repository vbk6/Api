package com.example.vishnubk.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Check network is Available
 *
 * @author Nooh KVM
 * @version 1.0
 * @since 27-05-2015
 */
public class NetworkUtil {
    private static final String TAG="NETWORK";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && (activeNetworkInfo.isConnected());
    }
}
