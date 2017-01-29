package eg.intercom.hrss.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import eg.intercom.hrss.R;
import eg.intercom.hrss.api.LstCompHst;
import eg.intercom.hrss.api.LstPerHst;

/**
 * Created by Emad.Essam on 11/14/2016.
 */

public class CompensationAdapter extends RecyclerView.Adapter<CompensationAdapter.ViewHolder> {


    Context mContext;
    String TAG = "PermissionAdapter";

    ArrayList<LstCompHst> requestsHistory;

    public  CompensationAdapter (Context context, ArrayList<LstCompHst> lstCompHstArrayList) {

        this.mContext = context;


        this.requestsHistory = lstCompHstArrayList;


    }
    @Override
    public CompensationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.comp_item_history, null);
        ViewHolder viewHolder = new ViewHolder(view);
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CompensationAdapter.ViewHolder holder, int position) {
        LstCompHst lst = requestsHistory.get(position);

        holder.text.setText(lst.getEmpEnName());
        holder.startDate.setText(lst.getStartDate());
    }

    @Override
    public int getItemCount() {
        return (null != requestsHistory ? requestsHistory.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView text;
        protected   TextView startDate;
        protected ImageView image;

        public ViewHolder(View view){

            super(view);
            this.text = (TextView) view.findViewById(R.id.comp_his);
            this.startDate  = (TextView) view.findViewById(R.id.comp_start_date);

        }



    }
}
