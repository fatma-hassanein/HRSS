package eg.intercom.hrss.activities.add;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
import info.hoang8f.android.segmented.SegmentedGroup;
import okhttp3.OkHttpClient;


/**
 * Created by Emad on 9/29/2016.
 */


public class NewVacationActivity extends AppCompatActivity implements APIListener, RadioGroup.OnCheckedChangeListener {
//    static Button startBtn;
//    static Button endBtn;
    String TAG = "VacationActivity Test";
    Context mContext;
    String fromDate;
    String vacSubType;
    SegmentedGroup segmented;
    private RadioGroup vacGroup;
    private RadioButton vacType;

    public JSONObject resultObject = null;
    public String msg;
    String checkstartDate;
    String toDate;
    int selectedID;
    String mgrName;
    String mgrCode;
    String empName;
    static TextView fromT, toT;
    Button submitBtn;
    String spinnerText;
    public static String ARG_CLICKED;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_vacation);

        mContext = this;
//        startBtn = (Button) findViewById(R.id.start_date);
//        endBtn = (Button) findViewById(R.id.end_date);
        fromT = (TextView) findViewById(R.id.from_text);
        fromT.setTypeface(Utility.applyCustomFonts("Roboto-Black.ttf", mContext));
        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        vacGroup = (RadioGroup) findViewById(R.id.vac_group);
        toT = (TextView) findViewById(R.id.to_text);
        toT.setTypeface(Utility.applyCustomFonts("Roboto-Black.ttf", mContext));
        toT.setText(currentDateTimeString);
        fromT.setText(currentDateTimeString);

        fromT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(v);
                ARG_CLICKED = "from";
            }
        });
        toT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(v);
                ARG_CLICKED = "to";
            }
        });
        empName = Constants.getDataInSharedPrefrences(Constants.EMP_NAME, mContext);
        mgrCode = Constants.getDataInSharedPrefrences(Constants.MGR_CODE, mContext);
        mgrName = Constants.getDataInSharedPrefrences(Constants.MGR_NAME, mContext);
//        startBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startBtn.setPressed(true);
//
//                showDate(v);
//                ARG_CLICKED = "from";
//
//            }
//        });
//        endBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                endBtn.setPressed(true);
//
//                showDate(v);
//                ARG_CLICKED = "to";
//
//            }
//        });
        selectedID = vacGroup.getCheckedRadioButtonId();

//        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner3);
//        ArrayAdapter<CharSequence> adapter2;
//
//        adapter2 = ArrayAdapter.createFromResource(this, R.array.type_2, android.R.layout.simple_spinner_item);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner1.setAdapter(adapter2);
//        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //  Toast.makeText(getBaseContext(),parent.getItemIdAtPosition(position)+"is selected",Toast.LENGTH_LONG).show();
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        submitBtn = (Button) findViewById(R.id.send_vac);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat simFormat = new SimpleDateFormat("dd/mm/yyyy");

                vacType = (RadioButton) findViewById(selectedID);
                spinnerText = vacType.getText().toString();
                //spinnerText = String.valueOf(spinner1.getItemAtPosition(position));
                Log.e("SpinnerTest", spinnerText);
                if (spinnerText.equalsIgnoreCase("Normal")) {

                    vacSubType = "1";
                    Log.e("vacSubType1", vacSubType);

                } else if (spinnerText.equalsIgnoreCase("Casual")) {

                    vacSubType = "2";
                    Log.e("vacSubType2", vacSubType);

                } else if (spinnerText.equalsIgnoreCase("Rest Earned")) {

                    vacSubType = "3";
                    Log.e("vacSubType3", vacSubType);


                } else {

                    vacSubType = "1";
                    Log.e("vacSubTypeELSE", vacSubType);

                }
                fromDate = null;
                toDate = null;

//                if (Utility.isNotNull(startBtn.getText().toString()))
//                    fromDate = startBtn.getText().toString();

                if (Utility.isNotNull(fromT.getText().toString()))
                    fromDate = fromT.getText().toString();

//                    checkstartDate = startBtn.getText().toString();
//                if (fromDate.equalsIgnoreCase("Start Date")) {
//
//                    fromDate = null;
//                }
                if (Utility.isNotNull(toT.getText().toString()))
                    toDate = toT.getText().toString();
