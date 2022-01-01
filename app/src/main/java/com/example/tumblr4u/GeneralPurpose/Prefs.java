package com.example.tumblr4u.GeneralPurpose;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.tumblr4u.R;

/**
 * This class gives access to SharedPreferences
 */
public class Prefs {
    /**
     * Store the Token, it should be called in login or signUp
     *
     * @param application the application object of the calling ViewModel you can use
     *                    getApplication() to get it inside ViewModel, and you need the ViewModel to
     *                    extend AndroidViewModel
     * @param token       user Token to be stored
     */
    public static void storeToken(Application application, String token) {
        SharedPreferences userDetails = PreferenceManager.getDefaultSharedPreferences(application);
        SharedPreferences.Editor edit = userDetails.edit();
        edit.putString(application.getString(R.string.Token), token);
        edit.apply();
    }

    /**
     * Store active BlogId, -> stored while signup? dashboard?
     *
     * @param application the application object of the calling ViewModel you can use
     *                    getApplication() to get it inside ViewModel, and you need the ViewModel to
     *                    extend AndroidViewModel
     * @param myBlogId    user blog id to be stored
     */
    public static void storeMyBlogId(Application application, String myBlogId) {
        SharedPreferences userDetails = PreferenceManager.getDefaultSharedPreferences(application);
        SharedPreferences.Editor edit = userDetails.edit();
        edit.putString(application.getString(R.string.myBlogId), myBlogId);
        edit.apply();
    }

    /**
     * Return current BlogId
     *
     * @param context pass the context from Adapters, Activities or fragments where you call this
     *                method
     */
    public static String getMyBlogId(Context context) {

        SharedPreferences userDetails = PreferenceManager.getDefaultSharedPreferences(context);
        return userDetails.getString(context.getString(R.string.myBlogId),"");
    }
    /**
     * get my blog Id from sharedPreferences
     * Call this function from ViewModel and pass the application to it
     * @param application the application provided from ViewModel - needs to make ViewModel extend
     *                    AndroidViewModel
     * */
    public static String getMyBlogId(Application application){
        SharedPreferences userDetails = PreferenceManager.getDefaultSharedPreferences(application);
        return userDetails.getString(application.getString(R.string.myBlogId),"");
    }
    /**
     * Return Token of the user call this function in the ViewModel to pass data to Repository and
     * send requests
     * TODO not tested yet
     * @param application application of the ViewModel that calls this function
     *                    ViewModel needs to extend AndroidViewModel
     */
    public static String getToken(Application application) {
        SharedPreferences userDetails = PreferenceManager.getDefaultSharedPreferences(application);
        return userDetails.getString(application.getString(R.string.Token),"");
    }
    /**
     * Store my Blog Name
     * @param application application object provided from ViewModel
     * @param myBlogName value to be stored in SharedPreferences
     * */
    public static void storeMyBlogName(Application application, String myBlogName) {
        SharedPreferences userDetails = PreferenceManager.getDefaultSharedPreferences(application);
        SharedPreferences.Editor edit = userDetails.edit();
        edit.putString(application.getString(R.string.myBlogName), myBlogName);
        edit.apply();
    }
    /**
     * Get blog name
     * @param application application object provided from ViewModel
     * */
    public static String getMyBlogName(Application application){
        SharedPreferences userDetails = PreferenceManager.getDefaultSharedPreferences(application);
        return userDetails.getString(application.getString(R.string.myBlogName),"");
    }
}
