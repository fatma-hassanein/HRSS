package eg.intercom.hrss.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eg.intercom.hrss.R;
import eg.intercom.hrss.api.APIListener;
import eg.intercom.hrss.api.PendingRequestModel;
import eg.intercom.hrss.api.ResultPendingRequest;
import eg.intercom.hrss.api.ServerConfig;
import eg.intercom.hrss.helpers.Constants;
import eg.intercom.hrss.helpers.Utility;
import eg.intercom.hrss.retrofit.RetrofitInterceptor;
import eg.intercom.hrss.retrofit.RetrofitAsynTask;
import okhttp3.OkHttpClient;

/**
 * Created by Emad.Essam on 11/7/2016.
 */

public class ManageActivity extends AppCompatActivity implements APIListener {
    //
//    JSONArray vacReqArr = new JSONArray();
//    JSONArray perReqArr =new JSONArray() ;
//    JSONArray compReqArr =new JSONArray();
//    JSONArray misReqArr =new JSONArray();
//
//    private static int vacReqNumber = 0;
//    private static int perReqNumber= 0;
//    private static int missReqNumber= 0;
//    private static int comReqNumber= 0;
    String TAG = "ManageActivity Test";

    public ResultPendingRequest pendingRequest;

    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_manage);

        getPendingRequest();


    }


    public void getPendingRequest() {

        Log.e("eweweeeeeeeeeee", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext));
        Utility.showProgressDialog(ManageActivity.this, getString(R.string.Loading));


        Map<String, String> mRetrofitHeader = new HashMap<>();
        String TOKEN = Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext);


        mRetrofitHeader.put("token", TOKEN);
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

        Constants.httpClient.addInterceptor(new RetrofitInterceptor(mRetrofitHeader, mContext));

        new RetrofitAsynTask(0, ServerConfig.PENDING_REQUEST, ServerConfig.METHOD_GET, mRetrofitHeader, null
                , this, this).execute();

    }

    @Override
    public void onSuccess(int id, String url, String response) {
        if (Utility.isProgressDialogShowing())
            Utility.removeProgressDialog();
        eg.intercom.hrss.helpers.Log.e("person Response in main", response + "gg");
        try {
            pendingRequest = new ResultPendingRequest();
            Gson gson = new Gson();

            pendingRequest = gson.fromJson(response, ResultPendingRequest.class);
            String mResult = String.valueOf(pendingRequest.getResult());
            if (mResult.trim().equalsIgnoreCase("1")) {

                List<PendingRequestModel> vacReq = pendingRequest.getLstVacReq();
                Log.v(TAG, "onevacreq" + vacReq);
                List<PendingRequestModel> compReqs = pendingRequest.getLstCompReq();
                Log.v(TAG, "onePerReq" + compReqs);
                List<PendingRequestModel> misReqs = pendingRequest.getLstMisReq();
                Log.v(TAG, "oneCompReq" + misReqs);
                List<PendingRequestModel> perReqs = pendingRequest.getLstPerReq();
                Log.v(TAG, "oneMisReq" + perReqs);




                        /*if (oneVacReq.length()> 2 )
                            Log.v(TAG,"oneVacReq :: "+oneVacReq +"====="+vacReqArr.length());
                        if (onePerReq.length()> 2 )
                            Log.v(TAG,"onePerReq :: "+onePerReq+"====="+perReqArr.length());
                        if (oneCompReq.length()> 2 )
                            Log.v(TAG,"oneCompReq :: "+oneCompReq+"====="+compReqArr.length());
                        if (oneMisReq.length()> 2 )
                            Log.v(TAG,"oneMisReq :: "+oneMisReq+"====="+misReqArr.length());*/

                //int pendingRequestNumber = vacReqArr.length() +perReqArr.length()+compReqArr.length()+misReqArr.length();
                        /*pendingRequest = jsonObject.getString("balance");
                        Log.v(TAG, "availableBalance :: " + availableBalance);*/

                        /*pendingRequestsNumberTV.setText(pendingRequestNumber+"");
                        if(pendingRequestNumber>0){
                            pendingRequestsNumberTV.setTextColor(getResources().getColor(R.color.orange_dark));
                        }*/

            } else {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(int id, String url, String response, int responseCode) {
        Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG).show();

    }
}
