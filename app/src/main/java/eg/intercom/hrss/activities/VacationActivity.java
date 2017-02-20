package eg.intercom.hrss.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import eg.intercom.hrss.R;
import eg.intercom.hrss.activities.add.NewVacationActivity;
import eg.intercom.hrss.adapters.VacationAdapter;
import eg.intercom.hrss.adapters.ViewPagerAdapter;
import eg.intercom.hrss.api.APIListener;
import eg.intercom.hrss.api.LstVacHst;
import eg.intercom.hrss.api.ServerConfig;
import eg.intercom.hrss.api.VacationHistoryResults;
import eg.intercom.hrss.helpers.Constants;
import eg.intercom.hrss.helpers.Utility;
import eg.intercom.hrss.retrofit.RetrofitInterceptor;
import eg.intercom.hrss.retrofit.RetrofitAsynTask;
import okhttp3.OkHttpClient;


import com.google.gson.Gson;
import com.klinker.android.sliding.SlidingActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VacationActivity extends SlidingActivity implements APIListener {
    RecyclerView vacHisView;
    Context mContext;
    private RecyclerView.LayoutManager layoutManager;

    String TAG = "VacationHistory";
    private VacationAdapter vacAdapter;
    //	public LstMisReq lstMisReq;
    private ArrayList<VacationHistoryResults> vacResults;
    private ArrayList<LstVacHst> vacList;
    @Override
    public void init(Bundle savedInstanceState) {
        setTitle(R.string.vac_activity);
        enableFullscreen();
        mContext = this;

        vacList = new ArrayList<LstVacHst>();
        vacResults = new ArrayList<VacationHistoryResults>();
        setPrimaryColors(
                getResources().getColor(R.color.vac_activity),
                getResources().getColor(R.color.vac_activity_dark)
        );
        setContent(R.layout.activity_vacation);
        setFab(
                getResources().getColor(R.color.fab_activity_accent),
                R.drawable.add,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(VacationActivity.this,NewVacationActivity.class);
                        startActivity(i);
                    }
                }
        );

        setHeaderContent(R.layout.header_photo_history);


        Intent intent = getIntent();
        if (intent.getBooleanExtra(MainActivity.ARG_USE_EXPANSION, false)) {
            expandFromPoints(
                    intent.getIntExtra(MainActivity.ARG_EXPANSION_LEFT_OFFSET, 0),
                    intent.getIntExtra(MainActivity.ARG_EXPANSION_TOP_OFFSET, 0),
                    intent.getIntExtra(MainActivity.ARG_EXPANSION_VIEW_WIDTH, 0),
                    intent.getIntExtra(MainActivity.ARG_EXPANSION_VIEW_HEIGHT, 0)
            );
        }
        vacHisView = (RecyclerView) findViewById(R.id.vac_list);
        getVacationHistoy();
    }

    /**
     * Created by Yaser on 8/28/2016.
     */
    public void getVacationHistoy() {

        Log.e("eweweeeeeeeeeee", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext));
        Utility.showProgressDialog(VacationActivity.this, getString(R.string.Loading));


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

        new RetrofitAsynTask(0, ServerConfig.VACATION_HISTORY, ServerConfig.METHOD_GET, mRetrofitHeader, null
                , this, this).execute();

    }

    @Override
    public void onSuccess(int id, String url, String response) {
        if (Utility.isProgressDialogShowing())
            Utility.removeProgressDialog();
        eg.intercom.hrss.helpers.Log.e("person Response in main", response + "gg");
        try {
//			results = new LstMisReq();
            VacationHistoryResults vacationHistoryResults = new VacationHistoryResults();
            Gson gson = new Gson();

            vacationHistoryResults = gson.fromJson(response, VacationHistoryResults.class);
            String mResult = String.valueOf(vacationHistoryResults.getResult());
            if (mResult.trim().equalsIgnoreCase("1")) {

                List<LstVacHst> hstReq = vacationHistoryResults.getLstVacHst();
                Log.v(TAG, "onehstReq" + hstReq);


                vacList = (ArrayList<LstVacHst>) hstReq;
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
        vacHisView.setLayoutManager(layoutManager);
        vacAdapter = new VacationAdapter(mContext, vacList);
        vacHisView.setAdapter(vacAdapter);
    }
}
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_vacation);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.vac_toolbar);
//
//        setSupportActionBar(toolbar);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.vac_fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(VacationActivity.this, NewVacationActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//    }
//
//    public void showDate (View view){
//        DateDialog dateDialog = new DateDialog();
//        dateDialog.show(getSupportFragmentManager(),"Date");
//    }
