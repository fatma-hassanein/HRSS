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

import eg.intercom.hrss.activities.add.NewCompensationActivity;
import eg.intercom.hrss.adapters.CompensationAdapter;
import eg.intercom.hrss.api.APIListener;
import eg.intercom.hrss.api.CompensationHistoryResults;
import eg.intercom.hrss.api.LstCompHst;
import eg.intercom.hrss.api.ServerConfig;
import eg.intercom.hrss.helpers.Constants;
import eg.intercom.hrss.helpers.Utility;
import eg.intercom.hrss.retrofit.RetrofitInterceptor;
import eg.intercom.hrss.retrofit.RetrofitAsynTask;
import okhttp3.OkHttpClient;

public class CompensationDaysActivity extends SlidingActivity implements APIListener{
    RecyclerView compHisView;
    Context mContext;
    String TAG = "CompensationHistory";
    private RecyclerView.LayoutManager layoutManager;
    private CompensationAdapter compAdapter;
    //	public LstMisReq lstMisReq;
    private ArrayList<CompensationHistoryResults> compResults;
    private ArrayList<LstCompHst> compList;
    @Override
    public void init(Bundle savedInstanceState) {
        setTitle(R.string.com_activity);
        enableFullscreen();
        mContext = this;

        compList = new ArrayList<LstCompHst>();
        compResults = new ArrayList<CompensationHistoryResults>();
        setPrimaryColors(
                getResources().getColor(R.color.comp_activity),
                getResources().getColor(R.color.comp_activity_dark)
        );
        setContent(R.layout.activity_compensation_days);
        setFab(
                getResources().getColor(R.color.fab_activity_accent),
                R.drawable.add,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(CompensationDaysActivity.this,NewCompensationActivity.class);
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
        compHisView = (RecyclerView) findViewById(R.id.comp_list);
        getCompensationHistoy();

    }

    /**
     * Created by Yaser on 8/28/2016.
     */
    public void getCompensationHistoy() {

        Log.e("eweweeeeeeeeeee", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext));
        Utility.showProgressDialog(CompensationDaysActivity.this, getString(R.string.Loading));


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

        new RetrofitAsynTask(0, ServerConfig.COMPENSATION_HISTORY, ServerConfig.METHOD_GET, mRetrofitHeader, null
                , this, this).execute();

    }

    @Override
    public void onSuccess(int id, String url, String response) {
        if (Utility.isProgressDialogShowing())
            Utility.removeProgressDialog();
        eg.intercom.hrss.helpers.Log.e("person Response in main", response + "gg");
        try {
//			results = new LstMisReq();
            CompensationHistoryResults compensationHistoryResults = new CompensationHistoryResults();
            Gson gson = new Gson();

            compensationHistoryResults = gson.fromJson(response, CompensationHistoryResults.class);
            String mResult = String.valueOf(compensationHistoryResults.getResult());
            if (mResult.trim().equalsIgnoreCase("1")) {

                List<LstCompHst> hstReq = compensationHistoryResults.getLstCompHst();
                Log.v(TAG, "onehstReq" + hstReq);


                compList = (ArrayList<LstCompHst>) hstReq;
                getAdapterValue();
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
        compHisView.setLayoutManager(layoutManager);
        compAdapter = new CompensationAdapter(mContext, compList);
        compHisView.setAdapter(compAdapter);
    }
}
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_compensation_days);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.com_toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.com_fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CompensationDaysActivity.this,NewCompensationActivity.class);
//                startActivity(intent);
//            }
//        });
//
//    }
//    public void showDate (View view){
//        DateDialog dateDialog = new DateDialog();
//        dateDialog.show(getSupportFragmentManager(),"Date");
//    }
