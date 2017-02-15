package eg.intercom.hrss.activities.add;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
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
import eg.intercom.hrss.retrofit.RetrofitInterceptor;
import eg.intercom.hrss.retrofit.RetrofitAsynTask;
import okhttp3.OkHttpClient;


/**
 * Created by Emad on 9/29/2016.
 */
public  class NewMissionActivity extends AppCompatActivity implements APIListener {
    public static String ARG_CLICKED;
    Button partialMisBtn , multipleMisBtn,sendBtn;
    DatePicker missionDate;
    static Button frOmBtn, tOBtn,startBtn,endBtn;
      String missionType;
    public  JSONObject resultObject=null;
    public String msg;

    Context mContext;
    String TAG = "MissionActivity Test";

    EditText remark;
    String year ,month , day, fromDate,toDate, date, remarks;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    public NewMissionActivity(){

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_mission_request);
        frOmBtn = (Button) findViewById(R.id.from);
        tOBtn = (Button) findViewById(R.id.to);
        partialMisBtn = (Button) findViewById(R.id.partial_button);
        multipleMisBtn = (Button) findViewById(R.id.multiple_button);
        startBtn = (Button) findViewById(R.id.fromDate);
        endBtn = (Button) findViewById(R.id.toDate);
        missionDate = (DatePicker) findViewById(R.id.datePicker_new_mission);
        remark = (EditText) findViewById(R.id.mis_remark);
        sendBtn = (Button) findViewById(R.id.mis_send);

       partialMisBtn.setSelected(true);
        startBtn.setVisibility(View.INVISIBLE);
        endBtn.setVisibility(View.INVISIBLE);

