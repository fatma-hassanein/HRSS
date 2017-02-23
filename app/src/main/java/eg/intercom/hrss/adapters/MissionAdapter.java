package eg.intercom.hrss.adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import eg.intercom.hrss.R;
import eg.intercom.hrss.activities.MissionActivity;
import eg.intercom.hrss.api.APIListener;
import eg.intercom.hrss.api.LstMisHst;
import eg.intercom.hrss.api.ServerConfig;
import eg.intercom.hrss.helpers.Constants;
import eg.intercom.hrss.helpers.Log;
import eg.intercom.hrss.helpers.Utility;
import eg.intercom.hrss.retrofit.RetrofitAsynTask;
import eg.intercom.hrss.retrofit.RetrofitInterceptor;
import okhttp3.OkHttpClient;

/**
 * Created by Emad.Essam on 11/14/2016.
 */

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.ViewHolder> implements APIListener {

    Context mContext;
    ArrayList<LstMisHst> requestsHistory;
    int clickedPosition;

    public MissionAdapter(Context context, ArrayList<LstMisHst> lstMisHstArrayList) {

        this.mContext = context;
        this.requestsHistory = lstMisHstArrayList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.mis_item_history, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        final int  itemPosition = position;
        final LstMisHst lst = requestsHistory.get(position);

//        holder.text.setText(lst.getDuration());
        holder.startDate.setText(lst.getStartDate());
        holder.endDate.setText(lst.getEndDate());
        holder.remarks.setText(lst.getRemarks());

        switch (lst.getStatus()) {
            case "0":
               // holder.status.setText("Pending");
                holder.statusImg.setImageResource(R.drawable.three_dots);
                final ViewHolder mholder = holder;

                holder.statusImg.setOnClickListener( new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        final PopupMenu popmenu = new PopupMenu(mContext, mholder.statusImg);
                        popmenu.getMenuInflater().inflate(R.menu.menu_mission, popmenu.getMenu());

                        popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                        {
                            public boolean onMenuItemClick(MenuItem item)
                            {
                                Toast.makeText(mContext, "You Clicked : " + item.getTitle() + lst.getRefNo() , Toast.LENGTH_SHORT).show();
                                Log.d(clickedPosition+" ", "clicked Position");
                                Log.d(lst.getRefNo(), "Ref No. ");

                                new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Are you sure?")
                                        .setContentText("Your Request will be removed")
                                        .setConfirmText("Yes")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {

                                                clickedPosition = itemPosition;
                                                Log.d(clickedPosition+" ", "clicked Position");
                                                Log.d(lst.getRefNo(), "Ref No. ");

                                                Map<String, String> mRetrofitHeader = new HashMap<>();
                                                Map<String, String> mRetrofitParams = new HashMap<>();
                                                String url = "";
                                                String TOKEN = Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext);

                                                mRetrofitHeader.put("token",TOKEN);
                                                Utility.generateRetrofitHttpHeader(mContext);

                                                Constants.httpClient = new OkHttpClient.Builder();
                                                Constants.httpClient.addInterceptor(new RetrofitInterceptor(mRetrofitHeader,mContext));

                                                mRetrofitParams.put("perCategory", "2");
                                                mRetrofitParams.put("mgrCode", Constants.getDataInSharedPrefrences(Constants.MGR_CODE,mContext));
                                                mRetrofitParams.put("mgrName", Constants.getDataInSharedPrefrences(Constants.MGR_NAME,mContext));
                                                url = 	ServerConfig.MISSION + lst.getRefNo() + ServerConfig.CANCEL;

                                                new RetrofitAsynTask(ServerConfig.CancelID, url, ServerConfig.METHOD_POST, mRetrofitHeader, mRetrofitParams
                                                        , MissionAdapter.this, mContext).execute();

                                                sDialog
                                                       .setTitleText("Done")
                                                       .setContentText("Request Canceled Successfully")
                                                       .setConfirmText("OK")
                                                       .setConfirmClickListener(null)
                                                       .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                            }
                                        })
                                        .setCancelText("No")
                                        .showCancelButton(true)
                                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.cancel();
                                            }
                                        })
                                        .show();


                                return true;
                            }
                        });

                        popmenu.show();
                    }
                });
                break;
            case "3":
               // holder.status.setText("Approved");
                holder.statusImg.setImageResource(R.drawable.approve_img);
                break;
            case "4":
               // holder.status.setText("Rejected");
                holder.statusImg.setImageResource(R.drawable.reject_img);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return (null != requestsHistory ? requestsHistory.size() : 0);
    }

    @Override
    public void onSuccess(int id, String url, String response) {

        Toast.makeText(mContext,"Request Canceled", Toast.LENGTH_SHORT).show();

        Log.v(response + " \n" + url,"Response");
        //requestsHistory.remove(clickedPosition);
    }

    @Override
    public void onFailure(int id, String url, String response, int responseCode) {

       Toast.makeText(mContext,"Request Failed", Toast.LENGTH_SHORT).show();
        Log.v(response + " \n" + url,"Response");
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        protected TextView text;
        protected TextView startDate;
        protected TextView endDate;
//        protected TextView status;
        protected ImageView statusImg;
        protected TextView remarks;

        public ViewHolder(View view) {

            super(view);
//            this.text = (TextView) view.findViewById(R.id.mis_his);
            this.startDate = (TextView) view.findViewById(R.id.mis_start_date);
            this.endDate = (TextView) view.findViewById(R.id.mis_end_date);
//            this.status = (TextView) view.findViewById(R.id.mis_status);
            this.statusImg = (ImageView) view.findViewById(R.id.status_image);
            this.remarks = (TextView) view.findViewById(R.id.mis_remarks);

        }

    }
}
