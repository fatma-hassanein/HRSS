package eg.intercom.hrss.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import eg.intercom.hrss.R;
import eg.intercom.hrss.api.LstCompReq;
import eg.intercom.hrss.api.LstMisReq;
import eg.intercom.hrss.api.LstPerReq;
import eg.intercom.hrss.api.LstVacReq;
import eg.intercom.hrss.api.PendingRequestModel;
import eg.intercom.hrss.api.ResultPendingRequest;


public class ListAdapter extends BaseAdapter {
    Activity activity;
    Context mContext;
    String TAG = "ManageActivity Test";

    ArrayList<PendingRequestModel> requests;

    //	ArrayList<ResultPendingRequest> requests;
    //	private ArrayList<dumpclass> data;
//	public ListAdapter(Activity a, ArrayList<dumpclass> basicList){
//		activity	=	a;
//		data		=	basicList;
//	}
    public ListAdapter(Context context, ArrayList<PendingRequestModel> resultPendingRequests) {
        this.mContext = context;


        this.requests = resultPendingRequests;


    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return requests.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return requests.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.test_layout, null);
            holder.req_typ = (ImageView) convertView.findViewById(R.id.req_image);
            holder.name = (TextView) convertView.findViewById(R.id.emp_name);
            holder.startDate = (TextView) convertView.findViewById(R.id.from_mis);
            holder.listItem= (LinearLayout) convertView.findViewById(R.id.SingleProfile);
            holder.endDate = (TextView) convertView.findViewById(R.id.to_mis);
            holder.image = (CircleImageView) convertView.findViewById(R.id.mem_photo_img_id);
//            holder.background = (Button) convertView.findViewById(R.id.type_background);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

//		List<LstVacReq> vacReq = requests.get(position).getLstVacReq();
//		Log.v(TAG, "onevacreq" + vacReq);
//		List<LstCompReq> compReqs = requests.get(position).getLstCompReq();
//		Log.v(TAG, "onePerReq" + compReqs);
//		List<LstMisReq> misReqs = requests.get(position).getLstMisReq();
//		Log.v(TAG, "oneCompReq" + misReqs);
//		List<LstPerReq> perReqs = requests.get(position).getLstPerReq();
//		Log.v(TAG, "oneMisReq" + perReqs);
        String empEnName = requests.get(position).getEmpEnName();
        String[] separated = empEnName.split(" ");
        String test = separated[0];
//        String last = separated[separated.length-1];
        empEnName = test+" ";
        holder.name.setText(empEnName+"requested a new mission");
        holder.startDate.setText(requests.get(position).getStartDate());
        holder.endDate.setText(requests.get(position).getEndDate());

if(requests.get(position).getRequestType().equalsIgnoreCase("m")){
//    holder.listItem.setBackgroundColor(Color.parseColor("#e0e595"));
//    convertView.setBackgroundColor();
    holder.req_typ.setImageResource(R.drawable.mis_image);
}else if(requests.get(position).getRequestType().equalsIgnoreCase("p")){
//    holder.background.setBackgroundColor(Color.parseColor("#488ea0"));
    holder.req_typ.setImageResource(R.drawable.per_image);

//    holder.listItem.setBackgroundColor(Color.parseColor("#d0e1e5"));
}else if(requests.get(position).getRequestType().equalsIgnoreCase("c")){
//    holder.listItem.setBackgroundColor(Color.parseColor("#e8aed6"));
    holder.req_typ.setImageResource(R.drawable.com_image);

//    convertView.setBackgroundColor(Color.parseColor("#a04886"));
        }else if(requests.get(position).getRequestType().equalsIgnoreCase("v")){
//    holder.listItem.setBackgroundColor(Color.parseColor("#ed9393"));
    holder.req_typ.setImageResource(R.drawable.vac_image);

//    convertView.setBackgroundColor(Color.parseColor("#a04848"));
        }
        return convertView;
    }


    public static class ViewHolder {
        TextView name;
        TextView startDate;
        TextView endDate;
        TextView remark;
        ImageView req_typ;
        
//        Button background;
        LinearLayout listItem;
        CircleImageView image;

    }
}