        missionType="1";
        mContext = this;

        Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext);


        View.OnClickListener buttonsListeners = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.partial_button){
                    partialMisBtn.setSelected(true);
                    multipleMisBtn.setSelected(false);
                    startBtn.setVisibility(View.INVISIBLE);


                    endBtn.setVisibility(View.INVISIBLE);
                    missionDate.setVisibility(View.VISIBLE);
                    frOmBtn.setVisibility(View.VISIBLE);
                    tOBtn.setVisibility(View.VISIBLE);
                    missionType = "1";
                }
                else {
                    multipleMisBtn.setSelected(true);
                    partialMisBtn.setSelected(false);
                    startBtn.setVisibility(View.VISIBLE);
                    endBtn.setVisibility(View.VISIBLE);


                    missionDate.setVisibility(View.INVISIBLE);
                    frOmBtn.setVisibility(View.INVISIBLE);
                    tOBtn.setVisibility(View.INVISIBLE);
                    missionType = "2";
                }
            }
        };
        partialMisBtn.setOnClickListener(buttonsListeners);
        multipleMisBtn.setOnClickListener(buttonsListeners);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startBtn.setPressed(true);

                showDate(v);
                ARG_CLICKED = "from";

            }
        });

        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            endBtn.setPressed(true);
                showDate(v);
                ARG_CLICKED="to";
            }
        });

        frOmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            frOmBtn.setPressed(true);
                showTime(v);
                ARG_CLICKED="from";

            }
        });


        tOBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            tOBtn.setPressed(true);
                showTime(v);
                ARG_CLICKED="to";

            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = missionDate.getYear()+"";
                month =  missionDate.getMonth()+1+"";
                month = month.length()<2 ?  "0"+month : month ;
                day = missionDate.getDayOfMonth()+"";
                day = day.length()<2 ?  "0"+day : day ;
                date = day+"/"+month+"/"+year;
                remarks= remark.getText().toString();

                fromDate=null;
                toDate= null;


                if (missionType == "1"){
                    Log.v(TAG,"multiple from date "+ frOmBtn.getText());
                    Log.v(TAG,"multiple to date "+ tOBtn.getText());
                    if(Utility.isNotNull(frOmBtn.getText().toString())){
                        fromDate = date + " " + frOmBtn.getText();
                    if(fromDate.contains("From")){

                        fromDate=null;
                    }}
                    if(Utility.isNotNull(tOBtn.getText().toString())){
                        toDate   = date + " " + tOBtn.getText();
                    if(toDate.contains("To")){

                        toDate=null;
                    }}
                }

                else {
                    Log.v(TAG,"multiple from date "+ startBtn.getText());
                    Log.v(TAG,"multiple to date "+ endBtn.getText());
                    if(Utility.isNotNull(startBtn.getText().toString())){
                        fromDate = startBtn.getText().toString() + " 00:00";
                    if(fromDate.contains("Start Date")){

                        fromDate=null;
                    }}
                    if(Utility.isNotNull(endBtn.getText().toString())){
                        toDate   = endBtn.getText().toString() + " 00:00";
                    if(toDate.contains("End Date")){

                        toDate=null;
                    }}

                }
                Log.v(TAG, "year :: " + year );
                Log.v(TAG, "month :: " + month);Log.v(TAG, "day :: " + day);
                Log.v(TAG, "date :: " + date);
                Log.v(TAG, "description :: " + remarks);
                Log.v(TAG, "R.string.com_param_date " + R.string.com_param_date + "");
                Log.v(TAG,"mission type :"+ missionType);
                Log.v(TAG,"partial from time :"+ frOmBtn.getText());
                Log.v(TAG,"partial to time "+ tOBtn.getText()) ;
                Log.v(TAG,"multiple from date "+ startBtn.getText());
                Log.v(TAG,"multiple to date "+ endBtn.getText());
                Log.v(TAG ,"final from date " +fromDate);
                Log.v(TAG ,"final to date " +toDate);

                if(Utility.isNotNull(remarks) && Utility.isNotNull(fromDate) && Utility.isNotNull(toDate)){
                    long from = Date.parse(fromDate);
                    long to = Date.parse(toDate);

                    if (from > to ){
                        new SweetAlertDialog(mContext,SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Wrong!")
                                .setContentText("Your dates are not valid!")
                                .show();
//                        Toast toast = Toast.makeText(getApplicationContext().getApplicationContext(), R.string.date_overlap, Toast.LENGTH_SHORT);
//                        toast.show();
                    }else {
                        // Instantiate Http Request Param Object
                        try {

                        getMission();
                            // Invoke RESTfull Web Service with Http parameters

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
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
                    if (!Utility.isNotNull(remarks)){
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
                }
            }
            });

    }









    public void showDate (View view){
        DateDialog dateDialog = new DateDialog();

        dateDialog.show(getSupportFragmentManager(),"Date");


    }

  public static void getMissionDate(int day, int month, int year) {


        System.out.println("dddddddd      "+ARG_CLICKED);

        if (ARG_CLICKED=="from") {
            startBtn.setText(day + "/" + month + "/" + year);
            startBtn.setPressed(false);
        } else  {
            endBtn.setText(day + "/" + month + "/" + year);
            endBtn.setPressed(false);
        }
    }
    public static  void getMissionTime (int hours, int minute) {

        String stHours  = hours+"";
        stHours = stHours.length()<2 ?  "0"+stHours : stHours ;

        String stMinute  = minute+"";
        stMinute = stMinute.length()<2 ?  "0"+stMinute : stMinute ;
        android.util.Log.v("minute","stMinute :)(:"+stMinute);
        android.util.Log.v("","ssssssss      "+ARG_CLICKED);

        if (ARG_CLICKED=="from") {
            frOmBtn.setText(stHours + ":" + stMinute);
            frOmBtn.setPressed(false);
        } else  {

            tOBtn.setText(stHours + ":" + stMinute);
            Log.e("totime",stHours + ":" + stMinute);
            tOBtn.setPressed(false);
        }
    }
    public void showTime(View view){


        TimeHandler timeHandler = new TimeHandler();
        timeHandler.show(getSupportFragmentManager(), "time");

        }

    @Override
    public void onSuccess(int id, String url, String response) {
        if(Utility.isProgressDialogShowing()==true) {
            Utility.removeProgressDialog();
            Log.e("onSuccesss response" , response);

        }else
        {
            Utility.showProgressDialog(NewMissionActivity.this, getString(R.string.Loading));

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
//
//                        Toast.makeText(getApplicationContext(), R.string.PERMISSION_WITHIN_WORK_TIME, Toast.LENGTH_SHORT).show();


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
            Utility.showProgressDialog(NewMissionActivity.this, getString(R.string.Loading));

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

//            NewPermissionActivity.getTime(hourOfDay, minute);




            NewMissionActivity.getMissionTime(hourOfDay,minute);


            //  Toast.makeText(context,"is selected: "+hourOfDay+"minute: "+minute,Toast.LENGTH_LONG).show();
        }
    }
    public static class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datedialog;


            datedialog = new DatePickerDialog(getActivity(), this,year,month,day);


            return datedialog;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Log.e("onTime set TimeHandler",monthOfYear +"found");
            int monthoFYear = monthOfYear+1;
            NewMissionActivity.getMissionDate(dayOfMonth,monthoFYear,year);
        }
    }

    public void getMission(){





        Utility.showProgressDialog(NewMissionActivity.this, getString(R.string.Loading));
        Utility.generateRetrofitHttpHeader(this);
        year = missionDate.getYear()+"";
         month =  missionDate.getMonth()+"";

        month = month.length()<2 ?  "0"+month : month ;
         day = missionDate.getDayOfMonth()+"";
        day = day.length()<2 ?  "0"+day : day ;
        date = day+"/"+month+"/"+year;
        Map<String, String> mRetrofitParams = new HashMap<>();
        mRetrofitParams.put("fromDateTime", fromDate);
        mRetrofitParams.put("toDateTime",  toDate);
        mRetrofitParams.put("permCategory", "2");
        mRetrofitParams.put("permType",missionType );
        mRetrofitParams.put("remarks",remarks);
        Map<String, String> mMissionHeader = new HashMap<>();
        // Bundle bundle = getIntent().getExtras();
        // token = bundle.getString("token");
        // Log.e("44444444444token",token);
        mMissionHeader.put("token", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));
//        mRetrofitHeader.put("userId", Constants.getDataInSharedPrefrences(Constants.USER_ID,mContext));
//        mRetrofitHeader.put("lang", Constants.getDataInSharedPrefrences(Constants.LANG,mContext));


//        resultObject=new JSONObject(response);
//
//        String mResult= resultObject.getString("result");
//        if(mResult.equalsIgnoreCase("1")){
//            openingBalance= resultObject.getString("openingBalance");
//            balance= resultObject.getString("balance");

        Log.e("mRetrofitHeader Act",mMissionHeader.size() +" ");


        Constants.httpClient = new OkHttpClient.Builder();

        Constants.httpClient.addInterceptor(new RetrofitInterceptor(mMissionHeader,mContext));

        new RetrofitAsynTask(0, ServerConfig.PERMISSION_REQUEST, ServerConfig.METHOD_POST,mMissionHeader,mRetrofitParams
                , this, this).execute();

    }

}

