package quanth.vn.com.tv_kids.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dcmen on 08/31/16.
 */
public class SharedPreferenceHelper {
    private static final String SHARED_PREF_NAME = "TVKIDS";
    private static SharedPreferenceHelper mInstance;
    private SharedPreferences mSharedPreferences;

    private SharedPreferenceHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferenceHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreferenceHelper(context);
        }
        return mInstance;
    }

    public void set(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public String get(String key) {
        return mSharedPreferences.getString(key, null);
    }

    public void clearSharePrefs(){
        mSharedPreferences.edit().clear().apply();
    }

}
