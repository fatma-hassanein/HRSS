package eg.intercom.hrss.helpers;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import java.util.Stack;

/**
 * Created by Emad on 10/21/2015.
 */
public class MyApplication extends Application {

    private static Context context;
    private static Activity activity;
    private SharedPreferences prefs;
    public static Stack<Class<?>> parentStack = new Stack<Class<?>>();


public static Application instance;
    public void onCreate() {
        super.onCreate();
        instance=this;

        //start service of clearing app data while clear from recent apps
//        Intent clearService = new Intent(this, ClearAppPreferencesService.class);
//        startService(clearService);

        MyApplication.context = getApplicationContext();
        MyApplication.activity = getActivity();
        prefs = getSharedPreferences(Constants.HRSS,Context.MODE_PRIVATE);

    }
    public static Context getAppContext() {
        Log.e("instanse", String.valueOf(instance.getApplicationContext()));
        return instance.getApplicationContext();
    }

    public static Activity getActivity() {
        return MyApplication.activity;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    //    MultiDex.install(this);
    }

    public  static void setCurrentActivity(Activity currentActivity){
        activity=currentActivity;

    }
}
