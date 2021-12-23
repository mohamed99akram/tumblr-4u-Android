package com.example.tumblr4u.GeneralPurpose;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

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
        SharedPreferences userDetails = application.getSharedPreferences(
                application.getString(R.string.userDetails),
                Context.MODE_PRIVATE);
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
        SharedPreferences userDetails = application.getSharedPreferences(
                application.getString(R.string.userDetails),
                Context.MODE_PRIVATE);
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
        return context.getSharedPreferences(
                context.getString(R.string.userDetails),
                Context.MODE_PRIVATE)
                .getString(context.getString(R.string.myBlogId),
                        "234352");
    }
    /**
     *
     * */
    public static String getMyBlogId(Application application){
        return application.getSharedPreferences(
                application.getString(R.string.userDetails),
                Context.MODE_PRIVATE).getString(
                        application.getString(R.string.myBlogId),
                "234352"
        );
    }
    /**
     * Return Token of the user call this function in the ViewModel to pass data to Repository and
     * send requests
     * TODO not tested yet
     * @param application application of the ViewModel that calls this function
     *                    ViewModel needs to extend AndroidViewModel
     */
    public static String getToken(Application application) {
        return application.getSharedPreferences(
                application.getString(R.string.Token),
                Application.MODE_PRIVATE
        ).getString(application.getString(R.string.Token), "Token");
    }
}
