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
import eg.intercom.hrss.activities.add.NewMissionActivity;
import eg.intercom.hrss.adapters.MissionAdapter;
import eg.intercom.hrss.adapters.ViewPagerAdapter;
import eg.intercom.hrss.api.APIListener;
import eg.intercom.hrss.api.LstMisHst;
import eg.intercom.hrss.api.MissionHistoryResults;
import eg.intercom.hrss.api.ServerConfig;
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

public class MissionActivity extends SlidingActivity{
//    RecyclerView misHisView;
    Context mContext;
    String TAG = "MissionHistory";
//    private RecyclerView.LayoutManager layoutManager;
//    private MissionAdapter misAdapter;
//    private ArrayList<MissionHistoryResults> misResults;
//    private ArrayList<LstMisHst> misList;
    @Override
    public void init(Bundle savedInstanceState) {
        setTitle(R.string.miss_activity);
        enableFullscreen();
        mContext=this;
//        misList = new ArrayList<LstMisHst>();
//        misResults = new ArrayList<MissionHistoryResults>();


        setPrimaryColors(
                getResources().getColor(R.color.mis_activity_dark),
                getResources().getColor(R.color.mis_activity_dark)
        );
        setContent(R.layout.activity_mission);
        setHeaderContent(R.layout.header_photo_history);
//        setFab(
//                getResources().getColor(R.color.fab_activity_accent),
//                R.drawable.add,
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent i = new Intent(MissionActivity.this,NewMissionActivity.class);
//                        startActivity(i);
//                    }
//                }
//        );

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        Intent intent = getIntent();
        if (intent.getBooleanExtra(MainActivity.ARG_USE_EXPANSION, false)) {
            expandFromPoints(
                    intent.getIntExtra(MainActivity.ARG_EXPANSION_LEFT_OFFSET, 0),
                    intent.getIntExtra(MainActivity.ARG_EXPANSION_TOP_OFFSET, 0),
                    intent.getIntExtra(MainActivity.ARG_EXPANSION_VIEW_WIDTH, 0),
                    intent.getIntExtra(MainActivity.ARG_EXPANSION_VIEW_HEIGHT, 0)
            );
        }
//        misHisView = (RecyclerView) findViewById(R.id.mis_list);
        //getMissionHistoy();
    }
}
    /**
     * Created by Yaser on 8/28/2016.
     */

//    public void getMissionHistoy() {
//
//        Log.e("eweweeeeeeeeeee", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext));
//        Utility.showProgressDialog(MissionActivity.this, getString(R.string.Loading));
//
//
//        Map<String, String> mRetrofitHeader = new HashMap<>();
//        String TOKEN = Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext);
//
//
//        mRetrofitHeader.put("token", TOKEN);
//        Utility.generateRetrofitHttpHeader(this);
////        resultObject=new JSONObject(response);
////
////        String mResult= resultObject.getString("result");
////        if(mResult.equalsIgnoreCase("1")){
////            openingBalance= resultObject.getString("openingBalance");
////            balance= resultObject.getString("balance");
//
////    Map<String, String> mNewHeader = new HashMap<>();
////
////    mNewHeader.put("token", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));
//        Constants.httpClient = new OkHttpClient.Builder();
//
//        Constants.httpClient.addInterceptor(new RetrofitInterceptor(mRetrofitHeader, mContext));
//
//        new RetrofitAsynTask(0, ServerConfig.MISSION_HISTORY, ServerConfig.METHOD_GET, mRetrofitHeader, null
//                , this, this).execute();
//
//    }


//    @Override
//    public void onSuccess(int id, String url, String response) {
//        if (Utility.isProgressDialogShowing())
//            Utility.removeProgressDialog();
//        eg.intercom.hrss.helpers.Log.e("person Response in main", response + "gg");
//        try {
////			results = new LstMisReq();
//            MissionHistoryResults missionHistoryResults = new MissionHistoryResults();
//            Gson gson = new Gson();
//
//            missionHistoryResults = gson.fromJson(response, MissionHistoryResults.class);
//            String mResult = String.valueOf(missionHistoryResults.getResult());
//            if (mResult.trim().equalsIgnoreCase("1")) {
//
//                List<LstMisHst> hstReq = missionHistoryResults.getLstPerHst();
//                Log.v(TAG, "onehstReq" + hstReq);
//
//
//                misList = (ArrayList<LstMisHst>) hstReq;
//                getAdapterValue();
//            } else {
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void onFailure(int id, String url, String response, int responseCode) {
//
//    }
//
//    public void getAdapterValue() {
//        layoutManager = new LinearLayoutManager(this);
//        misHisView.setLayoutManager(layoutManager);
//        misAdapter = new MissionAdapter(mContext, misList);
//        misHisView.setAdapter(misAdapter);
//    }
//}
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mission);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.mis_toolbar);
//        setSupportActionBar(toolbar);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.mission_fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MissionActivity.this,NewMissionActivity.class);
//                startActivity(intent);
//            }
//        });
//
//    }
//    public void showDate (View view){
//        DateDialog dateDialog = new DateDialog();
//        dateDialog.show(getSupportFragmentManager(),"Date");
//    }
//    public void showTime(View view){
//        TimeHandler timeHandler = new TimeHandler();
//        timeHandler.show(getSupportFragmentManager(), "time");
//    }