//                if (toDate.equalsIgnoreCase("End Date")) {
//
//                    toDate = null;
//                }
                Log.v(TAG, "multiple from date " + fromT.getText());
                Log.v(TAG, "multiple to date " + toT.getText());
                if (Utility.isNotNull(fromDate) && Utility.isNotNull(toDate)) {
                    Date from = null;
                    Date to = null;
                    try {
                        from = simFormat.parse(fromDate);
                        to = simFormat.parse(toDate);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (from.getTime() > to.getTime()) {
                        new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Wrong!")
                                .setContentText("Your dates are not valid!")
                                .show();
//                        Toast toast = Toast.makeText(getApplicationContext().getApplicationContext(), R.string.date_overlap, Toast.LENGTH_SHORT);
//                        toast.show();
                    } else {
                        // Instantiate Http Request Param Object
                        try {

                            getVacation();
                            // Invoke RESTful Web Service with Http parameters

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {

                    if (!Utility.isNotNull(fromDate)) {
                        try {

                            new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Wrong!")
                                    .setContentText("Start date is required!")
                                    .show();
//                            Toast.makeText(getApplicationContext(), R.string.required_from_date, Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (!Utility.isNotNull(toDate)) {

                        try {

                            new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Wrong!")
                                    .setContentText("End date is required!")
                                    .show();
//                            Toast.makeText(getApplicationContext(),R.string.required_to_date,Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }


            }
        });
//segmented.setOnCheckedChangeListener(this);
    }

    public void showDate(View view) {
        DateDialog dateDialog = new DateDialog();
        dateDialog.show(getSupportFragmentManager(), "Date");

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.button21:
                Toast.makeText(this, "One", Toast.LENGTH_SHORT).show();

                break;
            case R.id.button22:
                Toast.makeText(this, "two", Toast.LENGTH_SHORT).show();

                break;
            case R.id.button31:
                Toast.makeText(this, "three", Toast.LENGTH_SHORT).show();

                break;


        }
    }

    //    public void showTime(View view){
////        TimeHandler timeHandler = new TimeHandler();
////        timeHandler.show(getSupportFragmentManager(), "time");
//    }
    public static class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datedialog;


            datedialog = new DatePickerDialog(getActivity(), this, year, month, day);


            return datedialog;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Log.e("onTime set TimeHandler", dayOfMonth + "found");
            int monthoFYear = monthOfYear + 1;
            NewVacationActivity.getVacationDate(dayOfMonth, monthoFYear, year);
        }
    }

    @Override
    public void onSuccess(int id, String url, String response) {
        if (Utility.isProgressDialogShowing() == true) {
            Utility.removeProgressDialog();
            Log.e("onSuccesss response", response);

        } else {
            Utility.showProgressDialog(NewVacationActivity.this, getString(R.string.Loading));

        }
        if (id == 0) {

            try {
                resultObject = new JSONObject(response);

                String mResult = resultObject.getString("result");


                if (mResult.trim().equalsIgnoreCase("1")) {
                    // isUserAuthenticate = true;

                    new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Good job!")
                            .setContentText("Your request sent successfully!")
                            .show();

//                    Toast.makeText(getApplicationContext(), R.string.com_req_successfully, Toast.LENGTH_SHORT).show();


                } else if (resultObject.getString("msg") != null) {
                    msg = resultObject.getString("msg");
                    if (mResult.trim().equalsIgnoreCase("0") && msg.trim().equalsIgnoreCase("prev_vac")) {

                        Log.v(TAG, "msg :: " + msg);
                        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Wrong!")
                                .setContentText("Request already exists!")
                                .show();
//                        Toast.makeText(getApplicationContext(), R.string.prev_vac, Toast.LENGTH_SHORT).show();


                    } else if (mResult.trim().equalsIgnoreCase("0") && msg.trim().equalsIgnoreCase("PREV_VACATION")) {

                        msg = resultObject.getString("msg");
                        Log.v(TAG, "msg :: " + msg);
                        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Wrong!")
                                .setContentText("Request already exists!")
                                .show();
//                        Toast.makeText(getApplicationContext(), R.string.PREV_PERMISSION , Toast.LENGTH_SHORT).show();


                    } else if (mResult.trim().equalsIgnoreCase("0") && msg.trim().equalsIgnoreCase("START_DATE_EQUAL_END_DATE")) {
                        msg = resultObject.getString("msg");
                        Log.v(TAG, "msg :: " + msg);
                        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Wrong!")
                                .setContentText("Start date = End date!")
                                .show();

//                        Toast.makeText(getApplicationContext(), R.string.START_DATE_EQUAL_END_DATE , Toast.LENGTH_SHORT).show();


                    } else if (mResult.trim().equalsIgnoreCase("0") && msg.trim().equalsIgnoreCase("PERMISSION_WITHIN_WORK_TIME")) {

                        msg = resultObject.getString("msg");
                        Log.v(TAG, "msg :: " + msg);
                        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Oops!")
                                .setContentText("Permission not less than 15 minutes and within the working hours!")
                                .show();
//
//                        Toast.makeText(getApplicationContext(), R.string.PERMISSION_WITHIN_WORK_TIME, Toast.LENGTH_SHORT).show();


                    } else if (mResult.trim().equalsIgnoreCase("0") && msg.trim().equalsIgnoreCase("-100")) {
                        msg = resultObject.getString("msg");
                        Log.v(TAG, "msg :: " + msg);
                        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Wrong!")
                                .setContentText("Your dates are not  valid!")
                                .show();
//                        Toast.makeText(getApplicationContext(), R.string.date_overlap , Toast.LENGTH_SHORT).show();

                    } else {
                        msg = resultObject.getString("msg");
                        Log.v(TAG, "msg :: " + msg);
                        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops!")
                                .setContentText(msg)
                                .show();
//                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                    }
                }

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
        if (Utility.isProgressDialogShowing() == true) {
            Utility.removeProgressDialog();

        } else {
            Utility.showProgressDialog(NewVacationActivity.this, getString(R.string.Loading));

        }

        if (responseCode == 404) {
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops!")
                    .setContentText("Requested resource not found!")
                    .show();
//                Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();

        }
        // When Http response code is '500'
        else if (responseCode == 500) {
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops!")
                    .setContentText("Something went wrong at server!")
                    .show();
//                Toast.makeText(getApplicationContext(), R.string.something_wrong, Toast.LENGTH_LONG).show();


        }
        // When Http response code other than 404, 500
        else {

            Log.v(TAG, "statusCode :: " + responseCode);
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops!")
                    .setContentText("Unexpected error occurred!")
                    .show();
//                Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();


        }
    }

    public static void getVacationDate(int day, int month, int year) {

        System.out.println("dddddddd      " + ARG_CLICKED);

        if (ARG_CLICKED == "from") {
            fromT.setText(day + "/" + month + "/" + year);

//            startBtn.setText(day + "/" + month + "/" + year);
//            startBtn.setPressed(false);
        } else {
            toT.setText(day + "/" + month + "/" + year);

//            endBtn.setText(day + "/" + month + "/" + year);
//            endBtn.setPressed(false);
        }
    }

    public void getVacation() {


        Utility.showProgressDialog(NewVacationActivity.this, getString(R.string.Loading));
        Utility.generateRetrofitHttpHeader(this);

        Map<String, String> mRetrofitParams = new HashMap<>();
        mRetrofitParams.put("fromDateTime", fromDate);
        mRetrofitParams.put("toDateTime", toDate);
        mRetrofitParams.put("vacCategory", "1");
        mRetrofitParams.put("vacType", "1");
        mRetrofitParams.put("vacSubType", vacSubType);
        mRetrofitParams.put("employeeName", empName);
        mRetrofitParams.put("managerCode", mgrCode);
        mRetrofitParams.put("managerName", mgrName);

        Map<String, String> mVacationHeader = new HashMap<>();
        // Bundle bundle = getIntent().getExtras();
        // token = bundle.getString("token");
        // Log.e("44444444444token",token);
        mVacationHeader.put("token", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext));
//        mRetrofitHeader.put("userId", Constants.getDataInSharedPrefrences(Constants.USER_ID,mContext));
//        mRetrofitHeader.put("lang", Constants.getDataInSharedPrefrences(Constants.LANG,mContext));


//        resultObject=new JSONObject(response);
//
//        String mResult= resultObject.getString("result");
//        if(mResult.equalsIgnoreCase("1")){
//            openingBalance= resultObject.getString("openingBalance");
//            balance= resultObject.getString("balance");

        Log.e("mRetrofitHeader Act", mVacationHeader.size() + " ");


        Constants.httpClient = new OkHttpClient.Builder();

        Constants.httpClient.addInterceptor(new RetrofitInterceptor(mVacationHeader, mContext));

        new RetrofitAsynTask(0, ServerConfig.VACATION_REQUEST, ServerConfig.METHOD_POST, mVacationHeader, mRetrofitParams
                , this, this).execute();


    }


}


//
//else if (mResult.trim().equalsIgnoreCase("0")&& msg.trim().equalsIgnoreCase("MORE_THAN_ONE_HOUR_FOR_NURSING")) {
//
//        msg= resultObject.getString("msg");
//        Log.v(TAG, "msg :: " + msg);
//        new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
//        .setTitleText("Oops!")
//        .setContentText("you have 4 hours in a month!")
//        .show();
////                        Toast.makeText(getApplicationContext(), R.string.MORE_THAN_ONE_HOUR_FOR_NURSING, Toast.LENGTH_SHORT).show();
//
//        } else if (mResult.trim().equalsIgnoreCase("0")&& msg.trim().equalsIgnoreCase("MORE_THAN_ONE_NURSING_PERMISSION_IN_ONE_DAY")) {
//        msg= resultObject.getString("msg");
//        Log.v(TAG, "msg :: " + msg);
//        new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
//        .setTitleText("Oops!")
//        .setContentText("You exceeded the limit today!")
//        .show();
//                      Toast.makeText(getApplicationContext(), R.string.MORE_THAN_ONE_NURSING_PERMISSION_IN_ONE_DAY , Toast.LENGTH_SHORT).show();
//
//        }