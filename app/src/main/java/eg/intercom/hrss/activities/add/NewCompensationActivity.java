package eg.intercom.hrss.activities.add;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import eg.intercom.hrss.R;
import eg.intercom.hrss.api.APIListener;
import eg.intercom.hrss.api.ServerConfig;
import eg.intercom.hrss.helpers.Constants;
import eg.intercom.hrss.helpers.Utility;
import eg.intercom.hrss.retrofit.RerofitInterceptor;
import eg.intercom.hrss.retrofit.RetrofitAsynTask;
import okhttp3.OkHttpClient;


/**
 * Created by Emad on 9/29/2016.
 */

public  class NewCompensationActivity extends AppCompatActivity implements APIListener {
    DatePicker compDatePicker;
    Calendar c;
    String TAG = "Compensation Test";
    private SweetAlertDialog sd;
    Context mContext;
    Button comSubmit;
    public String msg;
    String year ;
    String month ;
    String descrip;
    String day;
    String date;
    public  JSONObject resultObject=null;

    EditText remarks;
    String remark;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
    public NewCompensationActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.new_compensation);
         remarks = (EditText) findViewById(R.id.comp_remark);
        remarks.setImeActionLabel("Custom text", KeyEvent.KEYCODE_ENTER);
        comSubmit = (Button) findViewById(R.id.comp_send);
        compDatePicker = (DatePicker) findViewById(R.id.datePicker_new_compensation);

        c = Calendar.getInstance();

        compDatePicker.setMaxDate(c.getTimeInMillis());
        comSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                year = compDatePicker.getYear()+"";
                 month =  compDatePicker.getMonth()+1+"";
                 day = compDatePicker.getDayOfMonth()+"";
                 date = day+"/"+month+"/"+year;
                descrip= remarks.getText().toString();
                Log.v("year :: ", "year :: " + year );
                Log.v("month ::", "month :: " + month);
                Log.v("day", "day :: " + day);
                Log.v("date", "date :: " + date);
                Log.v("description", "description :: " + descrip);
                Log.v("R.string.com_param_date", "R.string.com_param_date " + R.string.com_param_date + "");

                if(Utility.isNotNull(descrip)){

                    try {


                       setCompensation();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }else{
                    new SweetAlertDialog(mContext,SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Wrong!")
                            .setContentText("Description is required!")
                            .show();


//                        Toast.makeText(getApplicationContext(),R.string.des_required,Toast.LENGTH_SHORT).show();


                }
            }
        });



    }

    @Override
    public void onSuccess(int id, String url, String response) {
        if(Utility.isProgressDialogShowing()==true) {
            Utility.removeProgressDialog();

        }else
        {
            Utility.showProgressDialog(NewCompensationActivity.this, getString(R.string.Loading));

        }

        if (id == 0) {

            try {
                resultObject = new JSONObject(response);

                String mResult = resultObject.getString("result");

                Log.e("tokkkkkkkkkkkkken",Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));
                if (mResult.trim().equalsIgnoreCase("1")) {
                    // isUserAuthenticate = true
                     new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Good job!")
                            .setContentText("Your request sent successfully!")
                            .show();

//                        Toast.makeText(getApplicationContext(), R.string.com_req_successfully, Toast.LENGTH_LONG).show();


////                    getFragmentManager().beginTransaction().replace(R.id.contentFrame, Fragment.instantiate(getActivity().getApplicationContext(), Fragments.ONE.getFragment())).commit();
//
//                        /*
//                        Intent mainActivity = new Intent(getActivity(),MainActivity.class);
//                        mainActivity.putExtra("strJson",stIncomingJson);
//                        startActivity(mainActivity);*/

                }else if(resultObject.getString("msg")!=null){
                    msg = resultObject.getString("msg");
                    if (mResult.trim().equalsIgnoreCase("0") && msg.trim().equalsIgnoreCase("NOT_HOLIDAY")) {


                        Log.v(TAG, "msg :: " + msg);
                        new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Wrong!")
                                .setContentText("You can't pick a working day!")
                                .show();
//                        Toast.makeText(getApplicationContext(), R.string.not_holiday, Toast.LENGTH_LONG).show();


                } else if (mResult.trim().equalsIgnoreCase("0") && msg.trim().equalsIgnoreCase("prev_vac")) {

                        Log.v(TAG, "msg :: " + msg);
                        new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Wrong!")
                                .setContentText("Request already exists!")
                                .show();
//                        Toast.makeText(getApplicationContext(), R.string.prev_vac, Toast.LENGTH_LONG).show();

                } else if (mResult.trim().equalsIgnoreCase("0") && msg.trim().equalsIgnoreCase("-100")) {


                        Log.v(TAG, "msg :: " + msg);
                        new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Wrong!")
                                .setContentText("Your dates are not  valid!")
                                .show();
//                        Toast.makeText(getApplicationContext(), R.string.date_overlap, Toast.LENGTH_SHORT).show();

                } else {

                        Log.v(TAG, "msg :: " + msg);
                        new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Wrong!")
                                .setContentText(msg)
                                .show();
//                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();


                }}


            } catch (JSONException e) {

                new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops!")
                        .setContentText("Server side error!")
                        .show();
//                Toast.makeText(getApplicationContext(), "Error occurred [Server's JSON might be invalid]!", Toast.LENGTH_LONG).show();
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
            Utility.showProgressDialog(NewCompensationActivity.this, getString(R.string.Loading));

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


    public void setCompensation(){


    Utility.showProgressDialog(NewCompensationActivity.this, getString(R.string.Loading));
    Utility.generateRetrofitHttpHeader(this);
    year = compDatePicker.getYear()+"";
    month =  compDatePicker.getMonth()+1+"";
    day = compDatePicker.getDayOfMonth()+"";
    date = day+"/"+month+"/"+year;
    descrip= remarks.getText().toString();
    Map<String, String> mRetrofitParams = new HashMap<>();
    mRetrofitParams.put("dateTime", date);
    mRetrofitParams.put("remarks",remark);
    mRetrofitParams.put("employeeName", "EmpName");
    mRetrofitParams.put("managerCode", "1521");
    mRetrofitParams.put("managerName","MgrName");
    Map<String, String> mCompensatiionHeader = new HashMap<>();
    mCompensatiionHeader.put("token", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));
    Log.e("mRetrofitHeader Act",mCompensatiionHeader.size() +" ");
        Log.e("HeaderToken",Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));
    Constants.httpClient = new OkHttpClient.Builder();

    Constants.httpClient.addInterceptor(new RerofitInterceptor(mCompensatiionHeader,mContext));

    new RetrofitAsynTask(0, ServerConfig.COMPENSATION_REQUEST, ServerConfig.METHOD_POST,mCompensatiionHeader,mRetrofitParams
            , this, this).execute();



}

//
//    public void showDate (View view){
//        DateDialog dateDialog = new DateDialog();
//
//        dateDialog.show(getSupportFragmentManager(),"Date");
//
//    }
}