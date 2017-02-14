package eg.intercom.hrss.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import eg.intercom.hrss.R;

import com.google.gson.Gson;
import com.klinker.android.sliding.SlidingActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eg.intercom.hrss.activities.add.NewPermissionActivity;
import eg.intercom.hrss.adapters.PermissionAdapter;
import eg.intercom.hrss.api.APIListener;
import eg.intercom.hrss.api.LstPerHst;
import eg.intercom.hrss.api.PermissionHistoryResults;
import eg.intercom.hrss.api.ServerConfig;
import eg.intercom.hrss.helpers.Constants;
import eg.intercom.hrss.helpers.Utility;
import eg.intercom.hrss.retrofit.RerofitInterceptor;
import eg.intercom.hrss.retrofit.RetrofitAsynTask;
import okhttp3.OkHttpClient;

public class PermissionActivity extends SlidingActivity implements APIListener {
    RecyclerView perHisView;
    Context mContext;
    String TAG = "PermissionHistory";
    private RecyclerView.LayoutManager layoutManager;
    private PermissionAdapter perAdapter;
    private ArrayList<PermissionHistoryResults> perResults;
    private ArrayList<LstPerHst> perList;

    @Override
    public void init(Bundle savedInstanceState) {
        setTitle(R.string.per_activity);
        enableFullscreen();
        mContext = this;

        perList = new ArrayList<LstPerHst>();
        perResults = new ArrayList<PermissionHistoryResults>();


        setPrimaryColors(
                getResources().getColor(R.color.fab_activity_primary),
                getResources().getColor(R.color.fab_activity_primary_dark)
        );
        setContent(R.layout.activity_permission);


        setFab(
                getResources().getColor(R.color.fab_activity_accent),
                R.drawable.add,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(PermissionActivity.this, NewPermissionActivity.class);
                        startActivity(i);
                    }
                }
        );

        Intent intent = getIntent();
        if (intent.getBooleanExtra(MainActivity.ARG_USE_EXPANSION, false)) {
            expandFromPoints(
                    intent.getIntExtra(MainActivity.ARG_EXPANSION_LEFT_OFFSET, 0),
                    intent.getIntExtra(MainActivity.ARG_EXPANSION_TOP_OFFSET, 0),
                    intent.getIntExtra(MainActivity.ARG_EXPANSION_VIEW_WIDTH, 0),
                    intent.getIntExtra(MainActivity.ARG_EXPANSION_VIEW_HEIGHT, 0)
            );
        }

        perHisView = (RecyclerView) findViewById(R.id.per_list);


        getPermissionHistoy();

    }


    public void getPermissionHistoy() {

        Log.e("eweweeeeeeeeeee", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext));
        Utility.showProgressDialog(PermissionActivity.this, getString(R.string.Loading));


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

        Constants.httpClient.addInterceptor(new RerofitInterceptor(mRetrofitHeader, mContext));

        new RetrofitAsynTask(0, ServerConfig.PERMISSION_HISTORY, ServerConfig.METHOD_GET, mRetrofitHeader, null
                , this, this).execute();

    }

    @Override
    public void onSuccess(int id, String url, String response) {
        if (Utility.isProgressDialogShowing())
            Utility.removeProgressDialog();
        eg.intercom.hrss.helpers.Log.e("person Response in main", response + "gg");
        try {
//			results = new LstMisReq();
            PermissionHistoryResults permissionHistoryResults = new PermissionHistoryResults();
            Gson gson = new Gson();

            permissionHistoryResults = gson.fromJson(response, PermissionHistoryResults.class);
            String mResult = String.valueOf(permissionHistoryResults.getResult());
            if (mResult.trim().equalsIgnoreCase("1")) {

                List<LstPerHst> hstReq = permissionHistoryResults.getLstPerHst();
                Log.v(TAG, "onehstReq" + hstReq);


                perList = (ArrayList<LstPerHst>) hstReq;
                getAdapterValue();
//                InitializeValues();

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

    }

    public void getAdapterValue() {
        layoutManager = new LinearLayoutManager(this);
        perHisView.setLayoutManager(layoutManager);
        perAdapter = new PermissionAdapter(mContext, perList);
        perHisView.setAdapter(perAdapter);
    }

    /**
     * Created by Emad.Essam on 9/27/2016.
     */


}
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_permission);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.per_toolbar);
//        setSupportActionBar(toolbar);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.per_fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(PermissionActivity.this,NewPermissionActivity.class);
//                startActivity(intent);
//            }
//        });
//
//    }