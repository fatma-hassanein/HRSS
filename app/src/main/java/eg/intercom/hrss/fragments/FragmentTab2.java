package eg.intercom.hrss.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eg.intercom.hrss.R;
import eg.intercom.hrss.adapters.MissionAdapter;
import eg.intercom.hrss.api.APIListener;
import eg.intercom.hrss.api.LstMisHst;
import eg.intercom.hrss.api.MissionHistoryResults;
import eg.intercom.hrss.api.ServerConfig;
import eg.intercom.hrss.helpers.Constants;
import eg.intercom.hrss.helpers.Utility;
import eg.intercom.hrss.retrofit.RetrofitAsynTask;
import eg.intercom.hrss.retrofit.RetrofitInterceptor;
import okhttp3.OkHttpClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTab2 extends Fragment implements APIListener {

    Context mContext;
    String TAG = "MissionHistory";
    RecyclerView misHisView;
    private RecyclerView.LayoutManager layoutManager;
    private MissionAdapter misAdapter;
    private ArrayList<LstMisHst> misList;

    public FragmentTab2() {
        misList = new ArrayList<LstMisHst>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext= getContext();
        View rootView = inflater.inflate(R.layout.fragment_tab2, container, false);
        misHisView = (RecyclerView) rootView.findViewById(R.id.mis_list);
        misHisView.setHasFixedSize(true);

        getMissionHistoy();

        return rootView;
    }

    public void getMissionHistoy() {

        Log.e("eweweeeeeeeeeee", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext));
        Utility.showProgressDialog(getActivity(), getString(R.string.Loading));


        Map<String, String> mRetrofitHeader = new HashMap<>();
        String TOKEN = Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext);


        mRetrofitHeader.put("token", TOKEN);
        Utility.generateRetrofitHttpHeader(getActivity());
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

        new RetrofitAsynTask(0, ServerConfig.MISSION_HISTORY, ServerConfig.METHOD_GET, mRetrofitHeader, null
                , this, getActivity()).execute();

    }

    @Override
    public void onSuccess(int id, String url, String response) {
        if (Utility.isProgressDialogShowing())
            Utility.removeProgressDialog();
        eg.intercom.hrss.helpers.Log.e("person Response in main", response + "gg");
        try {
//			results = new LstMisReq();
            MissionHistoryResults missionHistoryResults = new MissionHistoryResults();
            Gson gson = new Gson();

            missionHistoryResults = gson.fromJson(response, MissionHistoryResults.class);
            String mResult = String.valueOf(missionHistoryResults.getResult());
            if (mResult.trim().equalsIgnoreCase("1")) {

                List<LstMisHst> hstReq = missionHistoryResults.getLstPerHst();
                Log.v(TAG, "onehstReq" + hstReq);

                for(int i = 0; i < hstReq.size(); i++)
                {
                    if(!hstReq.get(i).getStatus().equals("0"))
                        misList.add(hstReq.get(i));
                }

                // misList = (ArrayList<LstMisHst>) hstReq;
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
        layoutManager = new LinearLayoutManager(mContext);
        misHisView.setLayoutManager(layoutManager);
        misAdapter = new MissionAdapter(mContext, misList);
        misHisView.setAdapter(misAdapter);
    }


}
