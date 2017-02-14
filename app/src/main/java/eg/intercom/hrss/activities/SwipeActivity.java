package eg.intercom.hrss.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
import eg.intercom.hrss.api.PendingRequestModel;
import eg.intercom.hrss.api.ResultPendingRequest;
import eg.intercom.hrss.api.ServerConfig;
import eg.intercom.hrss.helpers.Constants;
import eg.intercom.hrss.helpers.Utility;
import eg.intercom.hrss.retrofit.RetrofitInterceptor;
import eg.intercom.hrss.retrofit.RetrofitAsynTask;
import okhttp3.OkHttpClient;

public class SwipeActivity extends Activity implements APIListener {
	Context mContext;
	String TAG = "ManageActivity Test";

	private ListView cmn_list_view;
	private ListAdapter listAdapter;
//	public LstMisReq lstMisReq;

	private ArrayList<PendingRequestModel> results;
	private ArrayList<PendingRequestModel> perlistdata,missListdata,vaclstdata;
	private ResultPendingRequest pendingRequest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe);
		cmn_list_view	=	(ListView) findViewById(R.id.cmn_list_view);



	//	perlistdata	=	new ArrayList<PendingRequestModel>();

		///////////////
		pendingRequest = new ResultPendingRequest();
		results		=	new ArrayList<PendingRequestModel>();

		mContext=this;

		getPendingRequest();

		final ListViewSwipeGesture touchListener = new ListViewSwipeGesture(
				cmn_list_view, swipeListener, this);
		touchListener.SwipeType	=	ListViewSwipeGesture.Double;    //Set two options at background of list item
		
		cmn_list_view.setOnTouchListener(touchListener);
		
		
	}


	public void getPendingRequest(){

		Log.e("eweweeeeeeeeeee", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));
		Utility.showProgressDialog(SwipeActivity.this, getString(R.string.Loading));


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

		Constants.httpClient.addInterceptor(new RetrofitInterceptor(mRetrofitHeader,mContext));

		new RetrofitAsynTask(0, ServerConfig.PENDING_REQUEST, ServerConfig.METHOD_GET,mRetrofitHeader, null
				, this, this).execute();

	}

	private void InitializeValues() {
		// TODO Auto-generated method stub
//		listdata.add(new dumpclass("one"));
//		listdata.add(new dumpclass("two"));
//		listdata.add(new dumpclass("three"));
//		listdata.add(new dumpclass("four"));
//		listdata.add(new dumpclass("five"));
//		listdata.add(new dumpclass("six"));
//		listdata.add(new dumpclass("seven"));
//		listdata.add(new dumpclass("Eight"));
		listAdapter		=	new ListAdapter(mContext, results);
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
		}

		@Override
		public void HalfSwipeListView(int position) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(),"Action_1", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void LoadDataForScroll(int count) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onDismiss(ListView listView, int[] reverseSortedPositions) {
			// TODO Auto-generated method stub



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
		if (Utility.isProgressDialogShowing())
			Utility.removeProgressDialog();
		eg.intercom.hrss.helpers.Log.e("person Response in main",response +"gg");
		try {
//			results = new LstMisReq();
			ResultPendingRequest resultPendingRequest = new ResultPendingRequest();
			Gson gson = new Gson();

			resultPendingRequest =gson.fromJson(response,ResultPendingRequest.class);
			String mResult= String.valueOf(resultPendingRequest.getResult());
			if (mResult.trim().equalsIgnoreCase("1")) {

				List<PendingRequestModel> vacReq = resultPendingRequest.getLstVacReq();
				Log.v(TAG, "onevacreq" + vacReq);
				Log.e("results onevacreq",vacReq.size() +"dd");

				List<PendingRequestModel> compReqs = resultPendingRequest.getLstCompReq();
				Log.v(TAG, "onePerReq" + compReqs);
				List<PendingRequestModel> misReqs = resultPendingRequest.getLstMisReq();
				Log.v(TAG, "oneCompReq" + misReqs);
				List<PendingRequestModel> perReqs = resultPendingRequest.getLstPerReq();
				Log.v(TAG, "oneMisReq" + perReqs);

				setRequestType(vacReq,"vac");
				setRequestType(compReqs,"comp");
				setRequestType(misReqs,"miss");
				setRequestType(perReqs,"perm");
				results.addAll(vacReq);
				results.addAll(misReqs);
				results.addAll(compReqs);
				results.addAll(perReqs);

				Log.e("results data",results.size() +"dd");
				//results = (ArrayList<PendingRequestModel>) misReqs;

				InitializeValues();

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

		}  catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFailure(int id, String url, String response, int responseCode) {

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
