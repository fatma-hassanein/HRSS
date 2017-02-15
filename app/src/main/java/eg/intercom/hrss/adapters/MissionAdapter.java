package eg.intercom.hrss.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import eg.intercom.hrss.R;
import eg.intercom.hrss.api.LstMisHst;

/**
 * Created by Emad.Essam on 11/14/2016.
 */

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.ViewHolder> {

    Context mContext;
    ArrayList<LstMisHst> requestsHistory;

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
        LstMisHst lst = requestsHistory.get(position);

        holder.text.setText(lst.getDuration());
        holder.startDate.setText(lst.getStartDate());
        holder.endDate.setText(lst.getEndDate());

        switch (lst.getStatus()) {
            case "0":
                holder.status.setText("Pending");
                holder.statusImg.setImageResource(R.drawable.pending_img);
                break;
            case "3":
                holder.status.setText("Approved");
                holder.statusImg.setImageResource(R.drawable.approve_img);
                break;
            case "4":
                holder.status.setText("Rejected");
                holder.statusImg.setImageResource(R.drawable.reject_img);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return (null != requestsHistory ? requestsHistory.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView text;
        protected TextView startDate;
        protected TextView endDate;
        protected TextView status;
        protected ImageView statusImg;

        public ViewHolder(View view) {

            super(view);
            this.text = (TextView) view.findViewById(R.id.mis_his);
            this.startDate = (TextView) view.findViewById(R.id.mis_start_date);
            this.endDate = (TextView) view.findViewById(R.id.mis_end_date);
            this.status = (TextView) view.findViewById(R.id.mis_status);
            this.statusImg = (ImageView) view.findViewById(R.id.status_image);

        }

    }
}
