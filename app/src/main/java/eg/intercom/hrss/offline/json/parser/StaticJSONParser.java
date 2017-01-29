package eg.intercom.hrss.offline.json.parser;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import eg.intercom.hrss.api.APIListener;
import eg.intercom.hrss.api.ServerConfig;
import eg.intercom.hrss.helpers.Log;
import eg.intercom.hrss.helpers.MyApplication;
import retrofit2.Call;

/**
 * Created by Emad on 10/18/2016.
 */
public class StaticJSONParser {
    static String response ;
    private ProgressDialog mDialog;
    Map<String, String> mRetrofitHeader;
    APIListener mListener;
    static String mUri="user/login";
    String mMethod;
    int mID;
    Map<String, String> options;
    private Context context;
    static Call<JsonObject> call;
    public StaticJSONParser(String pUri, String pMethod, Map<String, String> pHeaders,
                            Map<String, String> options, APIListener pListener, Context context) {
        mDialog = new ProgressDialog(context);
        mDialog.setCancelable(false);
        mRetrofitHeader = pHeaders;
        mListener = pListener;
        mUri =  pUri;
        mMethod = pMethod;

        this.options = options;

        this.context = context;

        call = null;
    }

    public static String checkURI(String pUri,String mMethod){

  //  ana me7taga methode te3ml check 3la kol request we terg3 el response el crossponding leeh
        Log.e("RmMethod+pUri",mMethod+pUri +"furtui");
        switch (mMethod+pUri) {

            case ServerConfig.METHOD_POST+ServerConfig.LOGIN_PATH:
                response= loadJSONFromAsset(renamJasonFile( ServerConfig.METHOD_POST+"_"+ServerConfig.LOGIN_PATH+".json"));
                Log.e("RmResponse",response);
                break;
            case ServerConfig.METHOD_GET+ServerConfig.VAC_BALANCE:
                response= loadJSONFromAsset(renamJasonFile( ServerConfig.METHOD_GET+"_"+ServerConfig.VAC_BALANCE+".json"));
                Log.e("VBResponse",response);

                break;


            case ServerConfig.METHOD_GET+ServerConfig.PENDING_REQUEST:
                response= loadJSONFromAsset(renamJasonFile( ServerConfig.METHOD_GET+"_"+ServerConfig.PENDING_REQUEST+".json"));
                Log.e("VBResponse",response);

                break;
            case ServerConfig.METHOD_GET+ServerConfig.COMPENSATION_HISTORY:
                response= loadJSONFromAsset(renamJasonFile( ServerConfig.METHOD_GET+"_"+ServerConfig.COMPENSATION_HISTORY+".json"));
                Log.e("VBResponse",response);

                break;
            case ServerConfig.METHOD_GET+ServerConfig.PERMISSION_HISTORY:
                response= loadJSONFromAsset(renamJasonFile( ServerConfig.METHOD_GET+"_"+ServerConfig.PERMISSION_HISTORY+".json"));
                Log.e("VBResponse",response);

                break;
            case ServerConfig.METHOD_GET+ServerConfig.MISSION_HISTORY:
                response= loadJSONFromAsset(renamJasonFile( ServerConfig.METHOD_GET+"_"+ServerConfig.MISSION_HISTORY+".json"));
                Log.e("VBResponse",response);

                break;
            case ServerConfig.METHOD_GET+ServerConfig.VACATION_HISTORY:
                response= loadJSONFromAsset(renamJasonFile( ServerConfig.METHOD_GET+"_"+ServerConfig.VACATION_HISTORY+".json"));
                Log.e("VBResponse",response);

                break;
            case ServerConfig.METHOD_POST+ServerConfig.VACATION_REQUEST:
                response= loadJSONFromAsset(renamJasonFile( ServerConfig.METHOD_POST+"_"+ServerConfig.VACATION_REQUEST+".json"));
                Log.e("RmResponse",response);
                break;
            case ServerConfig.METHOD_POST+ServerConfig.PERMISSION_REQUEST:
                response= loadJSONFromAsset(renamJasonFile( ServerConfig.METHOD_POST+"_"+ServerConfig.PERMISSION_REQUEST+".json"));
                Log.e("RmResponse",response);
                break;
            case ServerConfig.METHOD_POST+ServerConfig.COMPENSATION_REQUEST:
                response= loadJSONFromAsset(renamJasonFile( ServerConfig.METHOD_POST+"_"+ServerConfig.COMPENSATION_REQUEST+".json"));
                Log.e("RmResponse",response);
                break;
            case ServerConfig.METHOD_GET+ServerConfig.PROFILE_DETAIL:
                response= loadJSONFromAsset(renamJasonFile( ServerConfig.METHOD_GET+"_"+ServerConfig.PROFILE_DETAIL+".json"));
                Log.e("RmResponse",response);
                break;



        default:
            response="No File Found";
        }
        return response;
    }


    public static String loadJSONFromAsset( String fileName) {
        String json = null;
        InputStream is = null;
//        if(MyApplication.getAppContext()==null){
//
//            Log.e("null context","here");
//        }else {
//            Log.e("not null context","here");
////            Log.e("context", String.valueOf(MyApplication.getAppContext()));
//
//
//        }
        try {

            try {


                is =  MyApplication.getAppContext().getAssets().open("offlineJSON/"+fileName);

            } catch (IOException e) {
                Log.e("exp","kekkdkdkdkdkdkd");
                e.printStackTrace();
            }
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();

            return null;
        }
        Log.e("My static response",json.toString()+"ggg");
        return  json;
    }


    public static String renamJasonFile(String fileName){
        String newFileName = fileName.replace("/", "_");
        Log.e("File Name",fileName+"ff");
        Log.e("New File Name",newFileName+"ff");
        return  newFileName;
    }
}
