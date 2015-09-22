package g5.da1.da1_project;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Viktor on 21-09-2015.
 */
public class SessionManager {
    public static final String PREF_NAME = "PVCOSDA1PREF";
    public static final String KEY_UID = "uid";
    public static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final int PRIVATE_MODE = 0;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void setLoginDetails(String uid) {
        editor.putString(KEY_UID, uid);
        editor.putBoolean(KEY_IS_LOGGEDIN, true);
        editor.commit();
    }


    public String getUID() {
        return preferences.getString(KEY_UID,"nope");
    }
}