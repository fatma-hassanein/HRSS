package eg.intercom.hrss.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eg.intercom.hrss.R;
import eg.intercom.hrss.adapters.ListAdapter;
import eg.intercom.hrss.api.APIListener;
import eg.intercom.hrss.api.ListViewSwipeGesture;
import eg.intercom.hrss.api.LstCompReq;
import eg.intercom.hrss.api.LstMisReq;
import eg.intercom.hrss.api.LstPerReq;
import eg.intercom.hrss.api.LstVacReq;
import eg.intercom.hrss.api.PendingRequestModel;
import eg.intercom.hrss.api.ResultPendingRequest;
import eg.intercom.hrss.api.ServerConfig;
import eg.intercom.hrss.helpers.Constants;
import eg.intercom.hrss.helpers.Utility;
import eg.intercom.hrss.retrofit.RerofitInterceptor;
import eg.intercom.hrss.retrofit.RetrofitAsynTask;
import okhttp3.OkHttpClient;

public class SwipeActivity extends ActionBarActivity implements APIListener,SwipeRefreshLayout.OnRefreshListener  {
	Context mContext;
	String TAG = "ManageActivity Test";

	private ListView cmn_list_view;
	private ListAdapter listAdapter;
	private SwipeRefreshLayout swipeRefreshLayout;
	private ArrayList<PendingRequestModel> results;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe);

		mContext=this;

		cmn_list_view	=	(ListView) findViewById(R.id.cmn_list_view);
		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.manager_refresh_layout);
		swipeRefreshLayout.setOnRefreshListener(this);

		swipeRefreshLayout.post(new Runnable() {
									@Override
									public void run() {
										swipeRefreshLayout.setRefreshing(true);
										getPendingRequest();
									}
								}
		);

		final ListViewSwipeGesture touchListener = new ListViewSwipeGesture(
				cmn_list_view, swipeListener, this);
		touchListener.SwipeType	=	ListViewSwipeGesture.Double;    //Set two options at background of list item
		
		cmn_list_view.setOnTouchListener(touchListener);
		
		
	}

	@Override
	public void onRefresh() {
		getPendingRequest();
	}


	public void getPendingRequest(){

		Log.e("eweweeeeeeeeeee", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));
		Utility.showProgressDialog(SwipeActivity.this, getString(R.string.Loading));

		swipeRefreshLayout.setRefreshing(true);

		Map<String, String> mRetrofitHeader = new HashMap<>();
		String TOKEN = Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext);
		mRetrofitHeader.put("token",TOKEN);
		Utility.generateRetrofitHttpHeader(this);
		Constants.httpClient = new OkHttpClient.Builder();
		Constants.httpClient.addInterceptor(new RerofitInterceptor(mRetrofitHeader,mContext));

		new RetrofitAsynTask(0, ServerConfig.PENDING_REQUEST, ServerConfig.METHOD_GET,mRetrofitHeader, null
				, this, this).execute();

	}

	private void InitializeValues() {

		listAdapter	= new ListAdapter(mContext, results);
		cmn_list_view.setAdapter(listAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.swipe, menu);
		return true;
	}
	
	ListViewSwipeGesture.TouchCallbacks swipeListener = new ListViewSwipeGesture.TouchCallbacks() {

		@Override
		public void FullSwipeListView(int position) {

			// TODO Auto-generated method stub
			//Toast.makeText(getApplicationContext(),"Action_2", Toast.LENGTH_SHORT).show();
			PendingRequestModel item = (PendingRequestModel) listAdapter.getItem(position);

			Log.v(item.getRefNo(),"Ref No");

			Map<String, String> mRetrofitHeader = new HashMap<>();
			String TOKEN = Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext);

			mRetrofitHeader.put("token",TOKEN);
			Utility.generateRetrofitHttpHeader(SwipeActivity.this);

			Constants.httpClient = new OkHttpClient.Builder();
			Constants.httpClient.addInterceptor(new RerofitInterceptor(mRetrofitHeader, mContext));

			if(item.getRequestType().equals("v")){

				String url = ServerConfig.VACATION + item.getRefNo() + ServerConfig.ACCEPT;

				new RetrofitAsynTask(ServerConfig.VAcceptID, url, ServerConfig.METHOD_GET, mRetrofitHeader, null
						, SwipeActivity.this, SwipeActivity.this).execute();
			}
			else {
				Map<String, String> mRetrofitParams = new HashMap<>();
				String url = "";

				if (item.getRequestType().equals("p") || item.getRequestType().equals("m")) {

					if (item.getRequestType().equals("p"))
					{
					 mRetrofitParams.put("perCategory", "1");
					 url = 	ServerConfig.PERMISSION + item.getRefNo() + ServerConfig.ACCEPT;
					}
					else
					{
					 mRetrofitParams.put("perCategory", "2");
					 url = 	ServerConfig.MISSION + item.getRefNo() + ServerConfig.ACCEPT;
					}

					mRetrofitParams.put("mgrCode", Constants.getDataInSharedPrefrences(Constants.MGR_CODE,mContext));
					mRetrofitParams.put("mgrName", Constants.getDataInSharedPrefrences(Constants.MGR_NAME,mContext));
				}
				else{
					if (item.getRequestType().equals("c")){
						mRetrofitParams.put("mgrName", Constants.getDataInSharedPrefrences(Constants.MGR_NAME,mContext));
						url = 	ServerConfig.COMPENSATION + item.getRefNo() + ServerConfig.ACCEPT;
					}

				}

				new RetrofitAsynTask(ServerConfig.AcceptID, url, ServerConfig.METHOD_POST, mRetrofitHeader, mRetrofitParams
						, SwipeActivity.this, SwipeActivity.this).execute();
			}
		}

		@Override
		public void HalfSwipeListView(int position) {
			// TODO Auto-generated method stub
			//Toast.makeText(getApplicationContext(),"Action_1", Toast.LENGTH_SHORT).show();

			PendingRequestModel item = (PendingRequestModel) listAdapter.getItem(position);

			Log.v(item.getRefNo(),"Ref No");

			Map<String, String> mRetrofitHeader = new HashMap<>();
			String TOKEN = Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext);

			mRetrofitHeader.put("token",TOKEN);
			Utility.generateRetrofitHttpHeader(SwipeActivity.this);

			Constants.httpClient = new OkHttpClient.Builder();
			Constants.httpClient.addInterceptor(new RerofitInterceptor(mRetrofitHeader, mContext));

			Map<String, String> mRetrofitParams = new HashMap<>();
			String url = "";

			if(item.getRequestType().equals("v")){

				url = ServerConfig.VACATION + item.getRefNo() + ServerConfig.REJECT;
				mRetrofitParams.put("reason", "Work Issues --> to be changed");
			}
			else {

				if (item.getRequestType().equals("p") || item.getRequestType().equals("m")) {

					if (item.getRequestType().equals("p"))
					{
						mRetrofitParams.put("perCategory", "1");
						url = 	ServerConfig.PERMISSION + item.getRefNo() + ServerConfig.REJECT;
					}
					else
					{
						mRetrofitParams.put("perCategory", "2");
						url = 	ServerConfig.MISSION + item.getRefNo() + ServerConfig.REJECT;
					}

					mRetrofitParams.put("rejectReason", "Work Issues --> to be changed");
					mRetrofitParams.put("mgrName", Constants.getDataInSharedPrefrences(Constants.MGR_NAME,mContext));
				}
				else{
					if (item.getRequestType().equals("c")){
						mRetrofitParams.put("mgrName", Constants.getDataInSharedPrefrences(Constants.MGR_NAME,mContext));
						mRetrofitParams.put("reason", "Work Issues --> to be changed");
						url = 	ServerConfig.COMPENSATION + item.getRefNo() + ServerConfig.REJECT;

					}

				}
			}

			new RetrofitAsynTask(ServerConfig.RejectID, url, ServerConfig.METHOD_POST, mRetrofitHeader, mRetrofitParams
					, SwipeActivity.this, SwipeActivity.this).execute();
		}

		@Override
		public void LoadDataForScroll(int count) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onDismiss(ListView listView, int[] reverseSortedPositions) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(),"Delete", Toast.LENGTH_SHORT).show();
			for(int i:reverseSortedPositions){
				results.remove(i);
				listAdapter.notifyDataSetChanged();
			}
		}

		@Override
		public void OnClickListView(int position) {
			// TODO Auto-generated method stub
//			startActivity(new Intent(getApplicationContext(),TestActivity.class));
			Toast.makeText(getApplicationContext(),"New Activity", Toast.LENGTH_SHORT).show();


		}
		
	};

	@Override
	public void onSuccess(int id, String url, String response) {

		if(id == 0) {
			if (Utility.isProgressDialogShowing())
				Utility.removeProgressDialog();
			eg.intercom.hrss.helpers.Log.e("person Response in main", response + "gg");
			try {
				ResultPendingRequest resultPendingRequest = new ResultPendingRequest();
				Gson gson = new Gson();

				resultPendingRequest = gson.fromJson(response, ResultPendingRequest.class);
				String mResult = String.valueOf(resultPendingRequest.getResult());

				if (mResult.trim().equalsIgnoreCase("1")) {

					List<PendingRequestModel> vacReq = resultPendingRequest.getLstVacReq();
					Log.v(TAG, "onevacreq" + vacReq);
					Log.e("results onevacreq", vacReq.size() + "dd");

					List<PendingRequestModel> compReqs = resultPendingRequest.getLstCompReq();
					Log.v(TAG, "onePerReq" + compReqs);
					List<PendingRequestModel> misReqs = resultPendingRequest.getLstMisReq();
					Log.v(TAG, "oneCompReq" + misReqs);
					List<PendingRequestModel> perReqs = resultPendingRequest.getLstPerReq();
					Log.v(TAG, "oneMisReq" + perReqs);

					setRequestType(vacReq, "vac");
					setRequestType(compReqs, "comp");
					setRequestType(misReqs, "miss");
					setRequestType(perReqs, "perm");

					results = new ArrayList<PendingRequestModel>();

					results.addAll(vacReq);
					results.addAll(misReqs);
					results.addAll(compReqs);
					results.addAll(perReqs);

					Log.e("results data", results.size() + "dd");

					InitializeValues();

					swipeRefreshLayout.setRefreshing(false);

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
		else{
			if(id == ServerConfig.AcceptID || id == ServerConfig.VAcceptID)
			{
				Toast.makeText(getApplicationContext(),"Request Accepted", Toast.LENGTH_SHORT).show();
				getPendingRequest();
			}
			else
			if(id == ServerConfig.RejectID){
				Toast.makeText(getApplicationContext(),"Request Rejected", Toast.LENGTH_SHORT).show();
				getPendingRequest();
			}

		}
	}

	@Override
	public void onFailure(int id, String url, String response, int responseCode) {

		if(id == 0)
			swipeRefreshLayout.setRefreshing(false);
	}

	public void setRequestType(List<PendingRequestModel>mList,String flag){
		switch (flag){
			case "perm":
				for(int i=0;i<mList.size();i++){
					mList.get(i).setRequestType("p");
				}
				break;
			case "vac":
				for(int i=0;i<mList.size();i++){
					mList.get(i).setRequestType("v");
				}
				break;
			case "miss":
				for(int i=0;i<mList.size();i++){
					mList.get(i).setRequestType("m");
				}
				break;
			case "comp":
				for(int i=0;i<mList.size();i++){
					mList.get(i).setRequestType("c");
				}
				break;
		}

	}
}
