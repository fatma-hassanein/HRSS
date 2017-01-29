package eg.intercom.hrss.activities.add;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import eg.intercom.hrss.R;
import eg.intercom.hrss.api.APIListener;
import eg.intercom.hrss.api.ServerConfig;
import eg.intercom.hrss.helpers.Constants;
import eg.intercom.hrss.helpers.Log;
import eg.intercom.hrss.helpers.Utility;
import eg.intercom.hrss.retrofit.RerofitInterceptor;
import eg.intercom.hrss.retrofit.RetrofitAsynTask;
import okhttp3.OkHttpClient;


/**
 * Created by Emad on 9/29/2016.
 */

public  class NewPermissionActivity extends AppCompatActivity implements APIListener{
  public String msg;
     Calendar c;
    String TAG = "PermissionActivity Test";
    DatePicker perDatePicker;
   static  Button fromBtn;
   public String token;
    int hourOfDay;
    Button submit;
    int minute;
   static Button toBtn;
    String year ;
    String month ;
    String day;
    String date;
    EditText remarks;
    String remark;
    public  JSONObject resultObject=null;


    private static String ARG_CLICKED ;
    Context mContext;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_permission);
        mContext=this;
        Log.e("New Per,","herer");
        Log.e("NewPermissionActivity Token",Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext)+"hfhurhuhur");
        toBtn =  (Button)findViewById(R.id.to);
        fromBtn =  (Button)findViewById(R.id.from);
        submit = (Button) findViewById(R.id.send_per);
        remarks = (EditText) findViewById(R.id.remark);
        remarks.setImeActionLabel("Custom text", KeyEvent.KEYCODE_ENTER);
        perDatePicker = (DatePicker)findViewById(R.id.datePicker_new_permission);
       c = Calendar.getInstance();
        perDatePicker.setMaxDate(c.getTimeInMillis());

        Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext);

        toBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               toBtn.setPressed(true);


                showTime(v);
                ARG_CLICKED ="to";
                System.out.println(" on to date Clicked");


            }
        });
        fromBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            fromBtn.setPressed(true);



                showTime(v);
                ARG_CLICKED ="from";
                System.out.println(" on from date Clicked");
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                 year = perDatePicker.getYear()+"";
                 month =  perDatePicker.getMonth()+1+"";
                month = month.length()<2 ?  "0"+month : month ;
                 day = perDatePicker.getDayOfMonth()+"";
                day = day.length()<2 ?  "0"+day : day ;
                date = day+"/"+month+"/"+year;
                String fromDate = null;
                String toDate = null;
                if(Utility.isNotNull(fromBtn.getText().toString()))
                    fromDate = date + " " +fromBtn.getText().toString();

                if(fromDate.contains("From")){

                        fromDate=null;
                    }
                if(Utility.isNotNull(toBtn.getText().toString()))
                    toDate = date + " " +toBtn.getText().toString();
                if(toDate.contains("To")){

                    toDate=null;
                }

                Log.e("test",remarks.getText().toString());

                remark = remarks.getText().toString();
                Log.e("test",remark);
               Log.e(TAG, "year :: " + year );
                Log.v(TAG, "month :: " + month);
                Log.v(TAG, "day :: " + day);
                Log.v(TAG, "date :: " + date);
                Log.v(TAG,"partial from time :"+ fromDate);
                Log.v(TAG,"partial to time "+ toDate) ;
                Log.v(TAG,"remark ::" +remark);

                if(Utility.isNotNull(remark) && Utility.isNotNull(fromDate) && Utility.isNotNull(toDate) ){
                    long from = Date.parse(fromDate);
                    long to = Date.parse(toDate);
                    if (from > to ){


                        try {


                            Log.e("test", "date_overlap");
                            Log.e("test", "date_overlap");
                            new SweetAlertDialog(mContext,SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Wrong!")
                                    .setContentText(mContext.getResources().getString(R.string.date_overlap))
                                    .show();
//                            Toast.makeText(getApplicationContext(), R.string.date_overlap, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }else {
                        try {


                            setPermission();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }                    }
                }else{

                    if (!Utility.isNotNull(fromDate)) {
                        try {

                            new SweetAlertDialog(mContext,SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Wrong!")
                                    .setContentText("Start date is required!")
                                    .show();
//                            Toast.makeText(getApplicationContext(), R.string.required_from_date, Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (!Utility.isNotNull(toDate)){

                        try {
                            new SweetAlertDialog(mContext,SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Wrong!")
                                    .setContentText("End date is required!")
                                    .show();

//                            Toast.makeText(getApplicationContext(),R.string.required_to_date,Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (!Utility.isNotNull(remark)){
                        try {
                            new SweetAlertDialog(mContext,SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Wrong!")
                                    .setContentText("Description is required!")
                                    .show();

//                            Toast.makeText(getApplicationContext(),R.string.des_required,Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                       }
                }           // Instantiate Http Request Param Object

        }
        });


    }



//    public void showDate (View view){
//        DateDialog dateDialog = new DateDialog();
//        dateDialog.show(getSupportFragmentManager(),"Date");
//
//    }
    public void setPermission(){

        Utility.showProgressDialog(NewPermissionActivity.this, getString(R.string.Loading));
        Utility.generateRetrofitHttpHeader(this);
         year = perDatePicker.getYear()+"";
         month =  perDatePicker.getMonth()+1+"";
        month = month.length()<2 ?  "0"+month : month ;
         day = perDatePicker.getDayOfMonth()+"";
        day = day.length()<2 ?  "0"+day : day ;
         date = day+"/"+month+"/"+year;
        Map<String, String> mRetrofitParams = new HashMap<>();
        mRetrofitParams.put("fromDateTime", date + " " +fromBtn.getText().toString());
        mRetrofitParams.put("toDateTime",  date + " " +toBtn.getText().toString());
        mRetrofitParams.put("permCategory", "1");
        mRetrofitParams.put("permType", "121");
        mRetrofitParams.put("remarks",remark);
        Map<String, String> mPermissionHeader = new HashMap<>();
       // Bundle bundle = getIntent().getExtras();
       // token = bundle.getString("token");
       // Log.e("44444444444token",token);
        mPermissionHeader.put("token", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));
//        mRetrofitHeader.put("userId", Constants.getDataInSharedPrefrences(Constants.USER_ID,mContext));
//        mRetrofitHeader.put("lang", Constants.getDataInSharedPrefrences(Constants.LANG,mContext));


//        resultObject=new JSONObject(response);
//
//        String mResult= resultObject.getString("result");
//        if(mResult.equalsIgnoreCase("1")){
//            openingBalance= resultObject.getString("openingBalance");
//            balance= resultObject.getString("balance");

        Log.e("mRetrofitHeader Act",mPermissionHeader.size() +" ");


        Constants.httpClient = new OkHttpClient.Builder();

        Constants.httpClient.addInterceptor(new RerofitInterceptor(mPermissionHeader,mContext));

        new RetrofitAsynTask(0, ServerConfig.PERMISSION_REQUEST, ServerConfig.METHOD_POST,mPermissionHeader,mRetrofitParams
                , this, this).execute();


    }
    public void showTime(View view){
//        TimeHandler timeHandler = new TimeHandler();
//        timeHandler.show(getSupportFragmentManager(), "time");
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        Fragment prev = getSupportFragmentManager().findFragmentByTag("time");
//        if (prev != null) {
//            fragmentTransaction.remove(prev);
//        }
//        fragmentTransaction.addToBackStack(null);
        TimeHandler timeHandler = new TimeHandler();
        timeHandler.show(getSupportFragmentManager(),"time");
    }


    @Override
    public void onSuccess(int id, String url, String response) {

        if(Utility.isProgressDialogShowing()==true) {
            Utility.removeProgressDialog();
        Log.e("onSuccesss response" , response);

        }else
        {
            Utility.showProgressDialog(NewPermissionActivity.this, getString(R.string.Loading));

        }
        if(id==0){

            try {
                resultObject=new JSONObject(response);

                String mResult= resultObject.getString("result");


                if (mResult.trim().equalsIgnoreCase("1")) {
                    // isUserAuthenticate = true;

                    new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)

                            .setTitleText("Good job!")
                            .setContentText("Your request sent successfully!")
                            .show();

//                    Toast.makeText(getApplicationContext(), R.string.com_req_successfully, Toast.LENGTH_SHORT).show();






                }else if(resultObject.getString("msg")!=null){
                    msg= resultObject.getString("msg");
                     if (mResult.trim().equalsIgnoreCase("0")&& msg.trim().equalsIgnoreCase("prev_vac")) {

                        Log.v(TAG, "msg :: " + msg);
                         new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
                                 .setTitleText("Wrong!")
                                 .setContentText("Request already exists!")
                                 .show();
//                        Toast.makeText(getApplicationContext(), R.string.prev_vac, Toast.LENGTH_SHORT).show();


                    }else if (mResult.trim().equalsIgnoreCase("0")&& msg.trim().equalsIgnoreCase("PREV_PERMISSION")) {

                    msg= resultObject.getString("msg");
                        Log.v(TAG, "msg :: " + msg);
                         new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
                                 .setTitleText("Wrong!")
                                 .setContentText("Request already exists!")
                                 .show();
//                        Toast.makeText(getApplicationContext(), R.string.PREV_PERMISSION , Toast.LENGTH_SHORT).show();


                }else if (mResult.trim().equalsIgnoreCase("0")&& msg.trim().equalsIgnoreCase("MORE_THAN_ONE_HOUR_FOR_NURSING")) {

                    msg= resultObject.getString("msg");
                        Log.v(TAG, "msg :: " + msg);
                         new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                                 .setTitleText("Oops!")
                                 .setContentText("you have 4 hours in a month!")
                                 .show();
//                        Toast.makeText(getApplicationContext(), R.string.MORE_THAN_ONE_HOUR_FOR_NURSING, Toast.LENGTH_SHORT).show();

                } else if (mResult.trim().equalsIgnoreCase("0")&& msg.trim().equalsIgnoreCase("MORE_THAN_ONE_NURSING_PERMISSION_IN_ONE_DAY")) {
                    msg= resultObject.getString("msg");
                        Log.v(TAG, "msg :: " + msg);
                         new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                                 .setTitleText("Oops!")
                                 .setContentText("You exceeded the limit today!")
                                 .show();
//                        Toast.makeText(getApplicationContext(), R.string.MORE_THAN_ONE_NURSING_PERMISSION_IN_ONE_DAY , Toast.LENGTH_SHORT).show();

                }else if (mResult.trim().equalsIgnoreCase("0")&& msg.trim().equalsIgnoreCase("START_DATE_EQUAL_END_DATE")) {
                    msg= resultObject.getString("msg");
                        Log.v(TAG, "msg :: " + msg);
                         new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
                                 .setTitleText("Wrong!")
                                 .setContentText("Start date = End date!")
                                 .show();
//                        Toast.makeText(getApplicationContext(), R.string.START_DATE_EQUAL_END_DATE , Toast.LENGTH_SHORT).show();



                } else if (mResult.trim().equalsIgnoreCase("0")&& msg.trim().equalsIgnoreCase("PERMISSION_WITHIN_WORK_TIME")) {

                    msg= resultObject.getString("msg");
                      Log.v(TAG, "msg :: " + msg);
                         new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
                                 .setTitleText("Oops!")
                                 .setContentText("Permission not less than 15 minutes and within the working hours!")
                                 .show();
//                      Toast.makeText(getApplicationContext(), R.string.PERMISSION_WITHIN_WORK_TIME, Toast.LENGTH_SHORT).show();


                  } else if (mResult.trim().equalsIgnoreCase("0")&& msg.trim().equalsIgnoreCase("-100")) {
                    msg= resultObject.getString("msg");
                        Log.v(TAG, "msg :: " + msg);
                         new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
                                 .setTitleText("Wrong!")
                                 .setContentText("Your dates are not  valid!")
                                 .show();
//                        Toast.makeText(getApplicationContext(), R.string.date_overlap , Toast.LENGTH_SHORT).show();

                } else {
                    msg= resultObject.getString("msg");
                        Log.v(TAG, "msg :: " + msg);
                         new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                                 .setTitleText("Oops!")
                                 .setContentText(msg)
                                 .show();
//                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                }}

            } catch (JSONException e) {
                Log.e("excepKey", String.valueOf(e));
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(int id, String url, String response, int responseCode) {
        if(Utility.isProgressDialogShowing()==true) {
            Utility.removeProgressDialog();

        }else
        {
            Utility.showProgressDialog(NewPermissionActivity.this, getString(R.string.Loading));

        }

        if (responseCode == 404) {
            new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops!")
                    .setContentText("Requested resource not found!")
                    .show();
//                Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();

        }
        // When Http response code is '500'
        else if (responseCode == 500) {
            new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops!")
                    .setContentText("Something went wrong at server!")
                    .show();
//                Toast.makeText(getApplicationContext(), R.string.something_wrong, Toast.LENGTH_LONG).show();


        }
        // When Http response code other than 404, 500
        else {

            Log.v(TAG,"statusCode :: "+responseCode);
            new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops!")
                    .setContentText("Unexpected error occurred!")
                    .show();
//                Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();


        }
    }



    public static void getTime (int hours, int minute) {

        String stHours  = hours+"";
        stHours = stHours.length()<2 ?  "0"+stHours : stHours ;

        String stMinute  = minute+"";
        stMinute = stMinute.length()<2 ?  "0"+stMinute : stMinute ;
        android.util.Log.v("minute","stMinute :)(:"+stMinute);
        android.util.Log.v("","ssssssss      "+ARG_CLICKED);

        if (ARG_CLICKED=="from") {
            fromBtn.setText(stHours + ":" + stMinute);
            fromBtn.setPressed(false);
        } else  {
            toBtn.setText(stHours + ":" + stMinute);
            toBtn.setPressed(false);
        }
    }

    public  static class TimeHandler extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        Context context;
        TimePickerDialog timedialog;


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar calendar = Calendar.getInstance();

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);



            timedialog = new TimePickerDialog(getActivity(),this,hour,minute, DateFormat.is24HourFormat(getActivity()));



            return timedialog;
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Log.e("onTime set TimeHandler",hourOfDay +"found"+minute );
            NewPermissionActivity.getTime(hourOfDay, minute);




//            NewMissionActivity .getMissionTime(hourOfDay,minute);


            //  Toast.makeText(context,"is selected: "+hourOfDay+"minute: "+minute,Toast.LENGTH_LONG).show();
        }
    }
}