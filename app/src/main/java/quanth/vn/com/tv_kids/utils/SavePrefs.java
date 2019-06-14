package quanth.vn.com.tv_kids.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dcmen on 08/31/16.
 */
public class SavePrefs {
    private static final String SHARED_PREF_NAME = "TVKIDS";
    private static final String USER_NAME = "USER_NAME";
    private static final String TOKEN_GG = "TOKEN_GG";
    private static final String ID_GG = "ID_GG";
    private static final String URI_AVA = "URI_AVA";


    public static void saveUsername(Context context, String value) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mSharedPreferences.edit().putString(USER_NAME, value).apply();
    }

    public static String getUsername(Context context) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(USER_NAME, null);
    }

    public static void saveTokenGg(Context context, String value) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mSharedPreferences.edit().putString(TOKEN_GG, value).apply();
    }

    public static String getTokenGg(Context context) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(TOKEN_GG, null);
    }

    public static void saveIdGg(Context context, String value) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mSharedPreferences.edit().putString(ID_GG, value).apply();
    }

    public static String getIdGg(Context context) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(ID_GG, null);
    }
    public static void saveUriAva(Context context, String value) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mSharedPreferences.edit().putString(URI_AVA, value).apply();
    }

    public static String getUriAva(Context context) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(URI_AVA, null);
    }
    public static void clearSharePrefs(Context context) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mSharedPreferences.edit().clear().apply();
    }

}
