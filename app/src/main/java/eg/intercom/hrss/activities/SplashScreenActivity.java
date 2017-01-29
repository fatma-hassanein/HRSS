package eg.intercom.hrss.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import eg.intercom.hrss.R;

import eg.intercom.hrss.activities.LoginActivity;
import eg.intercom.hrss.helpers.MyApplication;

/**
 * Created by Yaser on 8/30/2016.
 */

public class SplashScreenActivity extends Activity {
    private Thread spalshThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.splash_screen);
        final SplashScreenActivity sPlashScreen = this;
        spalshThread = new Thread(){
            @Override
            public void run() {
                try{
                    synchronized (this){
                        wait(5000);
                    }
                }catch(InterruptedException e){

                }
                finish();
                Intent i = new Intent();
                i.setClass(sPlashScreen, LoginActivity.class);
                startActivity(i);

            }
        };
        spalshThread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            synchronized (spalshThread) {
                spalshThread.notifyAll();
            }

        }



        return true;


    }

    @Override
    protected void onResume() {
        super.onResume();

        MyApplication.setCurrentActivity(this);
    }
}

//Utility.showProgressDialog(LoginActivity.this, getString(R.string.Loading), null);
//        Map<String, String> mRetrofitParams = new HashMap<>();
//        mRetrofitParams.put("username", username.getText().toString());
//        mRetrofitParams.put("password", password.getText().toString());
////        Map<String, String> mHeader = new HashMap<>();
////
////mHeader.put("User-Agent", "android");
////        mHeader.put("ch","04");
//
//d
//        Constants.httpClient = new OkHttpClient.Builder();
//
//        Constants.httpClient.addInterceptor(new RerofitInterceptor(mHeader));
//
//iii
//
//
//        new RetrofitAsynTask(0,ServerConfig.LOGIN_PATH, ServerConfig.METHOD_POST,null, mRetrofitParams
//        , this, this).execute();
//        Hager • 2 mins

//if (Utility.isProgressDialogShowing())
//        Utility.removeProgressDialog();


