package eg.intercom.hrss.retrofit;

import android.content.Context;
import android.provider.SyncStateContract;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import eg.intercom.hrss.helpers.Constants;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Hager.Magdy on 9/26/2016.
 */
public class RetrofitInterceptor implements Interceptor {

    Map<String, String> retrofitheaders;
 //   CustomSharedPrefrences CustomPrefrence;
    Context mContext;

    public RetrofitInterceptor(Map<String, String> mretrofitheaders, Context mContext) {

        this.retrofitheaders = mretrofitheaders;
        this.mContext = mContext;
        Log.e("RetrofitInterceptor  is",retrofitheaders.size() +"fkfo");
    }

    @Override
    public Response intercept(Chain chain) throws IOException {


        Request original = chain.request();
        Request.Builder builder = original.newBuilder();
        Log.e("retrofitheaders size is",retrofitheaders.size() +"fkfo");

        if(Constants.getDataInSharedPrefrences(Constants.IS_USER_LOGIN,mContext).equalsIgnoreCase("true")){
            Log.e("MyToken", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext)+"hello");
            retrofitheaders.put(Constants.USER_TOKEN,Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));
            Log.e("MyToken", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext)+"hello");
        }else {
            Log.e("2222","dhuhd");
        }

        if (retrofitheaders != null &&retrofitheaders.size()>0) {
            Log.e("mHeaders found","here");
          //  retrofitheaders.put(Constants.USER_TOKEN,Constants.getDataInSharedPrefrences(Constants.USER_TOKEN));


            // Fill in the Form parameters
            for (String key : retrofitheaders.keySet()) {
                Object value = retrofitheaders.get(key);
                if (value != null) {
                    builder.addHeader(key, value.toString());
                   Log.e("mHeaders", key + ":" + value.toString());
                } else{
                    Log.e("2222 construcor","here");
                    retrofitheaders.put(key, null);}
            }
        }else {
            Log.e("mHeaders Not found","here");
        }


      Request request = builder.build();
        Response response = chain.proceed(request);
        return response;
    }
}
