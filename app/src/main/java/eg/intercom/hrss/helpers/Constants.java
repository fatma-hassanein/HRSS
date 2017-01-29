package eg.intercom.hrss.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import eg.intercom.hrss.R;
import okhttp3.OkHttpClient;


/**
 * Created by Emad on 10/11/2015.
 */
public class Constants {

//
public static final String Demo="demo";

    public static final String KEY_TEXT="Text";
    public static final String KEY_CHOICES="Choices";
    public static final String KEY_SELECTION="selection";
    public static final String KEY_O="O";
    public static final String KEY_R="R";
    public static final String KEY_TYPE="Type";
    public static final String KEY_STATE="State";
    public static final String KEY_CUSTOMER_SURVEY="customerSurvey";
    public static final String KEY_QUESTION_HEADER="QuestionHeader";
    public static final String KEY_ID="ID";
    public static final String KEY_ANSWER="Answer";
    public static final String KEY_ANSWERS="Answers";
    public static final String KEY_SELECTION_INDEX="selectionIndex";
    public static final String KEY_CONTENT_TYPE="Content-Type";
    public static final String APPLICATION_SLASH_JSON="application/json";


    public static final String DEAL_NUMBER="dealNumber";
    public static final String INSTRUCTION_TYPE="instructionType";
    public static final String CANCEL="cancel";
    public static final String INSTRUCTION_OPTION_ID="instructionOptionId";
    public static final String CHANGED_AMOUNT="changedAmount";
    public static final String NEW_DURATION="newDuration";
    public static final String DURATION_UNIT_v2="durationUnit";
    public static final String M="M";
    public static final String PAYMENT_INSTRUCTION="paymentInstruction";
    public static final String RENEW="Renew";
    public static final String ACCOUNT_TYPE_ID="account_type_id";
    public static final String ACCOUNT_TYPE_ID_V2="accountTypeId";



    public static final String CARD="card";
    public static final String CARD_NUMBER="cardNumber";
    public static final String CARD_KIND="cardKind";
    public static final String TIME="time";
    public static final String LOCATION="location";
    public static final String NUMBER_OF_CHEQUES="numberOfCheques";
    public static final String DELIVERY_OPTION="deliveryOption";
    public static final String RECEIVING_BRANCH_CODE="receivingBranchCode";



//CUSTOMER SURVEY
    public static final String SINGLE_SELECTION="Single Selection";  //viewType=0
    public static final String ENTRY_TEXT="Entry Text";              //viewType=1
    public static final String REQUIRED="Required";
    public static final String OPTIONAL="Optional";
    public static final String ANSWERS_LIST_LENGTH="answersListLength";

//AccountInterestRateInquiryActivity
    public static final String CURRENCY_CODE_V1="currencyCode";
    public static final String CURRENCY_CODE_V2="currency_code";
    public static final String ACCOUNT_TYPE_CODE_V1="accountTypeCode";
    public static final String ACCOUNT_TYPE_CODE_V2="account_type_code";
    public static final String ZERO="0";
    public static final String ONE="1";
    public static final String RESULT="result";
    public static final String ACCOUNT_RATES="account_rates";
    public static final String RATE="rate";
    public static final String DURATION="duration";
    public static final String DURATION_UNIT="duration_unit";
    public static final String HUNDRED_PERCENTAGE=" % ";
    public static final String SPACE=" ";
    public static final String EMPTY_TEXT="";
    public static final String DOT=".";
    public static final String ZERO_DOT_ZERO_ZERO="0.00";
    public static final String ACCOUNTS="accounts";
    public static final String CATEGORIES="categories";
    public static final String ACCOUNT_NAME="account_name";
    public static final String CATEGORY_ID="category_id";
    public static final String CATEGORY_NAME="category_name";
    public static final String CURRENCIES="currencies";
    public static final String CURRENCY_ID="currency_id";
    public static final String CURRENCY_NAME="currency_name";

//AccountNotificationsSubscriptionExecuteActivity

    public static final String LANG_EN = "en";
    public static final String LANG_AR = "ar";
    public static final String USER_TOKEN = "token";
    public static final String USER_ID = "userId";
    public static final String USER_PHOTO = "photo";

    public static final String PASSWORD = "password";
    public static final String ENG_NAME = "empEnShortName";
    public static final String EMP_NAME = "empName";
    public static final String MGR_CODE = "mgrCode";
    public static final String GENDER = "gender";
    public static final String ROLE = "role";

    public static final String MGR_NAME = "mgrName";

    public static final String HRSS = "HRSS";




    public static OkHttpClient.Builder httpClient = null;

    public static String IS_USER_LOGIN = "isUserLoggedIn";
    private static String regexStr = " ^0[0-9].*$";


    public static  String DEMO_ON="1";
    public static  String DEMO_OFF="0";
    public static boolean isNetworkOnline(Context context) {
        boolean status = false;
        try {
            // ConnectivityManager connectivity = (ConnectivityManager) context
            // .getSystemService(Context.CONNECTIVITY_SERVICE);
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null
                    && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null
                        && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;

    }

    public static boolean isEmpty(EditText et) {
        if (TextUtils.isEmpty(et.getText().toString())
                || TextUtils.isEmpty(et.getText().toString())) {
            return true;
        }
        return false;
    }

//    public static boolean isRegexValid(EditText et) {
//        if (Pattern.matches(regexStr, et.getText().toString())) {
//            // if (mMobile_et.getText().toString().matches(regexStr)){
//            return true;
//        } else {
//
//            return false;
//        }
//    }

//    public static String HandleResponseCode(int responseCode) {
//        String Usermessage = null;
//
//        switch (responseCode) {
//            case 0:
//                Usermessage = MyApplication.getAppContext().getString(R.string.general_error);
//                break;
//
//            case 500:
//                Usermessage = MyApplication.getAppContext().getString(R.string.general_error);
//                break;
//
//            case 400:
//                Usermessage = MyApplication.getAppContext().getString(R.string.general_error);
//                break;
//            case 401:
//                Usermessage = MyApplication.getAppContext().getString(R.string.general_error);
//                break;
//
//        }


//        return Usermessage;
//
//
//    }

    public static void showErrorHandlingMessageLong(Context context, String msg, int time) {
        /*final Toast toast = Toast.makeText(context , msg , Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, time);*/



    }




    public static void showSecurityDialog(String title, String msg, String buttonMsg, final Activity activity) {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, buttonMsg,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
//                        Utility.ClearSharedPrefrence();
                        activity.finish();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.startActivity(intent);
                    }
                });
        alertDialog.show();

    }
    public static void SaveDataInSharedPrefrences(String mkey,String mValue,Context mContext){
        SharedPreferences sharedPref = mContext.getSharedPreferences(Constants.HRSS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(mkey, mValue);
        editor.commit();

    }
    public static String getDataInSharedPrefrences(String mkey,Context mContext){
        SharedPreferences sharedPref = mContext.getSharedPreferences(Constants.HRSS, Context.MODE_PRIVATE);
        return   sharedPref.getString(mkey, "");


    }
    public static void showAlertDialog(String title, String msg, String buttonMsg, final Activity currentActivity, final Class backActivity) {
        AlertDialog alertDialog = new AlertDialog.Builder(currentActivity).create();
}}