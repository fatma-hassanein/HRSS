package eg.intercom.hrss.activities;

import android.content.Context;
import android.content.Intent;

import android.app.Activity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import eg.intercom.hrss.R;
import eg.intercom.hrss.api.APIListener;
import eg.intercom.hrss.api.ServerConfig;
import eg.intercom.hrss.helpers.Constants;
import eg.intercom.hrss.helpers.Log;
import eg.intercom.hrss.helpers.MyApplication;
import eg.intercom.hrss.helpers.Utility;
import eg.intercom.hrss.retrofit.RetrofitInterceptor;
import eg.intercom.hrss.retrofit.RetrofitAsynTask;
import okhttp3.OkHttpClient;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends Activity implements APIListener {

    String engNme, username, password, empName, mgrName, mgrCode, passText, userIDText,empPhoto,Token;
    LinearLayout loginLayout;
    public String Signout, role, gender , Signin;;
    CircleImageView person, otherPerson;
    TextView EMpName,other;
    TextInputLayout user;
    AppCompatCheckBox remember;
    public JSONObject resultObject = null;
    SharedPreferences passSharedPrefs;
    SharedPreferences.Editor editPassShared;
    Boolean saveLogin;
    Context mContext;
    Bitmap decodedByte;
    private EditText mUserView;
    private EditText mPasswordView;
    private View mProgressView, mLoginFormView;

    @Override
    protected void onResume() {
        super.onResume();

        MyApplication.setCurrentActivity(this);

        if (engNme != "" && empPhoto !="") {
            Log.e("engName", engNme);

            EMpName.setText("Welcome, " + engNme);
//            user.setHint(userID);
            person.setImageBitmap(decodedByte);

            user.setVisibility(View.INVISIBLE);
            mUserView.setText(userIDText);
            mUserView.setVisibility(View.INVISIBLE);
        } else {

            EMpName.setText(R.string.other_person);
            person.setImageResource(R.drawable.profile);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mContext = this;
        remember = (AppCompatCheckBox) findViewById(R.id.remember_Me);
        mUserView = (EditText) findViewById(R.id.email);
       mUserView.setTypeface(Utility.applyCustomFonts("Roboto-Thin.ttf",mContext));

        loginLayout = (LinearLayout) findViewById(R.id.parent);


//        populateAutoComplete();
        passText = Constants.getDataInSharedPrefrences(Constants.PASSWORD, mContext);
        userIDText = Constants.getDataInSharedPrefrences(Constants.USER_ID, mContext);
        engNme = Constants.getDataInSharedPrefrences(Constants.ENG_NAME, mContext);
        empPhoto = Constants.getDataInSharedPrefrences(Constants.USER_PHOTO, mContext);
        byte[] decodedString = Base64.decode(empPhoto, Base64.DEFAULT);
        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        person = (CircleImageView) findViewById(R.id.profile_image);
        user = (TextInputLayout) findViewById(R.id.user_name);

        person.setImageBitmap(decodedByte);
        EMpName = (TextView) findViewById(R.id.other_person);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setTypeface(Utility.applyCustomFonts("Roboto-Thin.ttf",mContext));

        EMpName.setText(R.string.other_person);
       EMpName.setTypeface(Utility.applyCustomFonts("Roboto-Thin.ttf",mContext));
// TODO I will check this later .....................
//        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//                if (id == R.id.login || id == EditorInfo.IME_NULL) {
//                    attemptLogin();
//                    return true;
//                }
//                return false;
//            }
//        });
        passSharedPrefs = getSharedPreferences("loginPrefs",MODE_PRIVATE);
        editPassShared = passSharedPrefs.edit();

        saveLogin = passSharedPrefs.getBoolean("saveLogin",false);
        if(saveLogin){
            mPasswordView.setText(passSharedPrefs.getString("password",""));
            remember.setChecked(true);

        }

//        if (remember.isChecked()) {
//            mPasswordView.setText(passText);
//            remember.setChecked(true);
//
//        } else {
//            mPasswordView.setText("");
//
//            remember.setChecked(false);
//
//        }
        AppCompatButton mLogInButton = (AppCompatButton) findViewById(R.id.email_sign_in_button);
        mLogInButton.setTypeface(Utility.applyCustomFonts("Roboto-Thin.ttf",mContext));
        mLogInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Constants.isNetworkOnline(mContext)) {

                    attemptLogin();


                } else {

                    final Snackbar snackbar = Snackbar.make(loginLayout, "Connect to internet", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {


                                    if (Constants.isNetworkOnline(mContext)) {

                                        Snackbar.make(loginLayout, "You are connected now", Snackbar.LENGTH_SHORT).show();

                                    } else {
                                        Snackbar.make(loginLayout, "You are not connected now", Snackbar.LENGTH_SHORT).show();

                                    }
                                }
                            });
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    snackbar.show();


                }
            }
        });
        other = (TextView) findViewById(R.id.other);
        other.setTypeface(Utility.applyCustomFonts("Roboto-Thin.ttf",mContext));

        other.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setVisibility(View.VISIBLE);
                user.setHint("Username");
                mPasswordView.setText("");

                EMpName.setText(R.string.other_person);
                person.setImageResource(R.drawable.profile);

                mUserView.setVisibility(View.VISIBLE);
                mUserView.setText("");
                mUserView.setEnabled(true);
                mUserView.requestFocus();


            }
        });
        otherPerson = (CircleImageView) findViewById(R.id.other_user_profile);

        otherPerson.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setVisibility(View.VISIBLE);
                user.setHint("Username");
                mPasswordView.setText("");
                person.setImageResource(R.drawable.profile);
                EMpName.setText(R.string.other_person);

                mUserView.setVisibility(View.VISIBLE);
                mUserView.setText("");
                mUserView.setEnabled(true);
                mUserView.requestFocus();

            }
        });


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    private void attemptLogin() {
//        if (mAuthTask != null) {
//            return;
//        }

        // Reset errors.
        mUserView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        username = mUserView.getText().toString();
        password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if(remember.isChecked()){

            editPassShared.putBoolean("saveLogin",true);
            editPassShared.putString("password",password);
            editPassShared.commit();

        }else {

            editPassShared.clear();
            editPassShared.commit();
        }
        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid username address.
        if (TextUtils.isEmpty(username)) {
            mUserView.setError(getString(R.string.error_field_required));
            focusView = mUserView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            // showProgress(true);
//            mAuthTask = new UserLoginTask(username, password);
//            mAuthTask.execute((Void) null);

            if (mUserView.getText().toString().equalsIgnoreCase(Constants.Demo)) {
                Log.e("DEMO On", "here");
                RetrofitAsynTask.DEMO_CONSTANT = Constants.DEMO_ON;

            } else {
                Log.e("DEMO Off", "here");
                RetrofitAsynTask.DEMO_CONSTANT = Constants.DEMO_OFF;
            }

            login();
        }
    }


    @Override
    public void onSuccess(int id, String url, String response) {
//       Utility.showProgressDialog(LoginActivity.this, getString(R.string.Loading), null);
        // showProgress(false);
        if (Utility.isProgressDialogShowing())
            Utility.removeProgressDialog();
        Log.e("success response", response + "hduer");


        if (id == 0) {

            try {
                resultObject = new JSONObject(response);

                String mResult = resultObject.getString("result");
                if (mResult.equalsIgnoreCase("1")) {
                    role = resultObject.getString("role");
                    Signin = resultObject.getString("in");
                    Signout = resultObject.getString("out");
                    gender = resultObject.getString("gender");
                    Token = resultObject.getString("token");
//                    password = resultObject.getString("password");
//                    username = resultObject.getString("userId");
                    empName = resultObject.getString("empName");
                    mgrName = resultObject.getString("mgrName");
                    mgrCode = resultObject.getString("mgrCode");

                    Constants.SaveDataInSharedPrefrences(Constants.USER_TOKEN, Token, mContext);
                    Constants.SaveDataInSharedPrefrences(Constants.USER_ID, username, mContext);
                    Constants.SaveDataInSharedPrefrences(Constants.PASSWORD, password, mContext);
                    Constants.SaveDataInSharedPrefrences(Constants.EMP_NAME, empName, mContext);
                    Constants.SaveDataInSharedPrefrences(Constants.MGR_NAME, mgrName, mContext);
                    Constants.SaveDataInSharedPrefrences(Constants.MGR_CODE, mgrCode, mContext);
                    Constants.SaveDataInSharedPrefrences(Constants.ROLE, role, mContext);
                    Constants.SaveDataInSharedPrefrences(Constants.GENDER, gender, mContext);


                    Constants.SaveDataInSharedPrefrences(Constants.IS_USER_LOGIN, "true", mContext);
                    Log.e("1111111111111111111111Token:: ", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext));

                    Intent i = new Intent(LoginActivity.this, MainActivity.class);


                    i.putExtra("in", Signin);
                    i.putExtra("out", Signout);
                    finish();
                    startActivity(i);

                    //  Toast.makeText(this, "Login Successfully", Toast.LENGTH_LONG).show();
                    ////then open activity
                } else if (mResult.equalsIgnoreCase("0")) {

                    String msg = resultObject.getString("msg");
                  //  Toast.makeText(this, "Invalid username and / or password", Toast.LENGTH_LONG).show();
                    if (msg.equalsIgnoreCase("invalid")) {

                        Toast.makeText(this, "Invalid username and / or password", Toast.LENGTH_LONG).show();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onFailure(int id, String url, String response, int responseCode) {
        // showProgress(false);

        if (Utility.isProgressDialogShowing())
            Utility.removeProgressDialog();
        Log.d("statusCode", responseCode + "ggg");
    }


    public void login() {
        Log.e("Login On", "here");
        Map<String, String> mRetrofitParams = new HashMap<>();
        mRetrofitParams.put("userId", mUserView.getText().toString());
        mRetrofitParams.put("password", mPasswordView.getText().toString());
        Constants.httpClient = new OkHttpClient.Builder();
        Map<String, String> mHeader = new HashMap<>();
        Constants.httpClient.addInterceptor(new RetrofitInterceptor(mHeader, mContext));
        new RetrofitAsynTask(0, ServerConfig.LOGIN_PATH, ServerConfig.METHOD_POST, null, mRetrofitParams
                , this, this).execute();


    }
}

