package eg.intercom.hrss.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.klinker.android.sliding.SlidingActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import eg.intercom.hrss.R;
import eg.intercom.hrss.api.APIListener;
import eg.intercom.hrss.api.ProfileResults;
import eg.intercom.hrss.api.ServerConfig;
import eg.intercom.hrss.helpers.Constants;
import eg.intercom.hrss.helpers.Utility;
import eg.intercom.hrss.retrofit.RerofitInterceptor;
import eg.intercom.hrss.retrofit.RetrofitAsynTask;
import okhttp3.OkHttpClient;

public class PersonActivity extends SlidingActivity implements APIListener {

//    public  JSONObject jsonObject=null;

   public ProfileResults jsonObject;
    TextView empCode,arabicName,engName,birthDate,jobTitle,hiringDate,directManager,department,workEmail;
    CircleImageView profImage;
    EditText personalEmail,homePhone,socialStatus,country,city,postalCode,address,arabicShort,engShort,officeExtension,mobile1,mobile2;
    Bitmap decodedByte;
    String mProfileImage;

   private Context mContext;
    @Override
    public void init(Bundle savedInstanceState) {
        setContent(R.layout.activity_person);
        mContext= this;
        setTitle("Profile Details");
        enableFullscreen();
        mProfileImage = Constants.getDataInSharedPrefrences(Constants.USER_PHOTO, mContext);
        byte[] decodedString = Base64.decode(mProfileImage, Base64.DEFAULT);
        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        setImage(decodedByte);
        Log.e("7777777777777777",Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));
        Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext);

        empCode = (TextView)findViewById(R.id.emp_code);
        arabicName = (TextView)findViewById(R.id.arabic_name);
        birthDate = (TextView)findViewById(R.id.birth_date);
        jobTitle = (TextView)findViewById(R.id.job_title);
        hiringDate = (TextView)findViewById(R.id.hiring_date);
        directManager = (TextView)findViewById(R.id.direct_manager);
        department  = (TextView)findViewById(R.id.department_name);
        workEmail = (TextView)findViewById(R.id.work_email);
        engName = (TextView)findViewById(R.id.eng_name);
        arabicShort  = (EditText)findViewById(R.id.arabic_short);
        engShort  = (EditText)findViewById(R.id.eng_short);
        officeExtension  = (EditText)findViewById(R.id.office_extension);
        mobile1 = (EditText)findViewById(R.id.mobile1);
        mobile2  = (EditText)findViewById(R.id.mobile2);
        personalEmail = (EditText)findViewById(R.id.personal_email);
        homePhone = (EditText)findViewById(R.id.home_phone);
        socialStatus = (EditText)findViewById(R.id.social_status);
        country = (EditText)findViewById(R.id.country);
        city = (EditText)findViewById(R.id.city);
        postalCode = (EditText)findViewById(R.id.postal_code);
        address = (EditText)findViewById(R.id.address);


        getProfileDetails();
    }

public void getProfileDetails(){
Log.e("eweweeeeeeeeeee",Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));
    Utility.showProgressDialog(PersonActivity.this, getString(R.string.Loading));


        Map<String, String> mRetrofitHeader = new HashMap<>();
       String TOKEN = Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext);


        mRetrofitHeader.put("token",TOKEN);
        Utility.generateRetrofitHttpHeader(this);
//        resultObject=new JSONObject(response);
//
//        String mResult= resultObject.getString("result");
//        if(mResult.equalsIgnoreCase("1")){
//            openingBalance= resultObject.getString("openingBalance");
//            balance= resultObject.getString("balance");

//    Map<String, String> mNewHeader = new HashMap<>();
//
//    mNewHeader.put("token", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));
    Constants.httpClient = new OkHttpClient.Builder();

    Constants.httpClient.addInterceptor(new RerofitInterceptor(mRetrofitHeader,mContext));

    new RetrofitAsynTask(0, ServerConfig.PROFILE_DETAIL, ServerConfig.METHOD_GET,mRetrofitHeader, null
            , this, this).execute();

}

    @Override
    public void onSuccess(int id, String url, String response) {

        if (Utility.isProgressDialogShowing())
            Utility.removeProgressDialog();
        eg.intercom.hrss.helpers.Log.e("person Response in main",response +"gg");
        try {
            jsonObject = new ProfileResults();
            Gson gson = new Gson();

            jsonObject =gson.fromJson(response,ProfileResults.class);
            String mResult= String.valueOf(jsonObject.getResult());


            if(mResult.equalsIgnoreCase("1")){
                eg.intercom.hrss.helpers.Log.e("person Response in main",response +"gg");


                empCode.setText(String.valueOf(jsonObject.getEmpCode()));
                arabicName.setText(jsonObject.getEmpArName());
                birthDate.setText(jsonObject.getBirthDate());
                jobTitle.setText(jsonObject.getJobTitle());
                hiringDate.setText(jsonObject.getEmploymentDate());
                directManager.setText(jsonObject.getMngrEnName());
                department.setText(jsonObject.getDeptEnName());
                workEmail.setText(jsonObject.getWorkMail());
                engName.setText(jsonObject.getPersonalMail());
                arabicShort.setText(jsonObject.getEmpArShortName());
                engShort.setText(jsonObject.getEmpEnShortName());
                officeExtension.setText(jsonObject.getExt());
                mobile1.setText(jsonObject.getMobile1());
                mobile2.setText(jsonObject.getMobile2());
                personalEmail.setText(jsonObject.getPersonalMail());
                homePhone.setText(jsonObject.getHomePhone());
                socialStatus.setText(jsonObject.getSocialStatus());
                country.setText(String.valueOf(jsonObject.getCountry()));
                city.setText(String.valueOf(jsonObject.getCity()));
                postalCode.setText(jsonObject.getPostalCode());
                address.setText(jsonObject.getHomeAddress());
                String empEnName = jsonObject.getEmpEnName();
                String[] separated = empEnName.split(" ");
                String test = separated[0];
                String last = separated[separated.length-1];
                empEnName = test+" "+last;
                Constants.SaveDataInSharedPrefrences(Constants.ENG_NAME,empEnName,mContext);
                byte[] decodedString = Base64.decode(String.valueOf(jsonObject.getPhoto()), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                profImage = (CircleImageView) findViewById(R.id.profile_image);

                profImage.setImageBitmap(decodedByte);
                Constants.SaveDataInSharedPrefrences(Constants.USER_PHOTO,mProfileImage,mContext);

            }


        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onFailure(int id, String url, String response, int responseCode) {
        Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_LONG).show();
    }




}
